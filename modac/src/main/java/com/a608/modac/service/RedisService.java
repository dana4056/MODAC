package com.a608.modac.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

        // 오늘 자정 - 현재시간 -> 남은시간으로 만료일 지정.
        // 만료일자 해당일 자정
        redisTemplate.expire(String.valueOf(memberSeq), calculateExpire().getSeconds(), TimeUnit.SECONDS);
    }

    private static Duration calculateExpire() {
        LocalDateTime expire = LocalDateTime.now().with(LocalTime.MAX);
        LocalDateTime now = LocalDateTime.now();
        Duration between = Duration.between(now, expire);
        return between;
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
