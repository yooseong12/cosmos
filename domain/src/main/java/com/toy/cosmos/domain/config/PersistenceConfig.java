package com.toy.cosmos.domain.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.ZonedDateTime;
import java.util.Optional;

@Configuration
@EntityScan("com.hyunjun.report.domain.entity")
@EnableJpaRepositories("com.toy.cosmos.domain.repository")
@EnableJpaAuditing(dateTimeProviderRef = "auditingDateTimeProvider")
public class PersistenceConfig {

    @Bean
    public DateTimeProvider auditingDateTimeProvider() {
        return () -> Optional.of(ZonedDateTime.now());
    }

}
