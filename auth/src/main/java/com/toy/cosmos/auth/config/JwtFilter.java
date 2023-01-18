package com.toy.cosmos.auth.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        try {
            String jwtToken = tokenProvider.getJwtToken(request);

            if (!ObjectUtils.isEmpty(jwtToken) && tokenProvider.isValidToken(jwtToken)) {
                Authentication authentication = tokenProvider.getAuthentication(jwtToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception ignored) {
        }
        filterChain.doFilter(request, response);
    }
}