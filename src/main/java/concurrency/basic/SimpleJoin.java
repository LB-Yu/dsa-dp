package concurrency.basic;

/**
 * A simple example of how to use {@link Thread#join()}.
 * {@link Thread#join()} actually use the {@link Object#wait()} & {@link Object#notifyAll()},
 * so don't use {@link Object#wait()} and {@link Object#notifyAll()} at the same time.
 * */
public class SimpleJoin {

  public volatile static int i = 0;

  public static class AddThread extends Thread {
    @Override
    public void run() {
      for (i = 0; i < 10000000; i++);
    }
  }

  public static void main(String[] args) throws InterruptedException {
    AddThread at = new AddThread();
    at.start();
    at.join();
    System.out.println(i);
  }
}
