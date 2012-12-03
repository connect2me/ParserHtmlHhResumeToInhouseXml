package ru.connect2me.util.hh.parser.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ru.connect2me.util.hh.parser.config.ParserHtmlHhResumeToInhouseXmlException;

/**
 * В резюме на hh.ru есть заголовок "Желаемая должность и зарплата".
 * Вот "Желаемая должность" и есть поле ResumeName
 * @author Зайнуллин Радик
 * @since 2012.12.03
 */
public class ResumeName {
    public String get(String txt) throws ParserHtmlHhResumeToInhouseXmlException {
      String regExp = "<div class=\"resume__position__title\">\\s*(.+?)\\s*</div>";
      Matcher matcher = Pattern.compile(regExp, Pattern.DOTALL | Pattern.CASE_INSENSITIVE).matcher(txt);      
      if (matcher.find()) return matcher.group(1);
      else return "not found";
  }
}