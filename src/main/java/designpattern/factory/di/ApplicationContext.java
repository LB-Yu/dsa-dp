package designpattern.factory.di;

public interface ApplicationContext {
  Object getBean(String beanId);
}
