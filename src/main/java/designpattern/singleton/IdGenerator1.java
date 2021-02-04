package designpattern.singleton;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 单例模式的饿汉式实现
 * */
public class IdGenerator1 {
    private AtomicLong id = new AtomicLong(0);

    private static IdGenerator1 INSTANCE = new IdGenerator1();

    private IdGenerator1() { }

    public static IdGenerator1 getInstance() {
        return INSTANCE;
    }

    public long getId() {
        return id.incrementAndGet();
    }
}
