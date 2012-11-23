package ru.connect2me.util.hh.parser.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ru.connect2me.util.hh.parser.config.ParserHtmlHhResumeToInhouseXmlException;

/**
 *  Получение "разрешения на работу"
 * 
 * @author Зайнуллин Радик
 */
public class WorkPermit {
  public String get(String txt) throws ParserHtmlHhResumeToInhouseXmlException {
    Matcher matcher = Pattern.compile("Разрешение на работу:\\s+(\\pL+)\\s+", Pattern.DOTALL|Pattern.CASE_INSENSITIVE).matcher(txt);
    String workPermit = "not found";
    if (matcher.find()) workPermit = matcher.group(1);
    return workPermit;
  }  
}
