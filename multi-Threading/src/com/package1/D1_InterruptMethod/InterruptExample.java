package com.package1.D1_InterruptMethod;


class MyThread extends Thread{
	public MyThread(String string) {
		super(string);
	}

	@Override
	public void run() {
			try {
				for(int i=0;i<5;i++) {
					
				
				 System.out.println("Thread is running... " + i);
		         Thread.sleep(1000); // Simulating long task
//				System.out.println(Thread.currentThread().getName() + " is executing for i: "+ i+" th time");
				}
		         
			} catch (InterruptedException e) {
				System.out.println("Thread was interrupted.");
			} finally {
	            System.out.println("Finally block executed.");
	        }
			
		}
	
}


public class InterruptExample {

	public static void main(String[] args) throws InterruptedException {
		MyThread myThread = new MyThread("Thread-1");
//		System.out.println("Current thread in main method is: "+ Thread.currentThread().getName());
		myThread.start();
		Thread.sleep(2000);
		myThread.interrupt();
		
	}

}
