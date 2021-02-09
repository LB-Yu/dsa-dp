package designpattern.observer;


import java.util.concurrent.Executor;

public class AsyncEventBus extends EventBus {
  public AsyncEventBus(Executor executor) {
    super(executor);
  }
}
