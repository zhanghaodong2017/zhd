package com.redis;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisTest {
	public static void main(String[] args) {
		String key="mymap";
		Map<String, String> map = new HashMap<String, String>();
		map.put("abcw", "12312");
		map.put("abcsw", "12312");
		map.put("abcs", "12312");
		RedisUtils.setObjectValue(key, map);

		Map<String, String> map2 =(Map<String, String>) RedisUtils.getObjectValue(key);
		System.out.println(map2);
		System.out.println(RedisUtils.getStringValue("123123"));
	}
}
