package ru.connect2me.util.hh;

import ru.connect2me.util.hh.config.Module;
import ru.connect2me.util.hh.config.ParserHtmlHhResumeToInhouseXmlException;
import ru.connect2me.util.hh.config.XMLConfiguration;
import ru.connect2me.util.hh.helper.Cellphone;
import ru.connect2me.util.hh.helper.Check;
import ru.connect2me.util.hh.helper.FullName;
import ru.connect2me.util.hh.helper.Id;

/**
 * Входная точка сервиса
 *
 * @author Зайнуллин Радик
 * @version 1.0
 * @since 2012.11.18
 */
public class Worker extends Module implements Command {
  public Worker() throws ParserHtmlHhResumeToInhouseXmlException {
    super(new XMLConfiguration(Main.class.getResourceAsStream("/config.xml")));
  }

  @Override
  public String execute(String html) throws ParserHtmlHhResumeToInhouseXmlException {
    // Проверка наличия входного текста
    if (html == null || html.matches("\\s*")) throw new ParserHtmlHhResumeToInhouseXmlException("Входной файл пустой или null.");
    // Проверка входной xml на well-formed
    Check check = ctx.getBean("check", Check.class);
    if (!check.isWellFormed(html)) throw new ParserHtmlHhResumeToInhouseXmlException("Входной файл не является xml well-formed!");
    // Парсинг входного текста
    String id = ctx.getBean("getId", Id.class).get(html); // 1. Получение id резюме
    String fullName = ctx.getBean("getFullName", FullName.class).get(html); // 2. Получение ФИО из резюме    
    String cellphone = ctx.getBean("getCellphone", Cellphone.class).get(html); // 3. Получение номера мобильного телефона из резюме    
    

    return "Hello World!";
  }
}
