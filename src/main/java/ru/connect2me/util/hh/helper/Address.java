package ru.connect2me.util.hh.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ru.connect2me.util.hh.config.ParserHtmlHhResumeToInhouseXmlException;

/**
 *  Получение почтового адреса
 * 
 * @author Зайнуллин Радик
 */
public class Address {
  public String get(String txt) throws ParserHtmlHhResumeToInhouseXmlException {
    Matcher matcher = Pattern.compile("Почтовый адрес:\\s*(.+?)\\<", Pattern.DOTALL).matcher(txt);
    String address = "not found";
    if (matcher.find()) {
      address = matcher.group(1);
    }
    return  address.trim();
  }  
}