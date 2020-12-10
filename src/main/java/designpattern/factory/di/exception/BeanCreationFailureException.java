package designpattern.factory.di.exception;

public class BeanCreationFailureException extends RuntimeException {
  public BeanCreationFailureException(String s, ReflectiveOperationException e) { }
}
