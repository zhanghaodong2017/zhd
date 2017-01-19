package com.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Test4 {
	public static void main(String[] args) throws Exception {

		File testFile = new File("E:\\JoymetecStudio_work\\day0628\\src","aa.txt");
		BufferedReader buffReader = new BufferedReader(new InputStreamReader(
				new FileInputStream(testFile)));

		String str = null;
		while ((str = buffReader.readLine()) != null) {
			String[] param = str.split(",");
//			System.out.println(param[0]+param[1]);
			System.out.println("case \"" +param[1] +"\": provincecode= \""+ param[0] +"\"; break;");
		}


	}
}
