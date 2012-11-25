package ru.connect2me.util.hh.parser;

import java.io.*;
import java.util.Enumeration;
import java.util.Properties;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import ru.connect2me.util.hh.parser.config.*;
import ru.connect2me.util.hh.parser.helper.*;
import ru.connect2me.util.hh.parser.util.Check;

/**
 * Входная точка сервиса
 * @author Зайнуллин Радик
 * @version 1.0
 * @since 2012.11.18
 */
public class Worker extends Module implements ParserHtmlHhResumeToInhouseXml {
  public Worker() throws ParserHtmlHhResumeToInhouseXmlException {
    super(new XMLConfiguration(Worker.class.getResourceAsStream("/config.xml")));
  }

  @Override
  public String execute(String html) throws ParserHtmlHhResumeToInhouseXmlException {
    logger.info("Разбор входного hh-резюме.");
    logger.info(html);
//    logger.debug("debug - Hello from jar - Worker");
//    logger.error("error - Hello from jar - Worker");
//    logger.warn("warn - Hello from jar - Worker");    
//    logger.trace("trace - Hello from jar - Worker");    
    // Проверка наличия входного текста
    if (html == null || html.matches("\\s*")) throw new ParserHtmlHhResumeToInhouseXmlException("Входной файл пустой или null.");
    // Проверка входной xml на well-formed
    Check check = ctx.getBean("check", Check.class);
    if (!check.isWellFormed(html)) throw new ParserHtmlHhResumeToInhouseXmlException("Входной файл не является xml well-formed!");
    // Чистка входного текста &apos; -> ' &#xD; -> <br/>
    html = html.replaceAll("&apos;", "'");
    // Парсинг входного текста
    Properties props = parse(html);
    
    // Запишем в лог полученные парсером величины
    Enumeration eProps = props.propertyNames();
    while (eProps.hasMoreElements()) { 
        String key = (String) eProps.nextElement(); 
        String value = props.getProperty(key); 
        logger.info(key + " => " + value);
    }
    // Получение выходного xml
    return convert(props);
  }

  private String convert(Properties props) throws ParserHtmlHhResumeToInhouseXmlException {
    try {
      TransformerFactory tFactory = TransformerFactory.newInstance();
      Transformer transformer = tFactory.newTransformer(new StreamSource(classLoader.getResourceAsStream("converter.xsl")));
      transformer.setParameter("pId", props.getProperty("id"));
      transformer.setParameter("pFullname", props.getProperty("fullname"));
      transformer.setParameter("pEmail", props.getProperty("email"));
      transformer.setParameter("pCell", props.getProperty("cellphone"));
      transformer.setParameter("pPhone", props.getProperty("homephone"));
      transformer.setParameter("pLocation", props.getProperty("location"));
      transformer.setParameter("pAddress", props.getProperty("address"));
      transformer.setParameter("pBirthdate", props.getProperty("birthdate"));
      transformer.setParameter("pRelocation", props.getProperty("relocation"));
      transformer.setParameter("pSalary", props.getProperty("salary"));
      transformer.setParameter("pDirection", props.getProperty("direction"));
      transformer.setParameter("pEducation", props.getProperty("education"));
      transformer.setParameter("pExperience", props.getProperty("experience"));
      transformer.setParameter("pCitizenship", props.getProperty("citizenship"));
      transformer.setParameter("pWorkPermit", props.getProperty("workPermit"));
      transformer.setParameter("pLanguage", props.getProperty("language"));
      transformer.setParameter("pSkills", props.getProperty("skills"));
      transformer.setParameter("pTrip", props.getProperty("trip"));
      transformer.setParameter("pSchedule", props.getProperty("schedule"));
      
      StringWriter writer = new StringWriter();      
      transformer.transform(new StreamSource(new StringReader("<t></t>")), new StreamResult(writer));
      return writer.toString();
    } catch (TransformerException ex) {
      throw new ParserHtmlHhResumeToInhouseXmlException("Не удалось сделать xlst преобразование" + ex.getMessage());
    } 
  }

  private Properties parse(String html) throws ParserHtmlHhResumeToInhouseXmlException {
    Properties props = new Properties();
    props.put("id", ctx.getBean("id", Id.class).get(html));                  // 1. Получение id резюме
    props.put("fullname", ctx.getBean("fullname", FullName.class).get(html));// 2. Получение ФИО из резюме    
    props.put("email", ctx.getBean("email", Email.class).get(html));         // 3. Получение email из резюме   
    props.put("cellphone", ctx.getBean("cellphone", Cellphone.class).get(html));// 4. Получение номера мобильного телефона из резюме    
    props.put("homephone", ctx.getBean("homephone", Homephone.class).get(html));// 5. Получение номера домашнего телефона из резюме    
    props.put("location", ctx.getBean("location", Location.class).get(html));   // 6. Получение местожительства человека из резюме  
    props.put("address", ctx.getBean("address", Address.class).get(html));      // 7. Получение почтового адреса из резюме  
    props.put("birthdate", ctx.getBean("birthdate", Birthdate.class).get(html)); // 8. Получение дня рождения из резюме
    props.put("relocation",ctx.getBean("relocation", Relocation.class).get(html));//9. Получение готовности переехать из резюме
    props.put("salary", ctx.getBean("salary", Salary.class).get(html));          // 10. Получение желаемой зарплаты
    props.put("direction", ctx.getBean("direction", Direction.class).get(html)); // 11. Получение желаемой специализации по которой апликант ищет работу
    props.put("education", ctx.getBean("education", Education.class).get(html)); // 12. Получение списка полученных образований аппликанта
    props.put("experience", ctx.getBean("experience", Experience.class).get(html));// 13. Получение списка полученных образований аппликанта
    props.put("citizenship", ctx.getBean("citizenship", Citizenship.class).get(html));// 14. Получение "гражданства"
    props.put("workPermit", ctx.getBean("workPermit", WorkPermit.class).get(html));   // 15. Получение "разрешения на работу"
    props.put("language", ctx.getBean("language", Language.class).get(html)); // 16. Получение "знание языков"
    props.put("skills", ctx.getBean("skills", Skills.class).get(html));       // 17. Получение "ключевых навыков"
    props.put("trip", ctx.getBean("trip", Trip.class).get(html));             // 18. Получение "готовность к командировкам"   
    props.put("schedule", ctx.getBean("schedule", Schedule.class).get(html)); // 19. Получение "занятость - полная, частичная и т.п."   
    return props;
  }
}