package ru.connect2me.util.hh.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ru.connect2me.util.hh.config.ParserHtmlHhResumeToInhouseXmlException;

/**
 *  Получение номера мобильного телефона из резюме
 * 
 * @author Зайнуллин Радик
 */
public class Cellphone {
  public String get(String txt) throws ParserHtmlHhResumeToInhouseXmlException {
    Matcher matcher = Pattern.compile("resume__contacts__phone__number").matcher(txt);
    int pos = -1;
    String cell = "not found";
    if (matcher.find()) pos = matcher.end();

    if (pos > -1) {
      matcher = Pattern.compile("(\\+7.*?)\\<", Pattern.DOTALL).matcher(txt);
      matcher.region(pos, txt.length());
      if (matcher.find()) cell = matcher.group(1);
    }
    return cell.trim();
  }   
}