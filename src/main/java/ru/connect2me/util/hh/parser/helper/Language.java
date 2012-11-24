package ru.connect2me.util.hh.parser.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ru.connect2me.util.hh.parser.config.ParserHtmlHhResumeToInhouseXmlException;

/**
 * Получение "знание языков"
 *
 * @author Зайнуллин Радик
 */
public class Language {

  public String get(String txt) throws ParserHtmlHhResumeToInhouseXmlException {
    StringBuilder sbLanguage = new StringBuilder();
    int pos = txt.lastIndexOf("Знание языков");
    Matcher matcher = Pattern.compile("((Русский|Английский|Немецкий|Финский|Итальянский|Татарский|Испанский|Португальский|Румынский) — \\pL+)", Pattern.DOTALL | Pattern.CASE_INSENSITIVE).matcher(txt);

    if (pos > 0) {
      matcher.region(pos, txt.length());
      while (matcher.find()) sbLanguage.append(matcher.group(1) + "#");
      
      return sbLanguage.toString().replaceAll("#$", "");
    } else return "not found";
  }
}