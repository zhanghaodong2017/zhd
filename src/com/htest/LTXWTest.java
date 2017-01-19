package com.htest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class LTXWTest {
	public static void main(String[] args) throws Exception {

//        String url="http://114.55.96.155:8888/WFsdk/mobile-submitNew2";
      String url="http://localhost:8080/WFsdk/mobile-submitNew2";
      String param ="sc=13898830337&appid=000700&imsi=460008853997428&price=10&extparams=123123";
      StringEntity str =new StringEntity(param);

//      HttpPost post = new HttpPost(url+"?"+param);
      HttpPost post = new HttpPost(url);
      post.setEntity(str);
      HttpGet get = new HttpGet(url+"?"+param);
      HttpClient client = HttpClients.createDefault();
      HttpResponse response = client.execute(get);
      String result = IOUtils.toString(response.getEntity().getContent());
	  System.out.println(result);
	}
}
