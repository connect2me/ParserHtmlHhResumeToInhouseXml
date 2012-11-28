package ru.connect2me.util.hh.parser.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ru.connect2me.util.hh.parser.config.ParserHtmlHhResumeToInhouseXmlException;

/**
 * 
 * Получение ФИО из резюме
 * @author Зайнуллин
 */
public class FullName {
  public String get(String txt) throws ParserHtmlHhResumeToInhouseXmlException {
    Matcher matcher = Pattern.compile("resume__personal__name['\"]?\\s*\\>\\s*([-\\pL]+[-\\s+\\pL]*)\\s*\\<\\/div").matcher(txt);
     if (matcher.find()) return matcher.group(1).trim().replaceFirst("-\\s+", "");
     else throw new ParserHtmlHhResumeToInhouseXmlException("Серьезная ошибка - не смогли получить ФИО из резюме.");
  }  
}