package com.tasktest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;


public class TaskTest {
	public static void main(String[] args) {
		try {
			Timer timer2 = new Timer();
			Calendar date = Calendar.getInstance();
			date.set(Calendar.HOUR, 24);
			date.set(Calendar.SECOND, 0);
			date.set(Calendar.MINUTE, 1);


			SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println(sdf.format(date.getTime()));
			timer2.schedule(new RefreshTask(), date.getTime() ,5000);
		} catch (Exception e) {

		}
	}
}
