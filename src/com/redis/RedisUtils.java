package com.redis;

import java.io.UnsupportedEncodingException;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisUtils {
	private static JedisPool jedisPool = new JedisPool("192.168.1.205", 6379);

	public static Jedis getJedis(){
		Jedis jedis = jedisPool.getResource();
		jedis.auth("Lemi20132015");
		jedis.select(15);
		return jedis;
	}
	public static void setStringValue(String key,String value){
		Jedis jedis = getJedis();
		try {
			jedis.set(key, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		jedis.disconnect();
	}
	public static void setStringValue(String key,int timeout,String value){
		Jedis jedis = getJedis();
		try {
			jedis.set(key, value);
			jedis.expire(key, timeout);
		} catch (Exception e) {
			e.printStackTrace();
		}
		jedis.disconnect();
	}
	public static void setObjectValue(String key,Object value) {
		Jedis jedis = getJedis();
		try {
			jedis.set(key.getBytes("utf-8"), SerializeUtil.serialize(value));
		} catch (Exception e) {
			e.printStackTrace();
		}
		jedis.disconnect();
	}
	public static void setObjectValue(String key,int timeout,Object value) {
		Jedis jedis = getJedis();
		try {
			jedis.set(key.getBytes("utf-8"), SerializeUtil.serialize(value));
			jedis.expire(key.getBytes("utf-8"), timeout);
		} catch (Exception e) {
			e.printStackTrace();
		}
		jedis.disconnect();
	}

	public static String getStringValue(String key){
		Jedis jedis = getJedis();
		String value = jedis.get(key);
		jedis.disconnect();
		return value;
	}
	public static Object getObjectValue(String key) {
		Jedis jedis = getJedis();
		byte[] value = null;
		try {
			value = jedis.get(key.getBytes("utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		jedis.disconnect();
		return SerializeUtil.unserialize(value);

	}
	public static void deleteValue(String key){
		Jedis jedis = getJedis();
		jedis.del(key);
		jedis.disconnect();
	}
	public static void deleteValue(byte[] key){
		Jedis jedis = getJedis();
		jedis.del(key);
		jedis.disconnect();
	}
	public static boolean isExist(String key){
		Jedis jedis = getJedis();
		String value = jedis.get(key);
		jedis.disconnect();
		if(value != null){
			return true;
		}else{
			return false;
		}

	}
}
