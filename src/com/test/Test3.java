package com.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test3 {
	  public static void main(String[] args) {
	        String s = "{\"error_code\":\"0\",\"guid\":\"36e8b521a54e11e69ee952540058cbe3\",\"smsregport\":\"10658422\",\"smsorderport\":\"1065842232\",\"smsreg\":\"QlVCQFR8ODggIDg4IDg4ODg4ODg4QDVVZk1YNmFoODJYT0ZANDhXQTYyQU5AQDAxMjM2OV9ldmFzZWd2QEA2ODc4MDU1OTg5NzA2QDg4NEA1Mjk0OS43MDQuQDg0NzQ2ODUzODAwNDM5NkA4MDA3NUA0MTU5ODY4ODEyODM0\",\"smsorder\":\"QUUyMDAwNTkwazl6NjE4KS96Mz41MDdbODAiNjBiMiBxMDBvNXM5ZzRsU0JAWlB5aG0gWzxUcEs6TWJtRXFDZD1yak51VnZvZHc1c2daPWRRMjAxQTExMFVEITU2NFlca3stMGJzMDAwPzBNSWxtVCVBNHdMWWNMbXBtN3RhY0lSMzA1dU9UST0=\",\"cpparam\":\"NJ12iv8sdpzxfyYr\",\"msgID\":0}";
	        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	        Map<String, String> map = null;
	        Pattern p = Pattern.compile("(error_code\"):(\"[^\"]+\")");
	        Matcher m = p.matcher(s);
	        String[] strs = null;
	        while (m.find()) {
	        	System.out.println(m.group());
	            strs = m.group().split(":");
	            if (strs.length == 2) {
	                map = new HashMap<String, String>();
//	              System.out.println(_strs[0].replaceAll("\"", "").trim() + "="
//	                      + _strs[1].trim().replaceAll("\"", ""));
	                map.put(strs[0].replaceAll("\"", "").trim(), strs[1].trim()
	                        .replaceAll("\"", ""));
	                list.add(map);
	            }

	        }
	         System.out.println(list.get(0));
	    }
}
