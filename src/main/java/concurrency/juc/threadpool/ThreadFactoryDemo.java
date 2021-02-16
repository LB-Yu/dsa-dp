package concurrency.juc.threadpool;

import java.util.concurrent.*;

import static concurrency.juc.threadpool.RejectThreadPoolDemo.*;

public class ThreadFactoryDemo {

    public static void main(String[] args) throws InterruptedException {
        MyTask task = new MyTask();
        ExecutorService es = new ThreadPoolExecutor(5, 5,
                0L, TimeUnit.MILLISECONDS,
                new SynchronousQueue<>(),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread t = new Thread(r);
                        t.setDaemon(true);
                        System.out.println("create " + t);
                        return t;
                    }
                });
        for (int i = 0; i < 5; ++i) {
            es.submit(task);
        }
        Thread.sleep(2000);
    }
}
