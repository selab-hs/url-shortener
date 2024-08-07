package com.urlshortener.config.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Slf4j
@Configuration
@EnableAsync
public class AsyncConfig {
    @Bean(name = "taskExecutor")
    public ThreadPoolTaskExecutor taskExecutor() {
        var executor = new ExecutorGenerator(
                10,
                15,
                15,
                "taskExecutor"
        );

        return executor.generate();
    }

    @Bean(name = "systemActionLogExecutor")
    public ThreadPoolTaskExecutor systemActionLogExecutor() {
        var executor = new ExecutorGenerator(
                10,
                15,
                15,
                "taskExecutor"
        );

        return executor.generate();
    }
}