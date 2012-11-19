package ru.connect2me.util.hh;

import ru.connect2me.util.hh.config.ParserHtmlHhResumeToInhouseXmlException;
import java.net.URL;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.context.support.GenericXmlApplicationContext;
import ru.connect2me.util.hh.helper.Check;

/**
 * Входная точка сервиса
 *
 * @author Зайнуллин Радик
 * @version 1.0
 * @since 2012.11.18
 */
public class Worker {

  public static String convert(String html) throws ParserHtmlHhResumeToInhouseXmlException {
    // Включение логирования
    URL url = Main.class.getResource("/log4j.xml");
    DOMConfigurator.configure(url);

    Logger log = Logger.getLogger("common");
    log.info("Hello World!");

    System.setProperty("javax.xml.transform.TransformerFactory", "net.sf.saxon.TransformerFactoryImpl");

    GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
    ctx.load("classpath:spring.xml");
    ctx.refresh();
    Check check = ctx.getBean("check", Check.class);
    if (check.isWellFormed("<?xml version=\"1.0\" encoding=\"UTF-8\"?><root><p>Hello World!</p></root>")) System.out.println("XML is well formed!");
    else System.out.println("XML is not well formed!");

    return "Hello World!";
  }
}
