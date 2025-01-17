package com.fdmgroup.producerConsumer;

import java.util.LinkedList;
import java.util.Queue;

public class WorkQueue {
	private Queue<WorkItem> queue = new LinkedList<>();
	
	public synchronized void addItemToQueue(WorkItem workItem) {
		if (queue.size() >= 10) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		queue.add(workItem);
	}
	
	public synchronized WorkItem getItemFromQueue() {
		WorkItem workItem = queue.poll();
		if (queue.size() < 10) {
			notify();
		}
		return workItem;
	}
	
	public int getQueueSize() {
		return queue.size();
	}
}
