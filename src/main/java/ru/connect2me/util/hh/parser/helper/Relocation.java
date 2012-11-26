package ru.connect2me.util.hh.parser.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ru.connect2me.util.hh.parser.config.ParserHtmlHhResumeToInhouseXmlException;

/**
 * Получение готовности переехать из резюме
 * @author Зайнуллин Радик
 */
public class Relocation {
  public String get(String txt) throws ParserHtmlHhResumeToInhouseXmlException {
    Matcher matcher = Pattern.compile("(не )?готов(а)? к переезду|(not )?ready to relocate", Pattern.CASE_INSENSITIVE).matcher(txt);
    if (matcher.find()) return matcher.group();
    else return "not found";
  }    
}
// not ready to relocate