package com.memcached;

import java.util.ArrayList;

import net.spy.memcached.MemcachedClient;

import com.codebuilding.Memcaches;

public class LimitTest {
	public static MemcachedClient cache = Memcaches.getMem();
	public static void main(String[] args) {
		ArrayList<String> limit = (ArrayList<String>) cache.get("DayLimitUrl");
		System.out.println(limit);
		String orderidstate = (String) cache.get("orderidstate-iuxioks9lD");
		String price = (String) cache.get("ordernoprice-iuxioks9lD") +"";
		System.out.println(orderidstate);
		System.out.println(price);
		Object sucnum = cache.get("sucnum-MYTYYD20_12");
		System.out.println(sucnum);

	}
}
