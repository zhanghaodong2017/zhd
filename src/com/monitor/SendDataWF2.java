package com.monitor;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import com.test.RedirectUtil;

public class SendDataWF2 {
	public static void main(String[] args) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("linkid", "28e93976785e6771f0767260e93901bb");
		params.put("result", "0");
		params.put("price", "6");
		params.put("mobile", "15607006949");
		params.put("extparams", "W119T8SS");
		RedirectUtil.redirect("LMZYLT", "AH", params);
	}
}
