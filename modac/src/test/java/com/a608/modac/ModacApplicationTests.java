package com.a608.modac;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.a608.modac.service.ChatServiceImpl;

@SpringBootTest
class ModacApplicationTests {

	@Autowired
	private ChatServiceImpl chatService;

	@Test
	void redis_save_test() {

		//when
//		final ChatCache saveChatCache = chatService.saveToRedis(chatCache);
//		System.out.println(chatCache.toString());
//		final String toRedis = chatService.findToRedis(chatCache.getSeq());
		//then
//		System.out.println(toRedis);
//		Assertions.assertThat(toRedis).isEqualTo("test1");
	}

}
