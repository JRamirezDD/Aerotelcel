package com.aerotelcel.subscription_redis;

import com.subscription_redis.config.RedisConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest(classes = {RedisConfig.class, RedisConfig.class})
@ExtendWith(SpringExtension.class)
class SubscriptionRedisApplicationTests {

	@Test
	void contextLoads() {
	}

}
