package com.codebuilding;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

public class DemoBuilding {
	public static void main(String[] args) throws Exception {
		File testFile = new File("E:\\Wrork4\\day0628\\src\\com\\codebuilding","params.txt");
		BufferedReader buffReader = new BufferedReader(new InputStreamReader(
				new FileInputStream(testFile)));

		String str = null;
		StringBuilder sb =new StringBuilder();
		StringBuilder sb2 =new StringBuilder();
		while ((str = buffReader.readLine()) != null) {
//			System.out.println("String "+str +" = json.getString(\""+str+"\");");
			System.out.println("String "+str +" = params.get(\""+str+"\");");
//			System.out.println("String "+str +" =req.getParameter(\""+str.trim()+"\");");
//			System.out.println("String "+str +" =params.get(\""+str.trim()+"\");");
//			System.out.println("map.put(\""+str+"\", "+str+");");
			if(sb.length() == 0){
				sb.append("\"");
				sb.append(str);
				sb.append("=\"+");
				sb.append(str);
			}else{
				sb.append("+\"&");
				sb.append(str);
				sb.append("=\"+");
				sb.append(str);
			}
			sb2.append(str+"=");
		}
		System.out.println();
		System.out.println(sb.toString());

//		System.out.println(sb2.toString());
	}
}
