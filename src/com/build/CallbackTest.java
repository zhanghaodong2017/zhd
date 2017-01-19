package com.build;

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

import com.lemi.utils.HttpUtil;

public class CallbackTest {
	public static void main(String[] args) throws Exception {

      String url="http://localhost:8080/CallbackServer/callback/YYJ";
//      String url="http://121.41.7.235:9494/CallbackServer/callback/ZHQY547";

      String params ="{\"body\":{\"appName\":\"精品小说章节购买0.1元\",\"channel\":\"3001231\",\"columnId\":40409,\"date\":\"2016-11-0417:37:45\",\"errorCode\":\"\",\"feeType\":\"02\",\"name\":\"超品高手\",\"orderId\":\"\",\"orderResult\":\"0\",\"orderType\":\"0\",\"subPrice\":0.1,\"transactionId\":\"WABP2016110411475929\",\"userNumber\":\"8618801232237\"}}";

      String back = HttpUtil.doPostByString(url, params);
      System.out.println(back);

	}
}
