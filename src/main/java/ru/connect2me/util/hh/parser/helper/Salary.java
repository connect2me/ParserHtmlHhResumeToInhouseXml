package ru.connect2me.util.hh.parser.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ru.connect2me.util.hh.parser.config.ParserHtmlHhResumeToInhouseXmlException;

/**
 *  Получение желаемой зарплаты
 * 
 * @author Зайнуллин Радик
 */
public class Salary {
  public String get(String txt) throws ParserHtmlHhResumeToInhouseXmlException {
    Matcher matcher = Pattern.compile("[\"\\']?resume__position__salary[\"\\']?\\>(.* руб\\.)\\s*\\<", Pattern.DOTALL).matcher(txt);
    if (matcher.find()) return matcher.group(1).replaceAll("\\D", "");
    else return "not found";
  }  
}