package com.a608.modac.service;

import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisService {
    public static final String DELIMITER = "_";
    @Autowired
    private final RedisTemplate<String, String> redisTemplate;

    public void saveReadArticle(final Long memberSeq, final Long articleSeq) {
        final ValueOperations<String, String> readArticleValueOperations = redisTemplate.opsForValue();
        readArticleValueOperations.append(String.valueOf(memberSeq), articleSeq + DELIMITER);

        redisTemplate.expire(String.valueOf(memberSeq), Duration.ofDays(1).getSeconds(), TimeUnit.SECONDS); // 만료일자 1일
    }

    public Optional<String> findById(final Long memberSeq) {
        final ValueOperations<String, String> readArticleValueOperations = redisTemplate.opsForValue();
        String readArticles = readArticleValueOperations.get(String.valueOf(memberSeq));

        if (readArticles == null) {
            return Optional.empty();
        }

        return Optional.ofNullable(readArticleValueOperations.get(String.valueOf(memberSeq)));
    }

}
