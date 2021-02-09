package concurrency.juc.count;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

  public static class Soldier implements Runnable {

    private String soldier;
    private final CyclicBarrier cyclic;

    public Soldier(CyclicBarrier cyclic, String soldierName) {
      this.cyclic = cyclic;
      this.soldier = soldierName;
    }

    @Override
    public void run() {
      try {
        cyclic.await();
        doWork();
        cyclic.await();
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (BrokenBarrierException e) {
        e.printStackTrace();
      }
    }

    private void doWork() {
      try {
        Thread.sleep(Math.abs(new Random().nextInt() % 10000));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(soldier + " Mission completed");
    }
  }

  public static class BarrierRun implements Runnable {

    private boolean flag;
    private int N;

    public BarrierRun(boolean flag, int N) {
      this.flag = flag;
      this.N = N;
    }

    @Override
    public void run() {
      if (flag) {
        System.out.println("Commander: [Soldier " + N + " mission completed]");
      } else {
        System.out.println("Commander: [Soldier " + N + " on call]");
        flag = true;
      }
    }
  }

  public static void main(String[] args) {
    final int N = 10;
    Thread[] allSoldier = new Thread[N];
    boolean flag = false;
    CyclicBarrier cyclic = new CyclicBarrier(N, new BarrierRun(flag, N));
    System.out.println("Assembly team");
    for (int i = 0; i < N; ++i) {
      System.out.println("Soldier " + i + " check in");
      allSoldier[i] = new Thread(new Soldier(cyclic, "Soldier " + i));
      allSoldier[i].start();
    }
  }
}
