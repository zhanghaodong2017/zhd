package com.test;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

import com.lemi.utils.JoyUtils;

public class HttpTest {
	public static void main(String[] args) throws Exception {
		String url = "http://127.0.0.1:8080/WFGl/ForwardServet?"
				+"message="+"message"
				+"&orderid="+"orderid"
				+"&spnumber="+"abcx"
				+"&mobile="+"abcx"
				+"&tradeid="+"abcx"
				+"&channelid="+"abcx"
				+"&actiontime="+"abcx"
				+"&reqsid="+"abcx"
				+"&price="+"1000";

		HttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet(url);
		get.setHeader("header_sp", "MGZHHHHH");
		get.setHeader("header_cp", "abc");
		get.setHeader("header_linkid", "123");
		get.setHeader("header_price", "10");
		get.setHeader("header_result", "0");

		String re = IOUtils.toString(client.execute(get)
				.getEntity().getContent());
		if(re == null || !re.equals("ok")){
			String msg = "abc" + "," + "abc" + "," + "123" + "," + "10" + "," + "0" + "," + url;
			JoyUtils.saveMsg("forward.txt", msg);
		}

	}
}
