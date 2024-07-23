package com.soumen.logback_poc;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Instant;
import java.util.concurrent.Executors;

@SpringBootApplication
@Slf4j
public class LogbackPocApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogbackPocApplication.class, args);
    }

    @Bean
    ApplicationRunner applicationRunner() {
        return args -> {
            //var executorService = Executors.newFixedThreadPool(5);
            //var executorService = Executors.newVirtualThreadPerTaskExecutor();
            var executorService = Executors.newFixedThreadPool(Runtime.getRuntime()
                    .availableProcessors());

            while (true) {
                executorService.submit(() -> {
                    MDC.put("TNAME", "worker-" + Thread.currentThread()
                            .getId());
                    log.info("Hello @ : {}", Instant.now());
                    MDC.clear();
                    try {
                        Thread.sleep(2000l);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        };
    }
}
