package ru.connect2me.util.hh.parser;

import ru.connect2me.util.hh.parser.config.ParserHtmlHhResumeToInhouseXmlException;

/**
 * Это интерфейс для использования во внешних (по отношению к библиотеке) приложениях.
 *
 * @author Зайнуллин Радик
 * @version 1.0
 * @since 2012.11.18
 */
public interface IParserHtmlHhResumeToInhouseXml {

  public String execute(String html) throws ParserHtmlHhResumeToInhouseXmlException;
}