package ru.connect2me.util.hh.parser.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ru.connect2me.util.hh.parser.config.ParserHtmlHhResumeToInhouseXmlException;

/**
 *  Получение почтового адреса
 * 
 * @author Зайнуллин Радик
 */
public class Address {
  public String get(String txt) throws ParserHtmlHhResumeToInhouseXmlException {
    Matcher matcher = Pattern.compile("Почтовый адрес:\\s*(.+?)\\<", Pattern.DOTALL).matcher(txt);
    if (matcher.find()) return matcher.group(1).trim();
    else return "not found";
  }  
}