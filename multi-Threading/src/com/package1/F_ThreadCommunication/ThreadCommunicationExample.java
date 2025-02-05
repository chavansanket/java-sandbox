package com.package1.F_ThreadCommunication;


class SharedResource{
	
	private int data;
	private boolean hasData;
	

	public synchronized void produce(int value) {
		while(hasData) {
			try {
				wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		data = value;
		hasData = true;
        System.out.println("Produced: " + value);
        notify();
	}
	
	
	public synchronized int consume() {
		while(!hasData) {
			try {
				wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		hasData=false;
		System.out.println("Consumed: " + data);
		notify();
		return data;
	}
}

class Producer implements Runnable{
	
	private SharedResource resource;
	
	public Producer(SharedResource resource) {
		this.resource = resource;
	}
	
	@Override
	public void run() {
		for(int i=1; i<=10; i++) {
			
			resource.produce(i);
			
//			synchronized (resource) {
//				try {
//					resource.wait(5000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
}

class Consumer implements Runnable{
	private SharedResource resource;
	
	public Consumer(SharedResource resource) {
		this.resource = resource;
	}
	
	@Override
	public void run() {
		for(int i = 1; i<=10;i++) {
			int data = resource.consume();
		}
		
	}
}

public class ThreadCommunicationExample {

	public static void main(String[] args) {
		SharedResource resource = new SharedResource();
		Thread producerThread = new Thread(new Producer(resource));
		Thread consumerThread = new Thread(new Consumer(resource));
		
		consumerThread.start();
		producerThread.start();

		
//		Producer producer = new Producer(resource);
//		Thread t1 = new Thread(producer);
//		t1.start();	
	
	}

}
