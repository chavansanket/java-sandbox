package com.package1.A_main;

public class MyThreadMain extends Thread {

	public MyThreadMain(String name) {
		super(name);
	}

	@Override
	public void run() {
		for (int i = 0; i < 200; i++) {
			System.out.println(Thread.currentThread().getName() + " is running.");

		}

	}

	public static void main(String[] args) {
		MyThreadMain t1 = new MyThreadMain("Thread-1");
//		MyThread t2 = new MyThread("Thread-2");
//		t1.setDaemon(true);

		t1.start();

		try {
			t1.join();
		} catch (Exception e) {

		}
		System.out.println("hi");

	}

}

//t1.join(); //Waits for this thread to die. (i.e. wait un-till t1 thread execution is not getting finished).

//Thread.yeild() //jo dusre thread h, unko bhi chance dena h,(ye sirf hint deta h, jaroori nhi h, aisa hi ho)
//User Thread //wo thread jisse aap kam karwa rhe ho(business logic)
//Daemon Thread //background thread; ex.GC thread; JVM will not wait for complete this thread execution; //t1.setDaemon(true) ...to make thread as daemon

//synchronized
//In multithreading, multiple threads may access shared resources simultaneously, causing race conditions (inconsistent data).
//🔹 The synchronized keyword is used to prevent multiple threads from accessing critical sections at the same time.
// lock is applied by thread which is accessing shared resource
//when t1 is accessing shared resource, t1 will not leave resource untill execution of shared resource(including Thread.sleep() of shared resource)
//when threads are running concurrently, due to relative timing between them, result is becoming unpredictable and here we are not using synchronized, this condition is called as a race condition and to avoid race condition we use 'synchronized'
//applied at critical section (resource is shared between thread ex.increament method of Counter class, is shared between two threads)
//we can make to a function or to a block of code 
//mutual exclusion : it ensures multiple thread will not access shared resource at same time (means at a time only one thread is accessing shared resource)
//✅ Use Synchronized Method if the entire method needs thread safety.
//✅ Use Synchronized Block if only part of the code needs synchronization (better performance).

//lock
//drawback of first type of lock(which comes with synchronized keyword). thread two is waiting for indefinitely time, so use second type of lock i.e.manual lock(explicit lock)
//lock.lock();....thread will waits untill lock will get,(it is similar to synchronized)
//lock.tryLock() and lock.tryLock(time,timeunit) will not wait untill lock is getting.

//Reentrant Class
//Reentrant Lock (Reentrant Class) in Java
//It is a part of java.util.concurrent.locks package.
//A reentrant lock allows the same thread to acquire the lock multiple times without causing a deadlock.
//If a thread that already holds the lock requests it again, it is allowed to proceed without waiting (it increments a counter internally).
//The lock is released only when the thread releases(ex.lock.unlock()) it as many times as it acquired it.
//How Reentrant Lock Prevents Deadlock?
//In traditional synchronization (using synchronized keyword), if the same thread tries to acquire a lock that it already holds, it could lead to a deadlock.
//ReentrantLock prevents this by allowing a thread to acquire the lock again if it already holds it.
//This avoids self-deadlock situations.

//stop()
//The stop() method was originally used to forcefully terminate a thread.
//However, it was deprecated in Java 1.2 because it is unsafe and can cause issues like resource leaks and inconsistent states.

//🔹 What is interrupt()?
//interrupt() does not stop a thread immediately.
//It sends a signal to the thread that it should stop.
//The thread must handle the interrupt properly (check isInterrupted() or handle InterruptedException).
//🔹 Why Use interrupt() Instead of stop()?
//Reason	stop() (Deprecated, Dangerous ❌)	interrupt() (Safe ✅)
//Graceful Stop	Stops immediately, no cleanup	Allows cleanup before stopping
//Resource Management	Leaves resources open	Ensures proper cleanup
//Thread Safety	Can leave thread in inconsistent state	Allows controlled exit
//🔹 How to Use interrupt() Properly?
//✅ Method 1: Check isInterrupted() in a Loop
//class MyThread extends Thread {
//    public void run() {
//        while (!Thread.currentThread().isInterrupted()) {  // Check for interrupt
//            System.out.println("Thread is running...");
//        }
//        System.out.println("Thread is stopping.");
//    }
//}
//
//public class InterruptExample {
//    public static void main(String[] args) throws InterruptedException {
//        MyThread t = new MyThread();
//        t.start();
//
//        Thread.sleep(2000);
//        t.interrupt();  // Gracefully stop the thread
//    }
//}
//✔ Thread stops when interrupted instead of running forever.
//
//✅ Method 2: Handle InterruptedException
//class MyThread extends Thread {
//    public void run() {
//        try {
//            while (!Thread.currentThread().isInterrupted()) {
//                System.out.println("Thread is sleeping...");
//                Thread.sleep(5000);
//            }
//        } catch (InterruptedException e) {
//            System.out.println("Thread stopping due to interrupt.");
//        }
//    }
//}
//✔ Stops sleeping threads safely.
//
//🔹 How Can a Thread Ignore interrupt()?
//A thread ignores interrupt() in two cases:
//
//Never checking isInterrupted() → Runs forever
//Catching InterruptedException and continuing execution
//❌ Example: Ignoring interrupt()
//class MyThread extends Thread {
//    public void run() {
//        try {
//            while (true) {
//                System.out.println("Thread is sleeping...");
//                Thread.sleep(5000);
//            }
//        } catch (InterruptedException e) {
//            System.out.println("Interrupt received, but ignoring...");
//        }
//    }
//}
//🚨 Thread keeps running even after interrupt() is called!
//
//✔ Fix: Check isInterrupted() after catching the exception.
//
//🔹 When Should a Thread Keep Running After interrupt()?
//Some threads should not stop immediately, for example:
//
//Critical work must be finished first (e.g., database transaction).
//Background services should not stop unless explicitly told (e.g., logging thread).
//Thread should handle cleanup before stopping (e.g., closing file connections).
//✅ Example: Completing Work Before Stopping
//class ImportantTask extends Thread {
//    public void run() {
//        for (int i = 1; i <= 5; i++) {
//            if (Thread.currentThread().isInterrupted()) {
//                System.out.println("Interrupt received, but finishing work...");
//            }
//            System.out.println("Processing step " + i);
//        }
//        System.out.println("Task finished safely.");
//    }
//}
//✔ Thread does not stop immediately but finishes its task first.
//
//🔹 Final Summary: Key Points to Remember
//Concept	Explanation
//What does interrupt() do?	Sends a signal to a thread to stop. Does NOT stop it immediately.
//How to stop a thread?	Use isInterrupted() or handle InterruptedException.
//How can a thread ignore interrupt()?	If it never checks isInterrupted() or catches InterruptedException and continues.
//Why let a thread keep running?	If it must finish a critical task before stopping.
//Why use interrupt() instead of stop()?	stop() is unsafe, interrupt() allows proper cleanup.
//✅ Best Practice: Always Handle interrupt() Gracefully!
//Never force stop a thread using stop()!
//Instead, check isInterrupted() or handle InterruptedException properly.

//📌 Short Notes on Thread.currentThread().interrupt() in Catch Block
//🔹 Why Call interrupt() in Catch Block?
//Restores the Interrupt Flag – When InterruptedException occurs, the interrupt flag is cleared. Calling interrupt() restores it.
//Allows Proper Handling – Other methods can check isInterrupted() and stop execution safely.
//Prevents Unexpected Behavior – Without restoring the flag, the thread may continue running instead of stopping.
//🔹 Example Behavior
//Without Thread.currentThread().interrupt();
//🚨 The thread ignores the interrupt and continues running.
//
//catch (InterruptedException e) {
//    System.out.println("Caught exception, but NOT setting interrupt flag.");
//}
//With Thread.currentThread().interrupt();
//✅ The thread knows it was interrupted and can stop safely.
//
//catch (InterruptedException e) {
//    System.out.println("Caught exception, restoring interrupt flag.");
//    Thread.currentThread().interrupt();  // Restore the interrupt flag
//}
//🔹 Key Takeaways
//interrupt() does NOT stop a thread immediately.
//The thread must check isInterrupted() and handle the interruption properly.
//Best Practice – Always restore the interrupt flag in the catch block when handling InterruptedException.



//disadvantages of synchronized
//a. no fairness
//b. indefinitely blocking
//c. no interruptibility available
//d. read/write locking (synchronized,could not distinguish between read and write operation and due to this unnecessary blocking happens)


////Fairness of locks & read write lock
//🔹 Summary Table
//Type	Behavior	Use Case
//Unfair Lock (Default)	Threads get lock randomly, fast	High-performance applications
//Fair Lock (true)	Threads get lock in order	Prevents starvation
//Read Lock	Multiple readers, no writers	Heavy read operations
//Write Lock	Only one writer, blocks reads/writes	Data modification

//📌 Short Notes on Fair & Unfair Locks
//🔹 Fair Lock (ReentrantLock(true))
//✅ Threads get the lock in the order they requested it (FIFO - First In, First Out).
//✅ Prevents starvation (all threads get a fair chance).
//🚨 Slower due to extra overhead.
//🔹 Use when fairness is important (e.g., scheduling).
//
//java
//Copy
//Edit
//Lock lock = new ReentrantLock(true); // Fair Lock
//📝 Output (Example Execution Order):
//
//csharp
//Copy
//Edit
//Thread-1 got the lock.
//Thread-2 got the lock.
//Thread-3 got the lock.
//🔹 Unfair Lock (ReentrantLock(false), Default)
//✅ Faster performance (CPU decides who gets the lock).
//✅ Threads acquire the lock randomly (not FIFO).
//🚨 May cause starvation (some threads may wait longer).
//🔹 Use for high-performance applications.
//
//java
//Copy
//Edit
//Lock lock = new ReentrantLock(false); // Unfair Lock (Default)
//📝 Output (Example Execution Order):
//
//csharp
//Copy
//Edit
//Thread-3 got the lock.
//Thread-1 got the lock.
//Thread-5 got the lock.
//🔹 Key Differences
//Feature	Fair Lock (true) ✅	Unfair Lock (false) 🚀
//Order	FIFO (Request Order)	Random (CPU Decides)
//Performance	Slower	Faster
//Starvation Risk	❌ No	✅ Possible
//✅ Use Fair Lock when fairness is needed.
//🚀 Use Unfair Lock for better speed.










