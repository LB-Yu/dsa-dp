package designpattern.builder;

public class ConstructorArg {
  private boolean isRef;
  private Class type;
  private Object arg;

  private ConstructorArg(Builder builder) {
    isRef = builder.isRef;
    type = builder.type;
    arg = builder.arg;
  }

  public boolean isRef() {
    return isRef;
  }

  public Class getType() {
    return type;
  }

  public Object getArg() {
    return arg;
  }

  public static class Builder {
    private boolean isRef;
    private Class type;
    private Object arg;

    public ConstructorArg build() {
      if (isRef) {
        if (!(arg instanceof String))
          throw new IllegalArgumentException("arg should be string type");
      } else {
        if (arg == null)
          throw new IllegalArgumentException("arg shouldn't be null");
        if (type == null)
          throw new IllegalArgumentException("type shouldn't be null");
      }
      return new ConstructorArg(this);
    }

    public void setRef(boolean isRef) {
      this.isRef = isRef;
    }

    public void setType(Class type) {
      this.type = type;
    }

    public void setArg(Object arg) {
      this.arg = arg;
    }
  }
}
