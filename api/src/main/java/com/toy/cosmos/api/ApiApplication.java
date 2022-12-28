package com.toy.cosmos.api;

import com.toy.cosmos.domain.entity.AttachedFile;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(scanBasePackages = "com.toy.cosmos")
@EntityScan(basePackages = {"com.toy.cosmos"})
@EnableConfigurationProperties(
        {AttachedFile.class}
)
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

}
