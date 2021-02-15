package concurrency.basic;

/**
 * A simple example of how to use {@link Object#wait()} and {@link Object#notify()} methods. <br/>
 * 1. When call {@link Object#wait()}, it will release the monitor of this instance. <br/>
 * 2. When {@link Object#wait()} returned, it will get the monitor again.
 * {@link Object#notify()} is the same. <br/>
 * So these two methods need call in the <code>synchronized</code> code block.
 * */
public class SimpleWN {

  private final static Object object = new Object();

  public static class T1 extends Thread {
    @Override
    public void run() {
      synchronized (object) {
        System.out.println(System.currentTimeMillis() + ": T1 start!");
        try {
          System.out.println(System.currentTimeMillis() + ": T1 wait for object");
          object.wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() + ": T1 end!");
      }
    }
  }

  public static class T2 extends Thread {
    @Override
    public void run() {
      synchronized (object) {
        System.out.println(System.currentTimeMillis() + ": T2 start! Notify one thread");
        object.notify();
        System.out.println(System.currentTimeMillis() + ": T2 end!");
        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public static void main(String[] args) {
    Thread t1 = new T1();
    Thread t2 = new T2();
    t1.start();
    t2.start();
  }
}
