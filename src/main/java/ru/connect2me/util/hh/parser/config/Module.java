package ru.connect2me.util.hh.parser.config;

import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * Инициализация логирования, включения Saxon xslt-парсера  
 *
 * @author Зайнуллин Радик
 * @version 1.0
 * @since 2012.11.18
 */
public abstract class Module {
  protected static ClassLoader classLoader;
  protected static GenericXmlApplicationContext ctx;
  protected static final Logger logger = LoggerFactory.getLogger(Module.class);
  protected static Properties props; // свойства из config.xml

  public Module(Configuration config) {
    init(config);
  }

  private void init(Configuration config) {
    classLoader = Thread.currentThread().getContextClassLoader();
    props = config.getProperties();
    // Включение Saxon парсера
    System.setProperty("javax.xml.transform.TransformerFactory", "net.sf.saxon.TransformerFactoryImpl");    
    // Включение spring контекста
    ctx = new GenericXmlApplicationContext();
    ctx.load("classpath:spring.xml");
    ctx.refresh();    
  }

  public Properties getProperties() { return props; }
}