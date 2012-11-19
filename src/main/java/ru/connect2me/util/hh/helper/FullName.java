package ru.connect2me.util.hh.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ru.connect2me.util.hh.config.ParserHtmlHhResumeToInhouseXmlException;

/**
 * 
 * Получение ФИО из резюме
 * @author Зайнуллин
 */
public class FullName {
  public String get(String txt) throws ParserHtmlHhResumeToInhouseXmlException {
    Matcher matcher = Pattern.compile("resume__personal__name['\"]?\\s*\\>\\s*([\\pL]+[\\s+\\pL]*)\\s*\\<\\/div").matcher(txt);
    String fullname = "not found";
    if (matcher.find()) fullname = matcher.group(1);
    else throw new ParserHtmlHhResumeToInhouseXmlException("Серьезная ошибка - не смогли получить ФИО из резюме.");
    return fullname.trim();
  }  
}