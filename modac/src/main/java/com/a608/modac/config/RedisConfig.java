package com.a608.modac.config;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.a608.modac.model.chat.DirectMessage;

@Configuration
public class RedisConfig implements Serializable {
	private static final long serialVersionUID = -6687460185264018379L;
	@Value("${spring.redis.host}")
	private String host;

	@Value("${spring.redis.port}")
	private int port;

	@Bean
	public RedisTemplate<String, DirectMessage> redisTemplate() {
		final RedisTemplate<String, DirectMessage> redisTemplate = new RedisTemplate<>();
		final LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(host, port);

		lettuceConnectionFactory.afterPropertiesSet();
		redisTemplate.setConnectionFactory(lettuceConnectionFactory);

		return redisTemplate;
	}

}
