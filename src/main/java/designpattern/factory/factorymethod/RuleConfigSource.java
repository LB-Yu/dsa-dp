package designpattern.factory.factorymethod;

import designpattern.factory.InvalidRuleConfigException;
import designpattern.factory.RuleConfig;

import static designpattern.factory.factorymethod.RuleConfigParserFactory.*;
import static designpattern.factory.RuleConfigParser.*;

public class RuleConfigSource {
  public RuleConfig load(String ruleConfigFilePath) {
    String ruleConfigFileExtension = getFileExtension(ruleConfigFilePath);

    IRuleConfigParserFactory parserFactory = RuleConfigParserFactoryMap.getParserFactory(ruleConfigFileExtension);
    if (parserFactory == null) {
      throw new InvalidRuleConfigException("Rule config file format is not supported: " + ruleConfigFilePath);
    }
    IRuleConfigParser parser = parserFactory.createParser();

    String configText = "";
    // 从ruleConfigFilePath文件中读取配置文本到configText中
    return parser.parse(configText);
  }

  private String getFileExtension(String filePath) {
    // ...解析文件名获取扩展名，比如rule.json，返回json
    return "json";
  }
}