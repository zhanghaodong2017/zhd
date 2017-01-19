package com.codebuilding;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

public class ZYBuilding {
	public static void main(String[] args) throws Exception {
		File testFile = new File("E:\\Wrork4\\day0628\\src\\com\\codebuilding",
				"zy.txt");
		BufferedReader buffReader = new BufferedReader(new InputStreamReader(
				new FileInputStream(testFile)));

		String str = null;
		List<String> mglist = new ArrayList<>();
		while ((str = buffReader.readLine()) != null) {
			mglist.add(str);
		}
		String url="http://114.55.52.145:8588/synInfo/lmacyInfo.Syn";
		int index=0;
		for (String mg : mglist) {
			index++;
			HttpClient client = HttpClients.createDefault();
			 HttpGet get = new HttpGet(url+"?"+mg);
			 String result =IOUtils.toString(client.execute(get).getEntity().getContent());
			 System.out.println(result);
			 System.out.println(index);
		}

	}
}
