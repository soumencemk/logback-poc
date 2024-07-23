package com.soumen.logback_poc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class AppConfig {

    @Bean
    TestBean testBean() {
        log.info("Creating : testBean");
        return new TestBean();
    }
}
