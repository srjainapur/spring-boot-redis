package com.zensar.redis.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="NAME", length=50, nullable=false)
	private String name;
	
	@Column(name="FOLLOWERS")
	private long followers;
	
	public User() {
    }

    public User(String name, long followers) {
        this.name = name;
        this.followers = followers;
    }

    //standard getters and setters
    

    @Override
    public String toString() {
        return String.format("User{id=%d, name='%s', followers=%d}", id, name, followers);
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getFollowers() {
		return followers;
	}

	public void setFollowers(long followers) {
		this.followers = followers;
	}
}
