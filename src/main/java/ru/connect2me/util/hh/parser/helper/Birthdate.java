package ru.connect2me.util.hh.parser.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ru.connect2me.util.hh.parser.config.ParserHtmlHhResumeToInhouseXmlException;

/**
 * Получение дня рождения из резюме
 * 
 * @author Зайнуллин Радик
 */
public class Birthdate {
  public String get(String txt) throws ParserHtmlHhResumeToInhouseXmlException {
    String resultRussian = getRussian(txt);
    String resultEnglish = getEnglish(txt);
    if (resultRussian.equals("not found")) return resultEnglish;
    else return "not found"; 
  }   
  // (23 of March 1984)
  private String getRussian(String txt){
    Matcher matcher = Pattern.compile("\\((\\d{1,2} (января|февраля|марта|апреля|мая|июня|июля|августа|сентября|октября|ноября|декабря) 19\\d{2})\\)").matcher(txt);
    if (matcher.find()) return matcher.group(1);
    else return "not found";
  }
  private String getEnglish(String txt){
    Matcher matcher = Pattern.compile("\\((\\d{1,2} of (January|February|March|April|May|June|July|August|September|October|November|December) 19\\d{2})\\)").matcher(txt);
    if (matcher.find()) return matcher.group(1);
    else return "not found";
  }  
}