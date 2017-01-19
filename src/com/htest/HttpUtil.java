package com.htest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;

public class HttpUtil {

	private static PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
	private static HttpClient client = null;
	private static RequestConfig requestConfig = RequestConfig.custom()
			.setSocketTimeout(50000).setConnectTimeout(5000).build();// 设置请求和传输超时时间

	static{
		connManager.setMaxTotal(512);
		connManager.setDefaultMaxPerRoute(32);
		client = HttpClients.custom().setConnectionManager(connManager).build();
	}

	/*
	 * get方式访问url
	 */
	public static String doGet(String url) throws IllegalStateException,
			ClientProtocolException, IOException {
		HttpGet get = new HttpGet(url);
		get.setConfig(requestConfig);

		String re = IOUtils.toString(client.execute(get).getEntity()
				.getContent(),"UTF-8");
		get.releaseConnection();
		return re;
	}

	/*
	 * String类型的参数Post访问
	 */
	public static String doPostByString(String url, String params)
			throws IllegalStateException, ClientProtocolException, IOException {
		HttpPost post = new HttpPost(url);
		if (params != null) {
			StringEntity se = new StringEntity(params);
			post.setEntity(se);
		}

		post.setConfig(requestConfig);
		String re = IOUtils.toString(client.execute(post).getEntity()
				.getContent());
		post.releaseConnection();
		return re;
	}

	/*
	 * 表单形式的post访问
	 */
	public static String doPostByForm(String url, Map<String, String> params)
			throws IllegalStateException, ClientProtocolException, IOException {
		HttpPost post = new HttpPost(url);

		if (params != null && params.size() > 0) {
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();

			Set<String> sets = params.keySet();
			for (String s : sets) {
				nvps.add(new BasicNameValuePair(s, params.get(s)));
			}
			post.setEntity(new UrlEncodedFormEntity(nvps));
		}

		post.setConfig(requestConfig);
		String re = IOUtils.toString(client.execute(post).getEntity()
				.getContent());
		post.releaseConnection();
		return re;
	}



	public static HttpClient getClient() {
		return client;
	}

	public static void setClient(HttpClient client) {
		HttpUtil.client = client;
	}

}
