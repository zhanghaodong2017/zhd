package com.zuidiao;

import java.util.Date;

public class TimeUtils {
	public static boolean isOk(){
		int hour = new Date().getHours();
		if(hour < 23 || hour > 6){
			return true;
		}else{
			return false;
		}

	}
}
