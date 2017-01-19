package com.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Test2 {
	public static void main(String[] args) throws Exception {
		List<String> list =new ArrayList<>();
		File file = new File("D:\\Joymetec\\sdk-server\\src\\com\\lemi\\code");
		File[] fs =file.listFiles();
		for (File file2 : fs) {
			list.add(file2.getName().replace(".java", ""));
		}
		//
		File testFile = new File("D:\\Joymetec\\sdk-server\\src","test.txt");
		BufferedReader buffReader = new BufferedReader(new InputStreamReader(new FileInputStream(testFile)));

		String str =null;
		while((str = buffReader.readLine()) != null){
			if(!list.contains(str)){
				System.out.println(str);
			}
		}
	}
}
