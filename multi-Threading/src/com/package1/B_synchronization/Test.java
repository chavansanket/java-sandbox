package com.package1.B_synchronization;

public class Test {

	public static void main(String[] args) {
		
		Counter counter = new Counter(); //this is common resource(object), shared by two threads
		
		MyThread t1 = new MyThread(counter);
		MyThread t2 = new MyThread(counter);
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(counter.getCount());

	}

}
