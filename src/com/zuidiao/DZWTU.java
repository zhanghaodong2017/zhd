package com.zuidiao;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import com.yace.OrderIdUtils;

public class DZWTU {

	public static void getCmd(String tel, String imsi, String imei) {
		String cpparam = "10e" + OrderIdUtils.getOrderId(14);

		String url = "http://203.88.197.167:9963/fd/wtyzmopen.do?pid=90217473&imsi="+imsi+"&imei="+imei+"&phone="+tel+"&cpparam=15d";


		// 第一步订单生成
		HttpClient client = HttpClients.createDefault();
		String re = "";
		try {
			re = IOUtils.toString(client.execute(new HttpGet(url)).getEntity()
					.getContent());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
