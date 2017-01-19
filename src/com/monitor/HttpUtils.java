package com.monitor;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

public class HttpUtils {
	public static void  doGet(String url){
		try {
			HttpGet get = new HttpGet(url);
			HttpClient client = HttpClients.createDefault();
			client.execute(get);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			String content="resultCode=10&cpPara=SV4VIEBGYW&phone=18078818192&fee=1000&timeStamp=1471938598188&";
			HttpClient client = HttpClients.createDefault();
			String url = "http://localhost:8080/WFGl/ForwardServet?" + content;
			HttpGet get = new HttpGet(url);
			get.setHeader("header_sp", "ACY");
			get.setHeader("header_cp", "ACYZY");
			get.setHeader("header_linkid", "1471938598188");
			get.setHeader("header_price", "10");
			get.setHeader("header_result", "0");

			String re = IOUtils.toString(client.execute(get)
					.getEntity().getContent());
			System.out.println(re);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
