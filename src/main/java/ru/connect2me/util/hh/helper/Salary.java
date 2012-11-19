package ru.connect2me.util.hh.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ru.connect2me.util.hh.config.ParserHtmlHhResumeToInhouseXmlException;

/**
 *  Получение желаемой зарплаты
 * 
 * @author Зайнуллин Радик
 */
public class Salary {
  public String get(String txt) throws ParserHtmlHhResumeToInhouseXmlException {
    Matcher matcher = Pattern.compile("[\"\\']?resume__position__salary[\"\\']?\\>(.* руб\\.)\\s*\\<", Pattern.DOTALL).matcher(txt);
    String salary = "not found";
    if (matcher.find()) {
      salary = matcher.group(1);
    }
    return salary;
  }  
}