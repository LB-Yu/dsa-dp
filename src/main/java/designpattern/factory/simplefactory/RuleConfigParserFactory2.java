package designpattern.factory.simplefactory;


import java.util.HashMap;
import java.util.Map;

import static designpattern.factory.RuleConfigParser.*;

public class RuleConfigParserFactory2 {
  private static final Map<String, IRuleConfigParser> cachedParsers = new HashMap<>();

  static {
    cachedParsers.put("json", new JsonRuleConfigParser());
    cachedParsers.put("xml", new XmlRuleConfigParser());
    cachedParsers.put("yaml", new YamlRuleConfigParser());
    cachedParsers.put("properties", new PropertiesRuleConfigParser());
  }

  public static IRuleConfigParser createParser(String configFormat) {
    if (configFormat == null || configFormat.isEmpty()) {
      return null;
    }
    return cachedParsers.get(configFormat.toLowerCase());
  }
}
