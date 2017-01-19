package com.htest;

import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

public class NewSDKTest_2 {

	public static void main(String[] args) throws Exception {
		String fm = "ZMXDMSPID122";
		String price = "10";
		String data = "{\"userId\":\"4j2ei82veo009acdd101d420161019\",\"appid\":\"000181\",\"imei\":\"868090024198543\",\"iccid\":\"89860002022200158336\",\"imsi\":\"460029220158336\",\"distro\":\"C0001\",\"appver\":\"1.0.0\",\"useragent\":\"Mozilla/5.0 (Linux; Android 4.4.2; CHM-TL00H Build/HonorCHM-TL00H) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/30.0.0.0 Mobile Safari/537.36\",\"model\":\"CHM-TL00H\",\"mac\":\"00:9a:cd:d1:01:d4\",\"tel\":\"15922051814\",\"carrier\":\"M\",\"androidversion\":\"19\",\"pro\":\"7\",\"ip\":\"111.165.1.192\",\"screen\":\"720*1280\",\"telType\":\"3\",\"lac\":\"\",\"cid\":\"\"}";
		String url = "http://118.178.116.121:9999/SdkServer/singlecmd/getCmd?fm="
				+ fm + "&price" + price + "&data="+URLEncoder.encode(data) ;
		HttpGet get = new HttpGet(url);
		HttpClient client = HttpClients.createDefault();
		HttpResponse response = client.execute(get);
		String result = IOUtils.toString(response.getEntity().getContent());
		System.out.println(URLDecoder.decode(result));
	}

}
