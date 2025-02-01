package com.package1.D2_FairLock;

import java.util.concurrent.locks.ReentrantLock;


class MyThread extends Thread{
	private static final ReentrantLock lock = new ReentrantLock(true);
	
	public MyThread(String name) {
		super(name);
	}
	
	@Override
	public void run() {
		try {
			lock.lock();
			System.out.println(Thread.currentThread().getName() + " is running");
			Thread.sleep(1000);
		}catch (Exception e) {
			
		} finally {
			lock.unlock();
		}
		
	}
	
}

public class FairLockExample {
	
	public static void main(String[] args) {
		MyThread t1 = new MyThread("Thread1");
		MyThread t2 = new MyThread("Thread2");
		MyThread t3 = new MyThread("Thread3");
		MyThread t4 = new MyThread("Thread4");
		MyThread t5 = new MyThread("Thread5");
		MyThread t6 = new MyThread("Thread6");
		MyThread t7 = new MyThread("Thread7");
		MyThread t8 = new MyThread("Thread8");
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		t7.start();
		t8.start();
		

	}

}
