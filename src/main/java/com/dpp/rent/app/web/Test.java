package com.dpp.rent.app.web;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

public class Test {

	public static void main(String[] args) {
		// 创建一个spring容器  
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(  
                "classpath:spring*.xml");  
        // 从容器中获得JedisClient对象  
        RedisTemplate redisTemplate = (RedisTemplate) applicationContext.getBean("redisTemplate");  
        redisTemplate.opsForValue().set("hello","world");  
        System.out.println(redisTemplate.opsForValue().get("hello"));  
	}

}
