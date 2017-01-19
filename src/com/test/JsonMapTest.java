package com.test;


import it.sauronsoftware.base64.Base64;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

public class JsonMapTest {
	public static void main(String[] args) throws Exception {
		Map<String, String> map =new HashMap<>();
		map.put("sid", "6970");
		map.put("sms", "Base64Encode(sms1)& Base64Encode(sms2)");
		map.put("port", "1065843601&1065842230");
		map.put("req", "Base64Encode(sms1)& Base64Encode(sms2)");
		map.put("orderno", "CT48625684957176");

		JSONObject json =JSONObject.fromObject(map);

		String encoded = Base64.encode("Hello, world!");// 编码
		System.out.println(Base64.decode(encoded));//解码
	}
}
