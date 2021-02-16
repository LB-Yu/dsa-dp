package concurrency.juc.threadpool;

import java.util.concurrent.*;

import static concurrency.juc.threadpool.DivTaskDemo.*;

public class TraceThreadPoolExecutorDemo {

    public static class TraceTreadPoolExecutor extends ThreadPoolExecutor {

        public TraceTreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        @Override
        public void execute(Runnable command) {
            super.execute(wrap(command, clientTrace(), Thread.currentThread().getName()));
        }

        @Override
        public Future<?> submit(Runnable task) {
            return super.submit(wrap(task, clientTrace(), Thread.currentThread().getName()));
        }

        private Exception clientTrace() {
            return new Exception("Client stack trace");
        }

        private Runnable wrap(final Runnable task, final Exception clientStack, String clientThreadName) {
            return new Runnable() {
                @Override
                public void run() {
                    try {
                        task.run();
                    } catch (Exception e) {
                        clientStack.printStackTrace();
                        throw e;
                    }
                }
            };
        }
    }

    public static void main(String[] args) {
        ThreadPoolExecutor pools = new TraceTreadPoolExecutor(0, Integer.MAX_VALUE,
                0L, TimeUnit.MILLISECONDS,
                new SynchronousQueue<>());

        for (int i = 0; i < 5; ++i) {
            pools.execute(new DivTask(100, i));
        }
    }
}
