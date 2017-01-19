package com.yace;

import java.net.URLEncoder;

import com.htest.HttpUtil;


public class SdkTask implements Runnable{
	private String data;


	public SdkTask(String data) {
		super();
		this.data = data;
	}
	@Override
	public void run() {
		String url="http://118.178.116.121:9999/SdkServer/register/register?data="+URLEncoder.encode(data);
		try {
			String back = HttpUtil.doGet(url);
			System.out.println(back);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
