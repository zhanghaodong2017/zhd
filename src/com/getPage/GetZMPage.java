package com.getPage;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class GetZMPage {


	public void  login(){
		try {
			Connection.Response res = Jsoup.connect("http://sdk.zmapp.com/sdkData/LoginSubmit.do;jsessionid=E438FFA63873BFB2680ABA9039381A9A")
				    .data("username", "cp_lemi", "password", "123456")
				    .method(Method.POST)
				    .execute();

				Document doc = res.parse();
				System.out.println(doc.toString());
				//这儿的SESSIONID需要根据要登录的目标网站设置的session Cookie名字而定
				String sessionId = res.cookie("SESSIONID");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
