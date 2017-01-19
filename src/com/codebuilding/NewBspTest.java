package com.codebuilding;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONObject;
import net.spy.memcached.MemcachedClient;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.htest.HttpUtil;

public class NewBspTest {
	public static MemcachedClient cache = Memcaches.getMem();
	public static void main(String[] args) throws Exception {

/*		String url = "http://121.41.7.235:9494/CallbackServer/custom/LTXW";
		StringEntity entity = new StringEntity(getXML(), "utf-8");
		HttpPost post = new HttpPost(url);
		post.setEntity(entity);
		HttpClient client = HttpClients.createDefault();
		HttpResponse response = client.execute(post);
		String result = IOUtils.toString(response.getEntity().getContent());

		System.out.println(result);*/
		/*String url = "http://localhost:8080/CallbackServer/callback/BJZZHRTSRDO?"
				+ "orderid=test1231231&price=0&mobile=11111111111";*/
		String url="http://121.41.7.235:9494/CallbackServer/callback/YYJ?orderid=test1231231&price=0&mobile=11111111111";
		cache.set("ordernofm-test1231231", 120, "BJZZHRTSRDO");
		cache.set("ordernoappid-test1231231", 120, "123456");
		String back = HttpUtil.doGet(url);
		System.out.println(back);

	}

	public static String getJson() {

		JSONObject json = new JSONObject();
		json.put("orderid", "000000000000160902113707LI06isl7lwa7");
		json.put("cpid", "86009070");
		json.put("appid", "9012009938620160816174243239100");
		json.put("fid", "00031472");
		json.put("consumeCode", "160816554273");
		json.put("payfee", "2000");
		json.put("hRet", "0");
		json.put("status", "0000");

		return json.toString();
	}

	public static String getXML() {
		Element root = DocumentHelper.createElement("callbackReq");
		Document document = DocumentHelper.createDocument(root);

		// 给根节点添加内容
		root.addElement("orderid").addText(
				"000000000000160902113707LI06isl7lwa6");
		root.addElement("cpid").addText("86009070");
		root.addElement("appid").addText("9012009938620160816174243239100");
		root.addElement("fid").addText("00031472");
		root.addElement("consumeCode").addText("160816554273");
		root.addElement("payfee").addText("100");
		root.addElement("hRet").addText("0");
		root.addElement("status").addText("0000");

		return document.asXML();
	}

	public static UrlEncodedFormEntity getText() throws UnsupportedEncodingException {

		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair("orderid","000000000000160902113707LI06isl7lwa8"));
		parameters.add(new BasicNameValuePair("cpid","86009070"));
		parameters.add(new BasicNameValuePair("appid","9012009938620160816174243239100"));
		parameters.add(new BasicNameValuePair("fid","00031472"));
		parameters.add(new BasicNameValuePair("consumeCode","160816554273"));
		parameters.add(new BasicNameValuePair("payfee","2000"));
		parameters.add(new BasicNameValuePair("hRet","0"));
		parameters.add(new BasicNameValuePair("status","0000"));
		UrlEncodedFormEntity formEntiry = new UrlEncodedFormEntity(parameters);
		return formEntiry;
	}

	public static String getText2() {
		String result="";
		JSONObject json = new JSONObject();
		json.put("orderid", "000000000000160902113707LI06isl7lwa2");
		json.put("cpid", "86009070");
		json.put("appid", "9012009938620160816174243239100");
		json.put("fid", "00031472");
		json.put("consumeCode", "160816554273");
		json.put("payfee", "2000");
		json.put("hRet", "0");
		json.put("status", "0000");
		Iterator<String> it = json.keys();
		while(it.hasNext()){
			String key = it.next();
			String value= json.getString(key);
			result += key+"="+value+"&";
		}


		return result;
	}
}
