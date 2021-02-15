package concurrency.basic;

/**
 * A simple example of thread interrupt. <br/>
 * There are 3 methods correspond to thread interrupt: <br/>
 * 1. {@link Thread#interrupt()}, member function to interrupt the thread; <br/>
 * 2. {@link Thread#isInterrupted()}, member function to judge if the thread is interrupted; <br/>
 * 3. {@link Thread#interrupted()}, static function to judge if the thread is interrupted and
 * reset the interrupted flag.
 * */
public class InterruptExample {

  public static void main(String[] args) throws InterruptedException {
    Thread thread1 = new Thread(() -> {
     while (true) {
       System.out.print(".");
       if (Thread.currentThread().isInterrupted()) {  // will not reset the interrupt flag
         System.out.println("Thread1 interrupted!");
       }
     }
    });

    Thread thread2 = new Thread(() -> {
      while (true) {
        System.out.print(".");
        if (Thread.interrupted()) { // will reset the interrupt flag
          System.out.println("Thread2 interrupted");
        }
      }
    });

    Thread thread3 = new Thread(() -> {
      while (true) {
        if (Thread.currentThread().isInterrupted()) {
          System.out.println("Thread3 interrupted");
          break;
        }
        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) {
          System.out.println("Thread3 interrupted when sleep");
          Thread.currentThread().interrupt();
        }
      }
    });

    thread1.start();
    thread1.interrupt();
    thread1.join();

    thread2.start();
    thread2.interrupt();
    thread2.join();

    thread3.start();
    Thread.sleep(2000);
    thread3.interrupt();
  }
}
