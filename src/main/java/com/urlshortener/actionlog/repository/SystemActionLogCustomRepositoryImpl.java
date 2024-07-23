package com.urlshortener.actionlog.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.urlshortener.actionlog.domain.SystemActionLog;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.urlshortener.actionlog.domain.QSystemActionLog.systemActionLog;

@Repository
@AllArgsConstructor
public class SystemActionLogCustomRepositoryImpl implements SystemActionLogCustomRepository {
    private final JPAQueryFactory query;

    @Override
    public List<SystemActionLog> findNullMemberIdLogsLimitedTo1000() {
        return query.selectFrom(systemActionLog)
                .where(systemActionLog.memberId.isNull())
                .limit(1000)
                .fetch();
    }
}