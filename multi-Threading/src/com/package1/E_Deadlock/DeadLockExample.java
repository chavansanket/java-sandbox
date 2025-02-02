package com.package1.E_Deadlock;


class Pen {
    public synchronized void writeWithPenAndPaper(Paper paper) {
        System.out.println(Thread.currentThread().getName() + " is using pen " + this + " and trying to use paper " + paper);
        paper.finishWriting();
    }

    public synchronized void finishWriting() {
        System.out.println(Thread.currentThread().getName() + " finished using pen " + this);
    }

    
}

class Paper {
    public synchronized void writeWithPaperAndPen(Pen pen) {
        System.out.println(Thread.currentThread().getName() + " is using paper " + this + " and trying to use pen " + pen);
        pen.finishWriting();
    }

    public synchronized void finishWriting() {
        System.out.println(Thread.currentThread().getName() + " finished using paper " + this);
    }
}

class Task1 implements Runnable{
    private Pen pen;
    private Paper paper;

    public Task1(Pen pen, Paper paper) {
        this.pen = pen;
        this.paper = paper;
    }

	
    @Override
    public void run() {
        pen.writeWithPenAndPaper(paper); // thread1 locks pen and tries to lock paper
    }
	
}

class Task2 implements Runnable {
    private Pen pen;
    private Paper paper;

    public Task2(Pen pen, Paper paper) {
        this.pen = pen;
        this.paper = paper;
    }

    @Override
    public void run() {
        synchronized (pen) {
        	paper.writeWithPaperAndPen(pen); // thread2 locks paper and tries to lock pen
		}
            
        
    }
}

//deadlock
//lock ko acquire krne ki ordering consistent krde(ex. pehle pen lao then get go for paper) then we can resolve deadlock


public class DeadLockExample {

	public static void main(String[] args) {
		Pen pen = new Pen();
		Paper paper = new Paper();
		Task1 task1 = new Task1(pen, paper);
		
		Thread thread1 = new Thread(task1, "Thread-1");
		Thread thread2 = new Thread(new Task2(pen, paper), "Thread-2");
		
		thread1.start();
		thread2.start();
		

	}

}
