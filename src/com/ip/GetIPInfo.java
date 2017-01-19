package com.ip;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class GetIPInfo {
	public static void main(String[] args) throws Exception {
		 List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		 parameters.add(new BasicNameValuePair("ip", "153.37.218.53"));
		 String url = "http://ip.chinaz.com/";
		 HttpClient httpclient = HttpClients.createDefault();
		 HttpPost post = new HttpPost(url);
		 UrlEncodedFormEntity formEntiry = new UrlEncodedFormEntity(parameters);
		 post.setEntity(formEntiry);
		 HttpResponse response = httpclient.execute(post);
		 String html = IOUtils.toString(response.getEntity().getContent());
		 Document document = Jsoup.parse(html, "gbk");
//		 System.out.println(document.text());
		 Elements divs = document.select("span[class=Whwtdhalf w50-0]");
		 System.out.println(divs.get(1).text());
	}
}
