package ru.connect2me.util.hh;

import java.net.URL;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * Входная точка сервиса
 *
 * @author Зайнуллин Радик
 * @version 1.0
 * @since 2012.11.18
 */
public class Worker {
  
  public static String convert(String html){
    // Включение логирования
    URL url = Main.class.getResource("/log4j.xml");
    DOMConfigurator.configure(url);

    Logger log = Logger.getLogger("common");
    log.info("Hello World!");
    
    System.setProperty("javax.xml.transform.TransformerFactory", "net.sf.saxon.TransformerFactoryImpl");
    
    
    
    return "Hello World!";
  }
}
