package com.monitor;

import java.util.Timer;
import java.util.TimerTask;

public class MonitorLTXW {
	public static void main(String[] args) {
		Timer timer = new Timer();
		LTXWTask task = new LTXWTask();
		timer.schedule(task, 0, 1000*60*2);
	}
}
