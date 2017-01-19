package com.codebuilding;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

public class SkyMonitor {
	public static void main(String[] args) throws Exception {
		 HttpClient client = HttpClients.createDefault();
		 String timestamp= System.currentTimeMillis()+"";
		 String token="md5(app_key+order_no+timestamp)";
		 String url="http://pay.tyread.com/query_order.json?"
		 		+ "app_key=0cf451fb033746a9ae2ec8bf4019a9f2"
		 		+ "&order_no=1GvG5P9b0Hg72sSK"
		 		+ "&timestamp="+timestamp+"&token="+token;
		 HttpGet get = new HttpGet(url);
		 HttpResponse response = client.execute(get);
		 String result = IOUtils.toString(response.getEntity().getContent());
		 System.out.println(result);
	}
}
