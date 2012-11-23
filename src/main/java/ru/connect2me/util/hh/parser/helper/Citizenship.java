package ru.connect2me.util.hh.parser.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ru.connect2me.util.hh.parser.config.ParserHtmlHhResumeToInhouseXmlException;

/**
 *  Получение гражданства
 * 
 * @author Зайнуллин Радик
 */
public class Citizenship {
  public String get(String txt) throws ParserHtmlHhResumeToInhouseXmlException {
    Matcher matcher = Pattern.compile("Гражданство:\\s+(\\pL+)\\s+", Pattern.DOTALL|Pattern.CASE_INSENSITIVE).matcher(txt);
    String citizenship = "not found";
    if (matcher.find()) citizenship = matcher.group(1);
    return citizenship;
  }  
}
