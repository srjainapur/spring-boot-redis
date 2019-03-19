package com.zensar.redis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zensar.redis.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
