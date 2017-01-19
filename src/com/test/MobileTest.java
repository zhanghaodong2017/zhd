package com.test;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

public class MobileTest {
	public static void main(String[] args) throws Exception {
		HttpClient client = HttpClients.createDefault();
		String url1 = "http://114.55.96.155:8888/sdk/mobile-submitNew2";
		String url2 = "http://153.37.218.54:8989/sdk/mobile-submitNew2";
		String url3 = "http://127.0.0.1:8080/sdk/mobile-submitNew2";
		HttpPost post = new HttpPost(url3);
		post.setEntity(new StringEntity("useragent=Mozilla/5.0 (Linux; Android 5.0; SM-N9005 Build/LRX21V; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/43.0.2357.121 Mobile Safari/537.36, roleid=|d98b9feb7dd84e2daf1f693b06eb83e6, verify=2066797727, imei=357507050007965, iccid=898600, buildkey=samsung_samsung_SM-N9005, sc=13010259500, appid=000839, chid=000840, isright=1, ip=153.37.218.54, distro=000null, wifi=true, price=1, pro=9, carrier=CUC, imsi=460012986012526"));
		String re = IOUtils.toString(client.execute(post).getEntity().getContent());
		System.out.println(re);
	}
}
