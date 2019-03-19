package com.zensar.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

import com.zensar.redis.model.User;
import com.zensar.redis.repository.UserRepository;

@SpringBootApplication
@EnableCaching
public class SpringRedisDemoApplication implements CommandLineRunner {

	private final Logger log = LoggerFactory.getLogger(SpringRedisDemoApplication.class);
	
	private final UserRepository userRepository;

	@Autowired
	public SpringRedisDemoApplication(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringRedisDemoApplication.class, args);
	}

	@Override
	public void run(String ...args) {
		//Populating embedded database here
		log.info("Saving users. Current user count is {}.", userRepository.count());
	    User shubham = new User("Shubham", 2000);
	    User pankaj = new User("Pankaj", 29000);
	    User lewis = new User("Lewis", 550);

	    userRepository.save(shubham);
	    userRepository.save(pankaj);
	    userRepository.save(lewis);
	    log.info("Done saving users. Data: {}.", userRepository.findAll());
	}	
	
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		return new JedisConnectionFactory();
	}
	
	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
		template.setConnectionFactory(jedisConnectionFactory());
		template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
		
		return template;
	}
	
}

