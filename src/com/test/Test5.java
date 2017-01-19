package com.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.utils.URLEncodedUtils;

public class Test5 {
	public static void main(String[] args) throws Exception {
		String url="http://www.hztentown.com/pay/createTtOrder.ttws?clientip=116.55.39.225&ext=13-6364903&iccid=89860068011550027758&imei=869922027585541&imsi=460027149855612&monthly=0&netinfo=WIFI&od=1467892423595&osinfo=4.1&osmodel=HUAWEi&price=1000&sp=lemi1&tel=15287102579&time=1467892423595&uid=|ipxcyf8v&sign=D1F5E639096E057D8C72EEFAC9D39876";
		char[] ch = url.toCharArray();
		System.out.println(ch[280]);
	}
}
