package com.http;

import java.io.File;
import java.io.FileInputStream;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HttpTest {
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		File file =new File("E:\\Wrork4\\day0628\\src\\com\\http","entity.txt");
		FileInputStream fis =new FileInputStream(file);
		String re = IOUtils.toString(fis);
		String entity = re.replace("\n", "").replace("\r", "").replace(" ", "");
		System.out.println(entity);
		String url = "http://localhost:8080/bsp/WxpayHandlerServlet";
		/*HttpClientUtils http = new HttpClientUtils();
		String result = http.get(url, entity);
		System.out.println(result);*/
		HttpPost post  =new HttpPost(url);
		post.setEntity(new StringEntity(re));
		HttpClient client = HttpClients.createDefault();
		HttpResponse response  = client.execute(post);
		String str =IOUtils.toString(response.getEntity().getContent());
		System.out.println(str);

	}
}
