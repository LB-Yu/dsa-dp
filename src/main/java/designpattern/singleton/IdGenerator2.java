package designpattern.singleton;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 单例模式的懒汉式实现
 * */
public class IdGenerator2 {
    private AtomicLong id = new AtomicLong(0);

    private static IdGenerator2 INSTANCE;

    private IdGenerator2() { }

    public static synchronized IdGenerator2 getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new IdGenerator2();
        }
        return INSTANCE;
    }

    public long getId() {
        return id.incrementAndGet();
    }
}
