package com.urlshortener.actionlog.repository;

import com.urlshortener.actionlog.domain.SystemActionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface SystemActionLogRepository extends JpaRepository<SystemActionLog, Long>, SystemActionLogCustomRepository {
    Long countByCreatedAtBetween(LocalDateTime startAt, LocalDateTime endAt);

    List<SystemActionLog> findByUrlId(Long urlId);

    Long countByUrlId(Long urlId);
}