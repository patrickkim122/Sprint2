package com.fdmgroup.raceCondition;

public class AccountRunnable implements Runnable {

	private Account account;
	
	public AccountRunnable(Account account) {
		this.account = account;
	}

	@Override
	public void run() {
		for (int i=0; i<5; i++) {
			account.makeDeposit(3);
		}

	}

}
