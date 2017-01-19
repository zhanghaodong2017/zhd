package com.monitor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.test.RedirectUtil;

public class SendData_2 {

	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("ZY9PDKYDI5");
		list.add("ZYLW0CEJCM");
		list.add("ZYW7KTH0VU");

		int index=0;
		for (String str : list) {
			Map<String, String> params = new HashMap<String, String>();
			params.put("linkid", "szlm"+(System.currentTimeMillis()+index*123));
			params.put("result", "0");
			params.put("price", "10");
			params.put("mobile", "");
			params.put("extparams", str);
			System.out.println(params.get("linkid"));
			RedirectUtil.redirect("GZLQTSRDO", "88", params);
			index++;
		}
		System.out.println(index
				);

	}
}
