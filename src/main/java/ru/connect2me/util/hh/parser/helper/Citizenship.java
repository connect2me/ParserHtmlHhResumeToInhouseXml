package ru.connect2me.util.hh.parser.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ru.connect2me.util.hh.parser.config.ParserHtmlHhResumeToInhouseXmlException;

/**
 *  Получение "гражданства" аппликанта
 * 
 * @author Зайнуллин Радик
 */
public class Citizenship {
  public String get(String txt) throws ParserHtmlHhResumeToInhouseXmlException {
    // Citizenship: Russia
    Matcher matcher = Pattern.compile("(?:Гражданство:|Citizenship:)\\s+(\\pL+)\\s+", Pattern.DOTALL|Pattern.CASE_INSENSITIVE).matcher(txt);
    if (matcher.find()) return matcher.group(1);
    else return "not found";
  }  
}