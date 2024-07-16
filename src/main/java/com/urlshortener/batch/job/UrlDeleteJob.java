package com.urlshortener.batch.job;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class UrlDeleteJob {
    private final NamedParameterJdbcTemplate template;
    private final LocalDateTime sixMonthsAgo = LocalDateTime.now().minusMonths(6);
    private final static int BATCH_SIZE = 1000;

    @Transactional
    public void removeSixMonthsOldData() {
        Long startDelete = System.currentTimeMillis();

        while (true) {
            Long starTimeBatch = System.currentTimeMillis();
            List<Long> idsToDelete = template.queryForList(
                    "SELECT id FROM short_url WHERE created_at >= :sixMonthsAgo LIMIT :batchSize",
                    new MapSqlParameterSource()
                            .addValue("sixMonthsAgo", sixMonthsAgo)
                            .addValue("batchSize", BATCH_SIZE),
                    Long.class);

            if (idsToDelete.isEmpty()) {
                Long endDelete = System.currentTimeMillis();
                Long result = endDelete - startDelete;
                log.info("DATA All Delete Time = {} ms", result);
                break;
            }

            int deletedRows = template.update(
                    "DELETE FROM short_url WHERE id IN (:ids)",
                    new MapSqlParameterSource("ids", idsToDelete));
            Long endTimeBatch = System.currentTimeMillis();
            Long result = endTimeBatch - starTimeBatch;
            log.info("6개월 지난 DATA Delete = {} rows, Time = {} ms", deletedRows, result);
        }
    }
}