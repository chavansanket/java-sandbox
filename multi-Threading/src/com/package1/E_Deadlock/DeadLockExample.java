//package com.package1.E_Deadlock;
//
//
//class Pen {
//    public synchronized void writeWithPenAndPaper(Paper paper) {
//        System.out.println(Thread.currentThread().getName() + " is using pen " + this + " and trying to use paper " + paper);
//        paper.finishWriting();
//    }
//
//    public synchronized void finishWriting() {
//        System.out.println(Thread.currentThread().getName() + " finished using pen " + this);
//    }
//}
//
//
//public class DeadLockExample {
//
//	public static void main(String[] args) {
//		
//
//	}
//
//}
