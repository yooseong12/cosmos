package com.toy.cosmos.auth.config;

import com.toy.cosmos.auth.model.TokenResponseDto;
import com.toy.cosmos.auth.exception.login.ExpiredTokenException;
import com.toy.cosmos.auth.exception.login.InvalidTokenException;
import com.toy.cosmos.domain.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

@Slf4j
@Component
public class TokenProvider {

    private static final String TOKEN_TYPE = "Bearer";

    private static final String AUTHORITY_KEY = "auth";

    private static final long ACCESS_TOKEN_EXPIRE_TIME_MILLIS = 24L * 60L * 60L * 1000L;

    private static final long REFRESH_TOKEN_EXPIRE_TIME_MILLIS = 30L * 24L * 60L * 60L * 1000L;

    private Key key;

    //todo: 현준

    /**
     * 현상
     * ApiApplication 을 실행하면 Could not resolve placeholder 오류 발생
     *
     * 이유
     * ApiApplication 이 application-auth.yml 를 읽어오지 못함.
     *
     * 해결방법
     * ApiApplication 이 application-test.yml 을 불러와야함
     *
     * 이유와 해결 방법은 알아 냈는데, Test 환경에서는 ActiveProfile 로 application-test.yml 을 불러오는 것을 확인했습니다.
     * 하지만 ApiApplication 이 실행 될 때에는 어떻게 application-test.yml 을 불러오는지 아무리 찾아봐도 모르겠습니다.
     * 다른 오류도 이와 같이 application-test.yml 을 불러오지 못해서 생긴 오류 입니다.
      */

    @Value("${security.jwt.key}")
    String secretKey;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

    public TokenResponseDto generateTokenResponse(User user, boolean tmpPasswordUsed, boolean isJoin) {
        long now = new Date().getTime();
        return TokenResponseDto.builder()
                .tokenType(TOKEN_TYPE)
                .accessToken(generateToken(user, new Date(now + ACCESS_TOKEN_EXPIRE_TIME_MILLIS)))
                .expiresIn((now + ACCESS_TOKEN_EXPIRE_TIME_MILLIS) / 1000)
                .refreshToken(generateRefreshToken(user, new Date(now + REFRESH_TOKEN_EXPIRE_TIME_MILLIS)))
                .refreshTokenExpiresIn((now + REFRESH_TOKEN_EXPIRE_TIME_MILLIS) / 1000)
                .tmpPasswordUsed(tmpPasswordUsed)
                .isJoin(isJoin)
                .build();
    }

    public TokenResponseDto generateTokenOnlyResponse(User user) {
        long now = new Date().getTime();
        return TokenResponseDto.builder()
                .tokenType(TOKEN_TYPE)
                .accessToken(generateToken(user, new Date(now + ACCESS_TOKEN_EXPIRE_TIME_MILLIS)))
                .expiresIn((now + ACCESS_TOKEN_EXPIRE_TIME_MILLIS) / 1000)
                .build();
    }

    private String generateToken(User user, Date expDate) {
        return Jwts.builder()
                .setIssuer("cosmos")
                .setSubject(user.getId().toString())
                .claim(AUTHORITY_KEY, user.getAuthority())
                .setExpiration(expDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    private String generateRefreshToken(User user, Date expDate) {
        return Jwts.builder()
                .setSubject(user.getId().toString())
                .setExpiration(expDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = parseClaims(token);
        if (ObjectUtils.isEmpty(claims.get(AUTHORITY_KEY))) {
            throw new InvalidTokenException(token);
        }

        Collection<? extends GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(claims.get(AUTHORITY_KEY).toString()));
        return new UsernamePasswordAuthenticationToken(claims.getSubject(), "", authorities);
    }

    public boolean isValidToken(String token) {
        parseClaims(token);
        return true;
    }

    public Claims parseClaims(String token) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            throw new ExpiredTokenException(token);
        } catch (Exception e) {
            throw new InvalidTokenException(token);
        }
    }

    public String getJwtToken(HttpServletRequest request) {
        String jwtToken = Optional.ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION))
                .orElseThrow(NullPointerException::new);

        if (!StringUtils.hasText(jwtToken) || !jwtToken.startsWith(TOKEN_TYPE)) {
            throw new InvalidTokenException(jwtToken);
        }

        return jwtToken.substring(7);
    }
}