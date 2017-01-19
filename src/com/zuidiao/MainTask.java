package com.zuidiao;

import java.util.Timer;


public class MainTask {
	public static void main(String[] args) {
		try {
			/*Calendar calendar4 =Calendar.getInstance();
			calendar4.set(2015, 7, 31, 0, 0, 0);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println(sdf.format(calendar4.getTime()));*/
			Timer timer = new Timer();
			timer.schedule(new DZCNWTask(),0);
			timer.schedule(new DZWTUTask(),0);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
