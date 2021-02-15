package concurrency.basic;

/**
 * A simple experiment of the visibility of volatile.
 * */
public class NoVisibility {

  private volatile static boolean ready;  // if ready is not volatile, the main function will never stop
  private static int number;

  private static class ReaderThread extends Thread {
    @Override
    public void run() {
      while (!ready);
      System.out.println(number);
    }
  }

  public static void main(String[] args) throws InterruptedException {
    new ReaderThread().start();
    Thread.sleep(1000); // sleep 1s, let ReaderThread run first
    number = 42;
    ready = true;
  }
}
