package ru.connect2me.util.hh.parser.helper;

import java.util.regex.*;
import ru.connect2me.util.hh.parser.config.ParserHtmlHhResumeToInhouseXmlException;

/**
 *  Получение "разрешения на работу"
 * 
 * @author Зайнуллин Радик
 */
public class WorkPermit {
  public String get(String txt) throws ParserHtmlHhResumeToInhouseXmlException {
    Matcher matcher = Pattern.compile("(?:Разрешение на работу:|Work permit:)\\s+(\\pL+)\\s+", Pattern.DOTALL|Pattern.CASE_INSENSITIVE).matcher(txt);
    if (matcher.find()) return matcher.group(1);
    return "not found";
  }  
}