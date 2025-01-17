package com.fdmgroup.threading;

public class Counter implements Runnable {

	@Override
	public void run() {
		int upper = 100;
		int counter = 0;
		while (counter < upper) {
			System.out.println(Thread.currentThread().getName() + " count is: " + counter);
			counter++;
		}
		System.out.println(Thread.currentThread().getName() + " is finished");

	}

}
