package com.package1.D_ReentrantClass;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantExample {
	
	private final Lock lock = new ReentrantLock();;
	
	public void outherMethod() {
		lock.lock();
		
		try {
			System.out.println(Thread.currentThread().getName() + " inside outer method");
			System.out.println("Outer method");
			innerMethod();
		} catch (Exception e) {
			
		}finally {
			lock.unlock();
		}
	}
	
	public void innerMethod() {
		System.out.println("1");
		lock.lock();
		System.out.println("2");
		try {
			System.out.println(Thread.currentThread().getName() + " inside inner method");
			System.out.println("Inner method");
		} catch (Exception e) {
			
		}finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName() + " inside main method");
		ReentrantExample example = new ReentrantExample();
		example.outherMethod();
		
	}

}
