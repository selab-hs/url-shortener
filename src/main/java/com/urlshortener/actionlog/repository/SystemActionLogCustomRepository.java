package com.urlshortener.actionlog.repository;

import com.urlshortener.actionlog.domain.SystemActionLog;

import java.util.List;

public interface SystemActionLogCustomRepository {
    List<SystemActionLog> findNullMemberIdLogsLimitedTo1000();
}