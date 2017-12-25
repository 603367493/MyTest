package com.cm.common;

import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.JedisPool;

public class RedisApplicationsDao {
	@Autowired
	public JedisPool jedisPool;
	
	
	
	
	public void get(String key){
		
	}
}
