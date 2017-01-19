package com.thread.tb;

public class TongBu {
	public static void main(String[] args) {
		Bank bank = new Bank();
		for (int i = 0; i < 100; i++) {
			BankTest test =new BankTest(bank);
			Thread thread =new Thread(test);
			thread.start();
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(bank.getAccount());

	}
}


class BankTest implements Runnable {

	private Bank bank;

	public BankTest(Bank bank) {
		super();
		this.bank = bank;
	}

	@Override
	public void run() {
		bank.add(10);
	}

}