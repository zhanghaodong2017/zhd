package com.test;

import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;

import com.lemi.utils.HttpUtil;


public class RedirectUtil {
	private static Logger logger = Logger.getLogger(RedirectUtil.class);
	public static void redirect(String sp, String cp, Map<String, String> params) {
		StringBuffer sb = new StringBuffer();
		if (params != null) {
			Set<String> keys = params.keySet();
			if (keys != null) {
				for (String key : keys) {
					sb.append(key);
					sb.append("=" + params.get(key) + "&");
				}
			}
		}

		HttpClient client = HttpClients.createDefault();
		String url = "http://114.55.96.155:8889/WFGl/control?" + sb.toString() + "cp=" + cp + "&sp=" + sp;
		System.out.println(url);
		HttpGet get = new HttpGet(url);
		try {
			client.execute(get);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void redirect2(String sp, String cp, String linkid,
			String price, String result, String content,String orderid) {



		try {
			HttpClient client = HttpClients.createDefault();
			String url = "http://114.55.96.155:8889/WFGl/ForwardServet?" + content;
			HttpGet get = new HttpGet(url);
			get.setHeader("header_sp", sp);
			get.setHeader("header_cp", cp);
			get.setHeader("header_linkid", linkid);
			get.setHeader("header_price", price);
			get.setHeader("header_result", result);
			get.setHeader("header_orderid", orderid);

			String re = IOUtils.toString(client.execute(get).getEntity().getContent());
			System.out.println(re);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String message =   ",GET," +sp + "," + cp + "," + linkid + "," + price + "," + result + "," + content;
		logger.info(message);

	}
	public static void redirectPost(String sp, String cp, String linkid,
			String price, String result, String content) {

		try {
			HttpClient client = HttpClients.createDefault();
			String url = "http://114.55.96.155:8889/WFGl/ForwardServet?" + content;
			HttpGet get = new HttpGet(url);
			get.setHeader("header_sp", sp);
			get.setHeader("header_cp", cp);
			get.setHeader("header_linkid", linkid);
			get.setHeader("header_price", price);
			get.setHeader("header_result", result);
			get.setHeader("header_method", "POST");

			String re = IOUtils.toString(client.execute(get).getEntity().getContent());
			System.out.println(re);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String message =  ",POST," +sp + "," + cp + "," + linkid + "," + price + "," + result + "," + content;
		logger.info(message);

	}

	public static String sendSDKData(String url,Integer ratio,String linkid,String channel,Integer price,Byte result){
		try {
			int random = RandomUtils.nextInt(100);
			if(random >= ratio){
				String urlReal = url+"?linkid=" + linkid + "&channel=" + channel
						+ "&price=" + price + "&result=" + result;
				String re = HttpUtil.doGet(urlReal);
				logger.info(urlReal);
				return re;
			}

		} catch (Exception e) {
		}
		return "";
	}



}
