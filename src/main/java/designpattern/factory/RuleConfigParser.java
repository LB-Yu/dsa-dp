package designpattern.factory;

public class RuleConfigParser {

  public interface IRuleConfigParser {
    RuleConfig parse(String configText);
  }

  public static class JsonRuleConfigParser implements IRuleConfigParser {
    @Override
    public RuleConfig parse(String configText) {
      return null;
    }
  }

  public static class XmlRuleConfigParser implements IRuleConfigParser {
    @Override
    public RuleConfig parse(String configText) {
      return null;
    }
  }

  public static class YamlRuleConfigParser implements IRuleConfigParser {
    @Override
    public RuleConfig parse(String configText) {
      return null;
    }
  }

  public static class PropertiesRuleConfigParser implements IRuleConfigParser {
    @Override
    public RuleConfig parse(String configText) {
      return null;
    }
  }
}
