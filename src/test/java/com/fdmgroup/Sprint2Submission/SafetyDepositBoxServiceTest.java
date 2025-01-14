package com.fdmgroup.Sprint2Submission;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class SafetyDepositBoxServiceTest {
	
	SafetyDepositBoxService sdbService;

	@BeforeEach
	void setUp() throws Exception {
		sdbService = SafetyDepositBoxService.getInstance();
		sdbService.setNumberOfSafetyDepositBoxes(2);
	}

	@Test
	void test() throws InterruptedException {
		System.out.println("Starting Program");
		Thread thread1 = new Thread(() -> {
			System.out.println("Thread 1 Started");
			SafetyDepositBox sdb1 = sdbService.allocateSafetyDepositBox();
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			sdbService.releaseSafetyDepositBox(sdb1);
		});
		
		System.out.println("Thread 1 made");
		
		Thread thread2 = new Thread(() -> {
			SafetyDepositBox sdb2 = sdbService.allocateSafetyDepositBox();
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			sdbService.releaseSafetyDepositBox(sdb2);
		});
		
		System.out.println("Thread 2 made");
		
		thread1.start();
		thread2.start();
		thread1.join();
		thread2.join();
		assertFalse(sdbService.isFalse());
		
		
		
	}

}