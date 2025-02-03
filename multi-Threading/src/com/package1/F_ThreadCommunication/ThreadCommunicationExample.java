package com.package1.F_ThreadCommunication;


class ShraredResource{
	
	private int data;
	private boolean hasData;
	
	public void produce(int value) {
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
	
	
	public int consume() {
		return data;
	}
}

public class ThreadCommunicationExample {

	public static void main(String[] args) {
		
	}

}
