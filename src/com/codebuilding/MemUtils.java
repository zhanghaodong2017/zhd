package com.codebuilding;

import net.spy.memcached.MemcachedClient;

import org.apache.commons.lang.StringUtils;


public class MemUtils {
	public static final String ORDERNO_PRICE = "ordernoprice-";
	public static final String ORDERNO_DISTRO = "ordernodistro-";
	public static final String ORDERNO_FM = "ordernofm-";
	public static final String ORDERNO_PRO = "ordernopro-";
	public static final String ORDERNO_FROM = "ordernofrom-";
	public static final Integer exp = 3600;

	public static MemcachedClient cache = Memcaches.getMem();

	public static void setOrderId(String orderId, String distro, String fm, String pro, String price) {
		cache.set(ORDERNO_FROM + orderId, exp, "LM");
		if (distro != null) {
			cache.set(ORDERNO_DISTRO + orderId, exp, distro);
		}
		if (fm != null) {
			cache.set(ORDERNO_FM + orderId, exp, fm);
		}
		if (pro != null) {
			cache.set(ORDERNO_PRO + orderId, exp, pro);
		}
		if (price != null) {
			cache.set(ORDERNO_PRICE + orderId, exp, price);
		}

	}
	public static void setOrderId(String orderId, String bieming,
			String extparams, String fm, String pro, String price) {
		if(bieming !=null){
			cache.set(ORDERNO_FROM + orderId, exp, bieming);
		}
		if (extparams != null) {
			cache.set(ORDERNO_DISTRO + orderId, exp, extparams);
		}
		if (fm != null) {
			cache.set(ORDERNO_FM + orderId, exp, fm);
		}
		if (pro != null) {
			cache.set(ORDERNO_PRO + orderId, exp, pro);
		}
		if (price != null) {
			cache.set(ORDERNO_PRICE + orderId, exp, price);
		}

	}
	public static Integer getPrice(String orderId){
		String price = cache.get(ORDERNO_PRICE + orderId)+"";
		if(StringUtils.isNotBlank(price)){
			return Integer.valueOf(price);
		}else{
			return 0;
		}
	}
	public static String getFm(String orderId){
		String fm = cache.get(ORDERNO_FM + orderId)+"";
		return fm;
	}
	public static String getPro(String orderId){
		String pro = cache.get(ORDERNO_PRO + orderId)+"";
		return pro;
	}
	public static String getDistro(String orderId){
		String distro = cache.get(ORDERNO_DISTRO + orderId)+"";
		return distro;
	}
	public static String getFrom(String orderId){
		String from = cache.get(ORDERNO_FROM + orderId)+"";
		return from;
	}
	public static void main(String[] args) {
//		cache.set("dingzhan-pid", 0, "http://203.88.197.167:9973/ctmmfee/open189.do?pid=90215311");
//		cache.delete("gtw-M-10-5");
		String t = (String) cache.get("reqtime-ZRKJDM");
		System.out.println(t);
	}
}
