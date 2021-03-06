package ru.connect2me.util.hh.parser.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ru.connect2me.util.hh.parser.helper.enums.EnumFormatInputDate;

/**
 * Получение дня рождения из резюме
 * 
 * @author Зайнуллин Радик
 */
public class Birthdate {
  public String get(String txt){
    Matcher matcher = Pattern.compile("\\((\\d{1,2} of (January|February|March|April|May|June|July|August|September|October|November|December) 19\\d{2})\\)|"
            + "\\((\\d{1,2} (января|февраля|марта|апреля|мая|июня|июля|августа|сентября|октября|ноября|декабря) 19\\d{2})\\)").matcher(txt);
    String formattedDate = "not found"; 
    if (matcher.find()) {
      for (EnumFormatInputDate cur: EnumFormatInputDate.values()) {
        String str = matcher.group();
        if (str.matches(cur.regexp())) {
          System.out.println(str);
          System.out.println("regexp " + cur.regexp() + ", replacement " + cur.replacement());
          formattedDate = str.replaceAll(cur.regexp(), cur.replacement());
        }
      }
    } 
    if (!formattedDate.equals("not found")&&formattedDate.length() < 10) {
      formattedDate = "0" + formattedDate;
    }
    System.out.println(formattedDate);
    return formattedDate;
  }  
}