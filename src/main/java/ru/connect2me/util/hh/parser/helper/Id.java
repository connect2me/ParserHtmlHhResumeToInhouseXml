package ru.connect2me.util.hh.parser.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ru.connect2me.util.hh.parser.config.ParserHtmlHhResumeToInhouseXmlException;

/**
 *  Получение id резюме
 * 
 * @author Зайнуллин Радик
 */
public class Id {
  public String get(String txt) throws ParserHtmlHhResumeToInhouseXmlException {
    Matcher matcher = Pattern.compile("resumeId:\\s+'?(\\d+)'?\\s+").matcher(txt);
    if (matcher.find()) return matcher.group(1);
    else throw new ParserHtmlHhResumeToInhouseXmlException("Серьезная ошибка - не смогли получить id резюме.");
  }
}