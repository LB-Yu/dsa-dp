package concurrency.juc.threadpool;

import java.util.concurrent.*;

public class DivTaskDemo {

    public static class DivTask implements Runnable {
        private int a, b;

        public DivTask(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public void run() {
            double re = a / b;
            System.out.println(re);
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor pools = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                0, TimeUnit.MILLISECONDS,
                new SynchronousQueue<>());
        for (int i = 0; i < 5; ++i) {
            Future re =  pools.submit(new DivTask(100, i));
            re.get();
        }
    }
}
