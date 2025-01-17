package com.fdmgroup.reentrantLock;

public class Demo {

	public static void main(String[] args) {
		Database databaseConnection = new Database();
		DatabaseRunnable databaseRunnable = new DatabaseRunnable(databaseConnection);
//		DatabaseRunnable databaseRunnable = new DatabaseRunnable(databaseConnection);
		
		Thread thread0 = new Thread(databaseRunnable);
		Thread thread1 = new Thread(databaseRunnable);
		Thread thread2 = new Thread(databaseRunnable);
		
		thread0.start();
		thread1.start();
		thread2.start();
	}

}
