package com.urlshortener.config.database;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = {
        "com.urlshortener.actionlog.repository", // 해당 리포지토리 포함되어 있는지 확인
        "com.urlshortener.shortener.repository",
        "com.urlshortener.member.repository"
}, repositoryImplementationPostfix = "Impl")
@EnableTransactionManagement
public class JpaConfig {
}