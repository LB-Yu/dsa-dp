package concurrency.juc.threadpool;

import java.util.concurrent.*;

public class ScheduleExecutorServiceDemo {

  public static void main(String[] args) {
    ScheduledExecutorService ses = Executors.newScheduledThreadPool(10);
    ses.scheduleAtFixedRate(new Runnable() {
      @Override
      public void run() {
        try {
          Thread.sleep(1000);
          System.out.println(System.currentTimeMillis() / 1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }, 0, 2, TimeUnit.SECONDS);
  }
}
