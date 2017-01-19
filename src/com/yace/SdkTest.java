package com.yace;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SdkTest {
	public static void main(String[] args) {
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(20);

		List<String> list = FeeUtils.getRegist();
		//33122
		int index =0;
		for (String data : list) {
			fixedThreadPool.execute(new SdkTask(data));
			index++;
		}
		System.out.println(index);
	}
}
