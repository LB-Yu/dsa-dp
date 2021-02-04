package designpattern.singleton;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 单例模式的枚举类实现
 * */
public enum  IdGenerator5 {
    INSTANCE;

    private AtomicLong id = new AtomicLong(0);

    public long getId() {
        return id.incrementAndGet();
    }
}
