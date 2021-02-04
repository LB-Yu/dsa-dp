package designpattern.singleton;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 单例模式的双重检测实现
 * */
public class IdGenerator3 {
    private AtomicLong id = new AtomicLong(0);

    private static IdGenerator3 INSTANCE;

    private IdGenerator3() { }

    public static IdGenerator3 getInstance() {
        if (INSTANCE == null) {
            synchronized (IdGenerator3.class) {
                if (INSTANCE == null) {
                    INSTANCE = new IdGenerator3();
                }
            }
        }
        return INSTANCE;
    }

    public long getId() {
        return id.incrementAndGet();
    }
}
