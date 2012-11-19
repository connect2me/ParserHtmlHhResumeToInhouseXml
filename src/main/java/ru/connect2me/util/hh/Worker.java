package ru.connect2me.util.hh;

import ru.connect2me.util.hh.config.Module;
import ru.connect2me.util.hh.config.ParserHtmlHhResumeToInhouseXmlException;
import ru.connect2me.util.hh.config.XMLConfiguration;
import ru.connect2me.util.hh.helper.*;

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
    String id = ctx.getBean("getId", Id.class).get(html);                      // 1. Получение id резюме
    String fullName = ctx.getBean("getFullName", FullName.class).get(html);    // 2. Получение ФИО из резюме    
    String cellphone = ctx.getBean("getCellphone", Cellphone.class).get(html); // 3. Получение номера мобильного телефона из резюме    
    String homephone = ctx.getBean("getHomephone", Homephone.class).get(html); // 4. Получение номера домашнего телефона из резюме    
    String location = ctx.getBean("getLocation", Location.class).get(html);    // 5. Получение местожительства человека из резюме  
    String address = ctx.getBean("getAddress", Address.class).get(html);       // 6. Получение почтового адреса из резюме  
    String birthdate = ctx.getBean("getBirthdate", Birthdate.class).get(html); // 7. Получение дня рождения из резюме
    String relocation = ctx.getBean("getRelocation", Relocation.class).get(html); // 8. Получение готовности переехать из резюме
    String salary = ctx.getBean("getSalary", Salary.class).get(html);             // 9. Получение желаемой зарплаты
    String direction = ctx.getBean("getDirection", Direction.class).get(html);   // 10. Получение желаемой специализации по которой апликант ищет работу
    String education = ctx.getBean("getEducation", Education.class).get(html);   // 11. Получение списка полученных образований аппликанта
    String experience = ctx.getBean("getExperience", Experience.class).get(html);// 12. Получение списка полученных образований аппликанта
    

    return "Hello World!";
  }
}
