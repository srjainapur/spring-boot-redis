package com.zensar.redis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;

//@Configuration
public class RedisConfig {
	
	@Autowired
	RedisConnectionFactory redisConnectionFactory;
	
	
}
