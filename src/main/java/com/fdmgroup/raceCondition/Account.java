package com.fdmgroup.raceCondition;

public class Account {
	private int balance = 5;

	public int getBalance() {
		return balance;
	}

	public void deposit(int amount) {
		balance = balance + amount;
	}

	public synchronized void makeDeposit(int amountToAdd) {
		deposit(amountToAdd);
		System.out.println(Thread.currentThread().getName() + " completed deposit. current balance: " + balance);
		
	}
}
