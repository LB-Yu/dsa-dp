package designpattern.factory.factorymethod;

import static designpattern.factory.RuleConfigParser.*;

public class RuleConfigParserFactory {

  public interface IRuleConfigParserFactory {
    IRuleConfigParser createParser();
  }

  public static class JsonRuleConfigParserFactory implements IRuleConfigParserFactory {
    @Override
    public IRuleConfigParser createParser() {
      return new JsonRuleConfigParser();
    }
  }

  public static class XmlRuleConfigParserFactory implements IRuleConfigParserFactory {
    @Override
    public IRuleConfigParser createParser() {
      return new XmlRuleConfigParser();
    }
  }

  public static class YamlRuleConfigParserFactory implements IRuleConfigParserFactory {
    @Override
    public IRuleConfigParser createParser() {
      return new YamlRuleConfigParser();
    }
  }

  public static class PropertiesRuleConfigParserFactory implements IRuleConfigParserFactory {
    @Override
    public IRuleConfigParser createParser() {
      return new PropertiesRuleConfigParser();
    }
  }
}
