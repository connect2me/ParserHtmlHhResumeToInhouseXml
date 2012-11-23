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
    Matcher matcher = Pattern.compile("\\((\\d{1,2} (января|февраля|марта|апреля|мая|июня|июля|августа|сентября|октября|ноября|декабря) 19\\d{2})\\)").matcher(txt);
    String birthdate = "not found";
    if (matcher.find()) {
      birthdate = matcher.group(1);
    }
    return  birthdate;
  }    
}