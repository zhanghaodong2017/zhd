package com.arvo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.sun.jndi.toolkit.url.UrlUtil;


public class ArvoTest {

	// 调用外网Internet上JS文件
	public static void interJSFuntion() throws ScriptException, NoSuchMethodException {
		// 构造一个脚本器
		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByName("JavaScript");

		String url = "http://localhost/Test";
		String html = "";//UrlUtil.getHtml(url);
		// 使用正则匹配js文件路径
		String js = "";//getTextZZ(html, "<script[\\s]*type=\"text/javascript\"[\\s]*src=[.&\\S]+>[\\s]*</script>");
//		js = url + UrlUtil.getTextZZ(js, "\\Q/\\E[.&\\S]+\\Q.js\\E");
		// 得到流
		Reader r = getReader(js, "utf-8");
		// 把Reader放入eval中(读者可以去API看一下,重载了很多的eval()方法)
		engine.eval(r);
		Invocable inv = (Invocable) engine;

		String value = (String) inv.invokeFunction("test", "我是一个参数");
		System.out.println(value);
	}

	// 得到Reader(使用HttpURLConnection得到外部js文件的Reader流)
	public static Reader getReader(String url, String code) {
		URL URL = null;
		HttpURLConnection conn = null;
		InputStream in = null;
		Reader br = null;
		try {
			URL = new URL(url);
			conn = (HttpURLConnection) URL.openConnection();
			// 设置get请求
			conn.setRequestMethod("GET");
			// 得到流
			in = conn.getInputStream();
			br = new InputStreamReader(in, code);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return br;
	}

	// 根据内容得到匹配
	public static String getTextZZ(String Text, String zz) {
		Pattern p = Pattern.compile(zz);
		Matcher m = p.matcher(Text);
		String group = "";
		while (m.find()) {
			group = m.group();
		}
		return group;
	}

	// 得到HTML
	public static String getHtml(String url) {
		URL URL = null;
		HttpURLConnection conn = null;
		InputStream in = null;
		BufferedReader br = null;
		String html = null;
		try {
			URL = new URL(url);
			conn = (HttpURLConnection) URL.openConnection();
			conn.setRequestMethod("GET");
			String code = "";//getCode(conn.getContentType());
			in = conn.getInputStream();
			br = new BufferedReader(new InputStreamReader(in, code));
			// 得到HTML文档
			String data = "";
			while ((data = br.readLine()) != null) {
				html += data + "\n";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				br.close();
				in.close();
				conn.disconnect();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return html;
	}
}
