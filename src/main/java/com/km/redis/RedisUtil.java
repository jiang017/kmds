package com.km.redis;

import redis.clients.jedis.Jedis;  

public class RedisUtil { 
	
	private static Jedis jedis = null;
	
	public static Jedis getJedisInstall(){
		if (jedis == null) {
			jedis = new Jedis("192.168.1.6",6379);    
		}
		return jedis;
	}
	
//	List<Student> userList = JSON.parseArray(jedis.get("userList"), Student.class);
  
}  