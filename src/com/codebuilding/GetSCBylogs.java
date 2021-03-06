package com.codebuilding;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import net.sf.json.JSONObject;


public class GetSCBylogs {
	public static void main(String[] args) throws Exception {
		File testFile = new File("E:\\Wrork4\\day0628\\src\\com\\codebuilding",
				"logs.txt");
		File hlxd = new File("E:\\Wrork4\\day0628\\src\\com\\codebuilding",
				"hlxd.txt");
		File mobileFile = new File("E:\\Wrork4\\day0628\\src\\com\\codebuilding",
				"mobile.txt");
		BufferedReader buffReader = new BufferedReader(new InputStreamReader(
				new FileInputStream(testFile)));
		BufferedReader mobileReader = new BufferedReader(new InputStreamReader(
				new FileInputStream(mobileFile)));
		BufferedWriter bufferedWriter = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(hlxd, true)));
		String str = null;
		List<String> mglist = new ArrayList<>();
		List<String> imsisclist = new ArrayList<>();
		List<String> mobilelist = new ArrayList<>();
		while ((str = mobileReader.readLine()) != null) {
			mobilelist.add(str.trim());
		}

		Map<String, String> mapsc = new HashMap<>();
		while ((str = buffReader.readLine()) != null) {
			String[] log = str.split("	");
			String[] param = log[0].split("&");
			String imsi = "";
			String extparams="";
			for (int i = 0; i < param.length; i++) {
				if (param[i].contains("=")) {
					String[] keyvalue = param[i].split("=");
					if (keyvalue[0].equals("imsi")) {
						imsi = keyvalue[1];
					}
					if (keyvalue[0].equals("extparams")) {
						extparams = keyvalue[1];
					}
				}

			}
			String sc = ImsiUtils.getMobileAll(imsi);
			if(sc !=null && !sc.equals("")){
				for (String mobile : mobilelist) {
					if( mobile.contains(sc)){
						System.out.println(mobile+","+imsi+","+sc+","+extparams);
					}
				}
			}


		}



//		bufferedWriter.write(mobile);
//		bufferedWriter.newLine();
//		buffReader.close();

/*		for (String mg : mglist) {
			String[] param = mg.split("&");
			String sc = "";
			String distro = "";
			for (int i = 0; i < param.length; i++) {
				if (param[i].contains("=")) {
					String[] keyvalue = param[i].split("=");
					if (keyvalue[0].equals("sc")) {
						sc = keyvalue[1];

					}

					if (keyvalue[0].equals("distro")) {
						distro = keyvalue[1];

					}

				}
			}
			bufferedWriter.write(sc);
			bufferedWriter.newLine();

		}*/

		bufferedWriter.flush();
		bufferedWriter.close();
		System.out.println("success");

	}
}
