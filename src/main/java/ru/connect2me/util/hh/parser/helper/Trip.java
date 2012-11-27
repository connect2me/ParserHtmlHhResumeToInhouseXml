package ru.connect2me.util.hh.parser.helper;

import java.util.regex.*;
import ru.connect2me.util.hh.parser.config.ParserHtmlHhResumeToInhouseXmlException;

/**
 *  Получение "готовность к командировкам"
 * 
 * @author Зайнуллин Радик
 */
public class Trip {
  public String get(String txt) throws ParserHtmlHhResumeToInhouseXmlException {
    Matcher matcher = Pattern.compile("Готов(а)? к командировкам|Готов(а)? к редким командировкам|Ready for business trips", Pattern.CASE_INSENSITIVE).matcher(txt);
    if (matcher.find()) return matcher.group();
    else return "not found";
  }  
}