package com.http;

import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

public class HttpClientUtils {
	private String reqType;
	private String url;
	private String entity;

	public HttpClientUtils(){

	}
	public HttpClientUtils(String url, String entity) {
		this.url = url;
		this.entity = entity;
	}

	public HttpClientUtils(String reqType, String url, String entity) {
		this.reqType = reqType;
		this.url = url;
		this.entity = entity;
	}

	public String getResult() throws Exception {
		URL httpURL = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) httpURL.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setUseCaches(false);
		connection.setInstanceFollowRedirects(true);
		connection.setRequestMethod(reqType);
		connection.setRequestProperty("Content-Type", "text/xml;charset=utf-8");
		connection.connect();
		OutputStreamWriter out = new OutputStreamWriter(
				connection.getOutputStream(), "UTF-8");
		out.write(entity);
		out.flush();
		out.close();
		InputStream is = connection.getInputStream();
		String result = IOUtils.toString(is);
		return result;
	}


	public String get(String url, String entity) throws Exception{
		this.url = url;
		this.entity = entity;
		this.reqType ="GET";
		return getResult();
	}

	public String post(String url, String entity) throws Exception {
		this.url = url;
		this.entity = entity;
		this.reqType ="POST";
		return getResult();
	}

}
