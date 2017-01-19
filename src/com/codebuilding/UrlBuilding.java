package com.codebuilding;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import org.apache.commons.lang.StringUtils;

public class UrlBuilding {
	public static void main(String[] args) throws Exception {
		File testFile = new File("E:\\Wrork4\\day0628\\src\\com\\codebuilding","params.txt");
		BufferedReader buffReader = new BufferedReader(new InputStreamReader(
				new FileInputStream(testFile)));

		String str = null;
		while ((str = buffReader.readLine()) != null) {
			if(StringUtils.isNotBlank(str)){
				System.out.println(str);
			}
		}
		System.out.println();
	}
}
