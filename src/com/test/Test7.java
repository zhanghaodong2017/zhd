package com.test;

import it.sauronsoftware.base64.Base64;

public class Test7 {
	public static void main(String[] args) throws Exception {
		String msg ="ISY+TGB8JitdJk5CeVd5ZXFRJit1JiEmKClucmBlcjMmODIwODIyMzQ1Njc4OTAxMjM0NTkmQmd8fGEu003d";
		String str = new String(Base64.decode(msg));
		System.out.println(str);
	}
}
