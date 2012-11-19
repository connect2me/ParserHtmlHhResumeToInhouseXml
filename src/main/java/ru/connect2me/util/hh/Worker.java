package ru.connect2me.util.hh;

import ru.connect2me.util.hh.config.Configuration;
import ru.connect2me.util.hh.config.Module;
import ru.connect2me.util.hh.config.ParserHtmlHhResumeToInhouseXmlException;
import ru.connect2me.util.hh.helper.Check;

/**
 * Входная точка сервиса
 *
 * @author Зайнуллин Радик
 * @version 1.0
 * @since 2012.11.18
 */
public class Worker extends Module implements Command {
  public Worker(Configuration config) {
    super(config);
  }

  @Override
  public String execute(String html) throws ParserHtmlHhResumeToInhouseXmlException {
    Check check = ctx.getBean("check", Check.class);


    return "Hello World!";
  }
}
