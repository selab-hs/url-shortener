package com.urlshortener.shortener.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.urlshortener.shortener.domain.QShortUrl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ShortUrlCustomRepositoryImpl implements ShortUrlCustomRepository {

    private final JPAQueryFactory query;

    @Override
    @Transactional
    public void updateShortenerUrlMemberId(String uuid, Long memberId) {
        final int batchSize = 1000;
        long lastId = 0;
        List<Long> ids;

        do {
            ids = query.select(QShortUrl.shortUrl.id)
                    .from(QShortUrl.shortUrl)
                    .where(QShortUrl.shortUrl.uuid.eq(uuid)
                            .and(QShortUrl.shortUrl.id.gt(lastId)))
                    .orderBy(QShortUrl.shortUrl.id.asc())
                    .limit(batchSize)
                    .fetch();

            if (!ids.isEmpty()) {
                query.update(QShortUrl.shortUrl)
                        .where(QShortUrl.shortUrl.id.in(ids))
                        .set(QShortUrl.shortUrl.memberId, memberId)
                        .execute();

                lastId = ids.get(ids.size() - 1);  // Update last processed id
            }
        } while (ids.size() == batchSize); // 마지막 배치가 최대 크기에 도달했을 때 반복
    }
}