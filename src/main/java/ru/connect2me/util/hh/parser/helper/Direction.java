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
    Matcher matcher = Pattern.compile("\"resume__position__specialization\">(.+?)(\\s?\\→.*?)</div>", Pattern.DOTALL).matcher(txt);

    String direction = "not found";
    if (matcher.find()) {
      direction = matcher.group(1);
    }
    return direction.trim().replaceAll("\r", "").replaceAll("\n", "").replaceAll("\r\n", "").replaceAll("\\s+", " ").replaceAll("\\s*$", "");
  }  
}
