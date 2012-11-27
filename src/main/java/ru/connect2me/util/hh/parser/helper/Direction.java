package ru.connect2me.util.hh.parser.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ru.connect2me.util.hh.parser.config.ParserHtmlHhResumeToInhouseXmlException;

/**
 *  Получение желаемой специализации по которой апликант ищет работу
 * 
 * @author Зайнуллин Радик
 */
public class Direction {
  public String get(String txt) throws ParserHtmlHhResumeToInhouseXmlException {
    Matcher m1 = Pattern.compile("\"resume__position__specialization\">(.+?)(\\s?\\→.*?)</div>", Pattern.DOTALL).matcher(txt);
    Matcher m2 = Pattern.compile("\"resume__position__specialization\">(.+?)</div>", Pattern.DOTALL).matcher(txt);
    
    if (m1.find()) return m1.group(1).replaceAll("\r\n|\r|\n", "").replaceAll("\\s+", " ").replaceAll("\\s*$", "");
    else if (m2.find()) return m2.group(1).replaceAll("\r\n|\r|\n", "").replaceAll("\\s+", " ").replaceAll("\\s*$", "");
    else return "not found";    
  }  
}