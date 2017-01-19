package com.html;

public class MainTest {
	public static void main(String[] args) {
		String name="is_delete";
		int _index = name.indexOf("_");
		String str =name.substring(_index+1, _index+2);
		String abc=name.replace("_"+str, str.toUpperCase());
		System.out.println(abc);
	}
}
