package com.zensar.redis.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.redis.model.User;
import com.zensar.redis.repository.UserRepository;

@RestController
public class UserController {
	
	private final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Cacheable(value="users", key="#userId", unless="#result.followers < 12000")
	@RequestMapping(value="/getUser/{userId}", method=RequestMethod.GET)
	public User getUser(@PathVariable("userId") String userId) {
		log.info("Getting user with ID {}.", userId);
		return userRepository.findById(Long.valueOf(userId)).get();
	}
	
	// Updating Cache
	@CachePut(value="users", key="#userId")
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public User updatePersonByID(@RequestBody User user) {
        return userRepository.save(user);
    }
	
	// Clearing Cache
	@CacheEvict(value="users", allEntries=true)
	@RequestMapping(value="/delete/{userId}", method=RequestMethod.DELETE)
	public void removeUserById(@PathVariable("userId") String userId) {
		log.info("deleting person with id {}", userId);
		userRepository.deleteById(Long.valueOf(userId));
	}
}
