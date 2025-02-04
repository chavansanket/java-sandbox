package com.package1.A_main;

public class MyThreadMain extends Thread {

	public MyThreadMain(String name) {
		super(name);
	}

	@Override
	public void run() {
		try {
			System.out.println("Current Thread "+ Thread.currentThread().getName()+ " before waiting");
			wait();
			System.out.println("Current Thread "+ Thread.currentThread().getName());
		} catch (InterruptedException e) {
			System.out.println(" wait state is ended ");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

		
//		try {
//			Thread.sleep(3000);	
//		}catch (InterruptedException e) {
//			System.out.println("Interrupted Exception occurred: "+ e);
//			System.out.println("Thread is stopping.... "+ Thread.currentThread().getName());
//		}
		
//		try {
//			for(int i= 0; i<5;i++) {
//				if(currentThread().isInterrupted()) {
//				}
//				System.out.println("I am doing work "+i + " ,is interrupted: "+ currentThread().isInterrupted());
//				Thread.sleep(100);
//			}
//		} 
//		catch (InterruptedException e) {
//			System.out.println("Exception occurred: "+e+" ,Thread is stopping.... "+ Thread.currentThread().getName()+  " ,is interrupted: "+ currentThread().isInterrupted());
//			Thread.currentThread().interrupt(); //restoring interrupt state
//			System.out.println("Thread is interrupted: "+ Thread.currentThread().isInterrupted());
//		}	

	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName() +" current");
		MyThreadMain t1 = new MyThreadMain("Thread-1");
//		MyThread t2 = new MyThread("Thread-2");
//		t1.setDaemon(true);

		t1.start();
		System.out.println(Thread.currentThread().getName() +" current");
//		try {
////			t1.join();
//			Thread.sleep(1000);
//			t1.interrupt();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			
//		}finally {
//			
//		}
		System.out.println("hi");

	}

}

//t1.join(); //Waits for this thread to die. (i.e. wait until t1 thread execution is not getting finished).

//Thread.yield() 
	//The Thread.yield() method is a hint to the thread scheduler that the current thread is willing to yield its current use of the CPU, 
		//allowing other threads of the same or higher priority to execute. 
	//However, this is just a suggestion, and the thread scheduler is free to ignore it. 
	//It does not guarantee that the current thread will immediately stop executing or that another thread will start executing.
	
	//When to Use Thread.yield():
		//In scenarios where you want to improve fairness among threads.
		//In busy-waiting or polling loops to reduce CPU usage.
		//When a thread is performing a low-priority task and wants to give higher-priority threads a chance to run.
	//]Thread.yield() is rarely used in real-world applications because modern thread schedulers are efficient, and explicit yielding is often unnecessary.

//User Thread //wo thread jisse aap kam karwa rhe ho(business logic)
//Daemon Thread //background thread; ex.GC thread; JVM will not wait for complete this thread execution; //t1.setDaemon(true) ...to make thread as daemon

//synchronized
//In multithreading, multiple threads may access shared resources simultaneously, causing race conditions (inconsistent data).
//🔹 The synchronized keyword is used to prevent multiple threads from accessing critical sections at the same time.
// lock is applied by thread which is accessing shared resource
//when t1 is accessing shared resource, t1 will not leave resource until execution of shared resource(including Thread.sleep() of shared resource)
//when threads are running concurrently, due to relative timing between them, result is becoming unpredictable and here we are not using synchronized, this condition is called as a race condition and to avoid race condition we use 'synchronized'
//applied at critical section (resource is shared between thread ex.increament method of Counter class, is shared between two threads)
//we can make to a function or to a block of code 
//mutual exclusion : it ensures multiple thread will not access shared resource at same time (means at a time only one thread is 
	//accessing shared resource)
//✅ Use Synchronized Method if the entire method needs thread safety.
//✅ Use Synchronized Block if only part of the code needs synchronization (better performance).

//lock
//drawback of first type of lock(which comes with synchronized keyword). thread two is waiting for indefinitely time, 
	//so use second type of lock i.e.manual lock(explicit lock)
//lock.lock();....thread will waits until lock will get,(it is similar to synchronized)
//lock.tryLock() and lock.tryLock(time,timeunit) will not wait until lock is getting.

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

//synchronized method or block : means this method will acquire intrinsic lock of the object the method belongs to(means who called this synchronized method)

//deadlock
//How to prevent?->lock ko acquire krne ki ordering consistent krde(ex. pehle pen lao then get go for paper; by introducing synchronized block with pen as arg in thread 2 task) then we can resolve deadlock

//ThreadCommunicaion
//wait(), notify(), notifyAll() these methods were introduced for thread communication
//these methods are called within synchronized context
//wait() method : tells to current thread that release the lock and wait until another thread call notify() or notifyAll()
//notify() and notifyAll() wakes up single and all waiting threads


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




//Absolutely! Let’s break down the lifecycle of a thread in Java and understand when the thread scheduler and CPU scheduler come into play. This will help you visualize how threads are managed and executed.
//
//Lifecycle of a Thread
//A thread in Java goes through several states during its lifecycle. These states are defined in the Thread.State enum. Here’s a step-by-step explanation of what happens:
//
//1. NEW State
//When you create a thread object using new Thread(), the thread is in the NEW state.
//
//At this point, the thread has been created but not yet started. It is just an object in memory.
//
//Example:
//
//java
//Copy
//Thread t1 = new Thread(() -> System.out.println("Thread running"));
//// t1 is in the NEW state
//2. RUNNABLE State
//When you call t1.start(), the thread transitions to the RUNNABLE state.
//
//In this state:
//
//The thread is eligible to run, but it may or may not be executing.
//
//The thread is added to the thread scheduler's ready queue, waiting for CPU time.
//
//The thread scheduler (part of the OS) decides when to allocate CPU time to the thread.
//
//Example:
//
//java
//Copy
//t1.start(); // t1 is now in the RUNNABLE state
//3. RUNNING State
//When the thread scheduler assigns CPU time to the thread, it transitions to the RUNNING state.
//
//In this state:
//
//The thread is actively executing its run() method.
//
//The CPU scheduler is responsible for managing the thread's execution on the CPU.
//
//Example:
//
//java
//Copy
//// Inside the run() method:
//public void run() {
//    System.out.println("Thread running"); // Thread is in the RUNNING state
//}
//4. BLOCKED/WAITING/TIMED_WAITING States
//A thread can temporarily stop executing and enter one of these states:
//
//BLOCKED: The thread is waiting to acquire a lock (e.g., for a synchronized block or ReentrantLock).
//
//WAITING: The thread is waiting indefinitely for another thread to perform a specific action (e.g., Object.wait() or Thread.join()).
//
//TIMED_WAITING: The thread is waiting for a specified amount of time (e.g., Thread.sleep(1000) or Object.wait(1000)).
//
//In these states:
//
//The thread is not consuming CPU time.
//
//The thread scheduler removes the thread from the CPU and places it in a waiting queue.
//
//Example:
//
//java
//Copy
//synchronized (lock) {
//    lock.wait(); // Thread enters WAITING state
//}
//5. TERMINATED State
//When the thread completes its run() method or is explicitly stopped, it enters the TERMINATED state.
//
//In this state:
//
//The thread is no longer alive and cannot be restarted.
//
//Its resources are cleaned up by the JVM.
//
//Example:
//
//java
//Copy
//// After the run() method completes:
//System.out.println("Thread finished"); // Thread enters TERMINATED state
//When Do the Thread Scheduler and CPU Scheduler Come into Play?
//Thread Scheduler
//The thread scheduler comes into play when:
//
//You call t1.start(): The thread is added to the scheduler's ready queue.
//
//The thread transitions between states (e.g., from RUNNABLE to RUNNING or from RUNNING to BLOCKED).
//
//The thread is waiting for CPU time (e.g., in the RUNNABLE state).
//
//CPU Scheduler
//The CPU scheduler comes into play when:
//
//The thread is in the RUNNING state and actively executing on the CPU.
//
//The CPU scheduler decides how much time the thread gets on the CPU (time slicing).
//
//The thread is preempted (interrupted) to allow another thread to run.
//
//Visualization of the Thread Lifecycle
//Copy
//NEW
//  ↓
//RUNNABLE (ready to run, waiting for CPU time)
//  ↓
//RUNNING (actively executing on the CPU)
//  ↓
//BLOCKED/WAITING/TIMED_WAITING (temporarily paused)
//  ↓
//TERMINATED (thread is dead)








