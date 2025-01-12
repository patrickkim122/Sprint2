package com.fdmgroup.producerConsumer;

public class Demo {

	public static void main(String[] args) {
		WorkQueue workQueue = new WorkQueue();
		
		Thread producerThread = new Thread(() -> {
			int count = 1;
			while (true) {
				System.out.println("Queue size is: " + workQueue.getQueueSize());
				workQueue.addItemToQueue(new WorkItem(count));
				System.out.println("producer: item " + count + " added");
				count++;
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		Thread consumerThread = new Thread(() -> {
			while (true) {
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				WorkItem workItem = workQueue.getItemFromQueue();
				System.out.println("Consumer: processing item " + workItem.getId());
				
			}
		});
		producerThread.start();
		consumerThread.start();
	}

}
