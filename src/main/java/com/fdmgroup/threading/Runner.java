package com.fdmgroup.threading;

public class Runner {
	public static void main(String[] args) throws InterruptedException {
//		ThreadExample thread0 = new ThreadExample();
//		thread0.start();
//		System.out.println("Hello from the Main Thread");
		
		Runnable runnable = new Counter();
		Thread thread0 = new Thread(runnable);
		Thread thread1 = new Thread(runnable);
//		thread0.start();
//		thread1.start();
		
		
		Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int x=1; x<50; x++) {
					System.out.println(Thread.currentThread().getName() + " the count is: " + x);
				}
			}
		});
		
		Thread thread3 = new Thread(() -> {
			for (int y=1; y<50; y++) {
				System.out.println(Thread.currentThread().getName() + " the count is: " + y);
			}
		});
		thread0.start();
		thread1.start();
		thread2.start();
		thread3.start();
		
		thread0.join();
		thread1.join();
		thread2.join();
		thread3.join();
		
		System.out.println("Last Action in the Main Thread");
		System.out.println("---> " + Thread.currentThread().getName() + " has finished");
		
	}
}
