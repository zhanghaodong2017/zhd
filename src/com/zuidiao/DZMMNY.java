package com.zuidiao;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import com.yace.OrderIdUtils;

public class DZMMNY {

	public static void getCmd(String tel, String imsi, String imei) {
		try {
			String cpparam = "10e" + OrderIdUtils.getOrderId(14);
			// String phone = params.get("tel");
			// String imei = params.get("imei");
			// String imsi = params.get("imsi");

		/*	String url = "http://203.88.197.167:9963/ctmmfee/nydxmm.do?pid=90215876&imsi="
					+ imsi
					+ "&imei="
					+ imei
					+ "&phone="
					+ tel
					+ "&cpparam="
					+ cpparam;*/


			String url = "http://203.88.197.167:9973/ctmmfee/open189.do?pid=90215311&imsi="+imsi+"&imei="+imei+"&phone="+tel+"&cpparam="+cpparam;


			// 第一步订单生成
			HttpClient client = HttpClients.createDefault();
			String re = "";

			re = IOUtils.toString(client.execute(new HttpGet(url)).getEntity()
					.getContent());
			saveMsg("dianxin.txt", url);
			saveMsg("dianxin.txt", re);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void saveMsg(String fileName, String msg) {
		try {
			File file = null;
			String osName = System.getProperty("os.name");
			if (osName != null && osName.equals("Linux")) {
				file = new File("/root/" + fileName);
			} else {
				file = new File("E:\\", fileName);
			}
			FileOutputStream out = new FileOutputStream(file, true);
			out.write((msg + "\r\n").getBytes());
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
