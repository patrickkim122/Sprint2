package com.fdmgroup.reentrantLock;

public class Database {
	public void readFromDatabase() {
		System.out.println(Thread.currentThread().getName() + " is reading from the database.");
	}
	
	public void writeToDatabase() {
		System.out.println(Thread.currentThread().getName() + " is writing to the database.");
	}
}
