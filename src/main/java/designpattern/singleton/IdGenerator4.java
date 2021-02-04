package designpattern.singleton;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 单例模式的静态内部类实现
 * */
public class IdGenerator4 {
    private AtomicLong id = new AtomicLong(0);

    private static class IdGeneratorHolder {
        private static final IdGenerator4 INSTANCE = new IdGenerator4();
    }

    private IdGenerator4() { }

    public static IdGenerator4 getInstance() {
        return IdGeneratorHolder.INSTANCE;
    }

    public long getId() {
        return id.incrementAndGet();
    }
}
