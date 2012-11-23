package ru.connect2me.util.hh.parser.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ru.connect2me.util.hh.parser.config.ParserHtmlHhResumeToInhouseXmlException;

/**
 * Получение "занятость - полная, частичная и т.д."
 * 
 * @author Зайнуллин Радик
 */
public class Schedule {
  public String get(String txt) throws ParserHtmlHhResumeToInhouseXmlException {
    // Полная занятость,  Полный день
    Matcher matcher = Pattern.compile("Полная занятость,[^pL]+Полный день", Pattern.CASE_INSENSITIVE | Pattern.DOTALL).matcher(txt);
    if (matcher.find()) return matcher.group();
    else return "not found";
  }    
}