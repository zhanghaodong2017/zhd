package com.thread.tb;

import java.util.concurrent.locks.ReentrantLock;

public class Bank {
	private int account=10000;

	private ReentrantLock lock = new ReentrantLock();

	public int getAccount(){
		return account;
	}
	public void add(int money){
		lock.lock();
		try {
			Thread.sleep(5);
			account = account + money;
			Thread.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}
