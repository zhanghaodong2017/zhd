package com.htest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.test.RedirectUtil;

public class GetSDKTest {

	public static void main(String[] args) throws Exception {
		String line="useragent=Mozilla/5.0 (Linux; U; Android 4.1.2; zh-cn; GT-I9300 Build/JZO54K) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30&roleid=da4238338e9640398dd810e9f2ebd6f7&verify=1988713069&imei=354156052640820&iccid=89860115871029855399&buildkey=samsung_samsung_GT-I9300&appid=000181&sc=15527488670&chid=000733&isright=1&ip=221.234.171.27&distro=G032&wifi=true&price=10&pro=21&carrier=U&imsi=460017482403094&";

			String price = "";
			String pro = "";
			JSONObject json = new JSONObject();
			String[] strParam = line.split("&");
			for (int i = 0; i < strParam.length; i++) {
				if (strParam[i].contains("=")) {
					String[] keyValue = strParam[i].split("=");
					if (keyValue[0].equals("appid")
							|| keyValue[0].equals("imei")
							|| keyValue[0].equals("iccid")
							|| keyValue[0].equals("imsi")
							|| keyValue[0].equals("distro")
							|| keyValue[0].equals("useragent")
							|| keyValue[0].equals("carrier")
							|| keyValue[0].equals("ip")
							|| keyValue[0].equals("appid")
							|| keyValue[0].equals("appid")) {
						json.put(keyValue[0], keyValue[1]);
					}else if(keyValue[0].equals("sc")){
						json.put("tel", keyValue[1]);
					}else if(keyValue[0].equals("roleid")){
						json.put("userId", keyValue[1]);
					}else if(keyValue[0].equals("price")){
						price = keyValue[1];
					}else if(keyValue[0].equals("pro")){
						pro = keyValue[1];
					}

				}
			}
			json.put("appver", "1.0.0");
			json.put("model", "SM-N9005");
			json.put("mac", "D0:22:BE:48:1C:F4");
			json.put("androidversion", "4.6");
			json.put("screen", "1080*1920");

			try {
					String url="http://localhost:8080/SdkServer/cmd/getcmd?data="+URLEncoder.encode(json.toString(), "UTF-8")+"&price="+price;
					System.out.println(url);
					String re = HttpUtil.doGet(url);
					System.out.println(re);
					saveMsg("result.txt", url +"******"+re);

			} catch (Exception e) {
				e.printStackTrace();
			}



		System.out.println("success");

	}
	public static void saveMsg(String fileName,String msg) {
		try {
			File file = new File("E:\\NewSdkServerTest", fileName);
			if(!file.exists()){
				file.createNewFile();
			}
			FileOutputStream out = new FileOutputStream(file, true);
			out.write((msg+"\r\n\r\n").getBytes());
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
