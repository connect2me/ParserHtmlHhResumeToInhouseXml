package ru.connect2me.util.hh.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ru.connect2me.util.hh.config.ParserHtmlHhResumeToInhouseXmlException;

/**
 *  Получение email из резюме
 * 
 * @author Зайнуллин Радик
 */
public class Email {
  public String get(String txt) throws ParserHtmlHhResumeToInhouseXmlException {
    Matcher matcher = Pattern.compile("mailto:(\\S+@(?!hh.ru)[^\\s'\"]+)").matcher(txt);
    String email = "not found";
    if (matcher.find()) email = matcher.group(1);
    else throw new ParserHtmlHhResumeToInhouseXmlException("Серьезная ошибка - не смогли получить email из резюме.");
    return email.trim();
  }    
}
