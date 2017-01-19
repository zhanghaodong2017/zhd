package com.codebuilding;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

public class ProBuilding {
	public static void main(String[] args) throws Exception {

		File testFile = new File(
				"E:\\JoymetecStudio_work\\day0628\\src\\com\\codebuilding",
				"pro.txt");
		BufferedReader buffReader = new BufferedReader(new InputStreamReader(
				new FileInputStream(testFile)));

		String str = null;
		StringBuilder sb = new StringBuilder();
		sb.append("switch (key) {\n");
		while ((str = buffReader.readLine()) != null) {
			String[] pros = str.split("	");
			sb.append("case \"" + pros[0] + "\":\n location=\"" + pros[1]
					+ "\";\n break;\n");
		}
		sb.append("}");
		System.out.println(sb.toString());

	}
}
