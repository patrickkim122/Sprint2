package com.fdmgroup.reentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DatabaseRunnable implements Runnable {
	private Database database;
	private Lock lock;
	
	public DatabaseRunnable(Database database) {
		super();
		this.database = database;
		this.lock = new ReentrantLock();
	}
	
	@Override
	public void run() {
		try {
			if (lock.tryLock(10, TimeUnit.SECONDS))
			System.out.println(Thread.currentThread().getName() + " has locked database");
			database.writeToDatabase();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			System.out.println(Thread.currentThread().getName() + " has unlocked database");
			lock.unlock();
		}
		
		

	}

}
