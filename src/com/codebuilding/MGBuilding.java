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

public class MGBuilding {
	public static void main(String[] args) throws Exception {
		File testFile = new File("E:\\Wrork4\\day0628\\src\\com\\codebuilding",
				"mg.txt");
		BufferedReader buffReader = new BufferedReader(new InputStreamReader(
				new FileInputStream(testFile)));

		String str = null;
		StringBuilder sb = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		Comparator<String> comparator = new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return Integer.valueOf(o2) - Integer.valueOf(o1);
			}
		};
		TreeMap<String, Integer> map = new TreeMap<String, Integer>();
		List<String> mglist = new ArrayList<>();
		while ((str = buffReader.readLine()) != null) {
			mglist.add(str);
		}

		for (String mg : mglist) {
//			System.out.println("'"+mg+"',");
			String[] param = mg.split(",");
//			System.out.println("case \""+param[1]+"\":");
//			System.out.println("feename=\""+param[2]+"\";");
//			System.out.println("payfee= \""+param[0]+"\";");
//			System.out.println("break;");
			/*
			 * case "006095246006":
					reqc.setPrice(8);
					reqc.setFm("MGHZMP");
					break;*/
			System.out.println("case \""+param[0]+"\":");
			System.out.println("reqc.setPrice("+Integer.valueOf(param[1])+");");
//			System.out.println("reqc.setFm(\"MGHLXD\");");
			System.out.println("break;");
		}

	}
}
