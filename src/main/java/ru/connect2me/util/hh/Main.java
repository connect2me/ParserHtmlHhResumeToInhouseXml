package ru.connect2me.util.hh;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import org.apache.commons.io.IOUtils;
import ru.connect2me.util.hh.config.ParserHtmlHhResumeToInhouseXmlException;

/**
 * Входная точка для ручного тестирования сервиса
 *
 * @author Зайнуллин Радик
 * @version 1.0
 * @since 2012.11.18
 */
public class Main {
  public static void main(String[] args) throws ParserHtmlHhResumeToInhouseXmlException {
    InputStream is = Main.class.getResourceAsStream("/test/input/sample001.txt");
    StringWriter writer = new StringWriter();
    try {
      IOUtils.copy(is, writer, "UTF-8");
    } catch (IOException ex) {
      System.out.println("Не могу прочесть тестовый sample файл.");
    }
    
    new Worker().execute(writer.toString());
  }
}