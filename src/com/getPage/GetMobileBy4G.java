package com.getPage;

import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;

public class GetMobileBy4G {
	public static void main(String[] args) {
		try {
			Connection.Response res = Jsoup.connect("http://mail.10086.cn/")
					.header("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1")
					.header("Upgrade-Insecure-Requests", "1")
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
					.header("Accept-Encoding", "gzip, deflate, sdch")
					.header("Accept-Language", "zh-CN,zh;q=0.8")
					.header("Host", "mail.10086.cn")
					.header("Cache-Control", "no-cache")
					.header("Connection", "keep-alive")
					.header("Pragma", "no-cache")
				    .method(Method.GET)
				    .execute();

			Map<String,String> cookieMap= res.cookies();
			Map<String,String> headerMap = res.headers();
			int status = res.statusCode();
			String url = res.url().toString();
			System.out.println(url);
			System.out.println("status:"+status);
			System.out.println(cookieMap);
			System.out.println(headerMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
