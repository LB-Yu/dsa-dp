package designpattern.factory.di;

import designpattern.factory.di.bean.RateLimiter;

public class Demo {
  public static void main(String[] args) {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
    RateLimiter rateLimiter = (RateLimiter) applicationContext.getBean("RateLimiter");
    rateLimiter.test();
    // ...
  }
}
