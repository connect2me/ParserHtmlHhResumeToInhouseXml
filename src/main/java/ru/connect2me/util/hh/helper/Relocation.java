package ru.connect2me.util.hh.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ru.connect2me.util.hh.config.ParserHtmlHhResumeToInhouseXmlException;

/**
 * Получение готовности переехать из резюме
 * @author Зайнуллин Радик
 */
public class Relocation {
  public String get(String txt) throws ParserHtmlHhResumeToInhouseXmlException {
    Matcher matcher = Pattern.compile("готов(а)? к переезду \\(Россия\\)", Pattern.CASE_INSENSITIVE).matcher(txt);
    if (matcher.find()) return matcher.group();
    else return "not found";
  }    
}