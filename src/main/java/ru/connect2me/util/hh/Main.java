package ru.connect2me.util.hh;

import ru.connect2me.util.hh.config.ParserHtmlHhResumeToInhouseXmlException;
import ru.connect2me.util.hh.config.XMLConfiguration;

/**
 * Входная точка для ручного тестирования сервиса
 *
 * @author Зайнуллин Радик
 * @version 1.0
 * @since 2012.11.18
 */
public class Main {
  public static void main(String[] args) throws ParserHtmlHhResumeToInhouseXmlException {
    new Worker(new XMLConfiguration(Main.class.getResourceAsStream("/config.xml"))).execute("html");
  }
}