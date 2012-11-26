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
  /*
   <div class="resume-block">
   <div class="resume-block__title m-resume-block__title_sub">
   <div class="resume-block__title__text">
   <span>
   Languages
   </span>
   </div>
   </div>
   <div>
   Russian — native
   </div>
   <div>
   English — fluent
   </div>
   <div>
   German — basic knowledge
   </div>
   </div>
   */

  private String getEnglish(String txt) {
    StringBuilder sbLanguage = new StringBuilder();
    String regExp = "<div class=\"resume-block__title__text\">\\s*<span>\\s*Languages\\s*</span>\\s*</div>";
    Matcher m = Pattern.compile(regExp).matcher(txt);
    int pos = -1;
    if (m.find()) pos = m.end();
    
    Matcher matcher = Pattern.compile("((Russian|English|German|Finnish|Italian|French|Spanish|Tatar|Portuguese|Romanian) — \\pL+)", Pattern.DOTALL | Pattern.CASE_INSENSITIVE).matcher(txt);

    if (pos > 0) {
      matcher.region(pos, txt.length());
      while (matcher.find()) {
        sbLanguage.append(matcher.group(1) + "#");
      }
      return sbLanguage.toString().replaceAll("#$", "");
    } else {
      return "not found";
    }    
  }

  private String getRussian(String txt) {
    StringBuilder sbLanguage = new StringBuilder();
    int pos = txt.lastIndexOf("Знание языков");
    Matcher matcher = Pattern.compile("((Русский|Английский|Немецкий|Финский|Итальянский|Татарский|Испанский|Португальский|Румынский) — \\pL+)", Pattern.DOTALL | Pattern.CASE_INSENSITIVE).matcher(txt);

    if (pos > 0) {
      matcher.region(pos, txt.length());
      while (matcher.find()) {
        sbLanguage.append(matcher.group(1) + "#");
      }
      return sbLanguage.toString().replaceAll("#$", "");
    } else {
      return "not found";
    }
  }

  public String get(String txt) throws ParserHtmlHhResumeToInhouseXmlException {
    String result = getRussian(txt);
    if (result.equals("not found")) result = getEnglish(txt);
    return result;
  }
}