package com.nio;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {

	public static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

	public static ExecutorService getInstance() {
		return cachedThreadPool;
	}

}
