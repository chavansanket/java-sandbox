package com.package1.C_locks;

public class Main {

	public static void main(String[] args) {
		BankAccount bankAccount = new BankAccount();
		//anonymous class 
		Runnable task = new Runnable() {
			
			@Override
			public void run() {
				bankAccount.withdraw(50);
			}
		};
		
		Thread t1 = new Thread(task, "Thread 1");
		Thread t2 = new Thread(task, "Thread 2");
		t1.start();
		t2.start();
	}

}
