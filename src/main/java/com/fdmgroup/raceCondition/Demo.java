package com.fdmgroup.raceCondition;

public class Demo {

	public static void main(String[] args) {
		Account account = new Account();
		
		AccountRunnable runnable1 = new AccountRunnable(account);
		AccountRunnable runnable2 = new AccountRunnable(account);
		
		Thread thread0 = new Thread(runnable1);
		Thread thread1 = new Thread(runnable2);
		
		thread0.start();
		thread1.start();
		

	}

}
