package ru.connect2me.util.hh;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Properties;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import ru.connect2me.util.hh.config.Module;
import ru.connect2me.util.hh.config.ParserHtmlHhResumeToInhouseXmlException;
import ru.connect2me.util.hh.config.XMLConfiguration;
import ru.connect2me.util.hh.helper.*;
import ru.connect2me.util.hh.util.Check;

/**
 * Входная точка сервиса
 *
 * @author Зайнуллин Радик
 * @version 1.0
 * @since 2012.11.18
 */
public class Worker extends Module implements Command {
  public Worker() throws ParserHtmlHhResumeToInhouseXmlException {
    super(new XMLConfiguration(Worker.class.getResourceAsStream("/config.xml")));
  }

  @Override
  public String execute(String html) throws ParserHtmlHhResumeToInhouseXmlException {
    // Проверка наличия входного текста
    if (html == null || html.matches("\\s*")) {
      throw new ParserHtmlHhResumeToInhouseXmlException("Входной файл пустой или null.");
    }
    // Проверка входной xml на well-formed
    Check check = ctx.getBean("check", Check.class);
    if (!check.isWellFormed(html)) {
      throw new ParserHtmlHhResumeToInhouseXmlException("Входной файл не является xml well-formed!");
    }
    // Чистка входного текста &apos; -> ' &#xD; -> <br/>
    html = html.replaceAll("&apos;", "'");
    // Парсинг входного текста
    Properties props = parse(html);
    // Получение выходного xml
    String result = convert(props);
    
    return result;
  }

  private String convert(Properties props) throws ParserHtmlHhResumeToInhouseXmlException {
    try {
      StringWriter writer = new StringWriter();
      StreamResult sr = new StreamResult(writer);
      StringReader reader = new StringReader("<t></t>");
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
      transformer.transform(new StreamSource(reader), sr);
      return writer.toString();
    } catch (TransformerException ex) {
      throw new ParserHtmlHhResumeToInhouseXmlException("Не удалось сделать xlst преобразование");
    } 
  }

  private Properties parse(String html) throws ParserHtmlHhResumeToInhouseXmlException {
    Properties props = new Properties();
    String id = ctx.getBean("id", Id.class).get(html);                      // 1. Получение id резюме
    String fullname = ctx.getBean("fullname", FullName.class).get(html);    // 2. Получение ФИО из резюме    
    String email = ctx.getBean("email", Email.class).get(html);             // 3. Получение email из резюме
    String cellphone = ctx.getBean("cellphone", Cellphone.class).get(html); // 4. Получение номера мобильного телефона из резюме    
    String homephone = ctx.getBean("homephone", Homephone.class).get(html); // 5. Получение номера домашнего телефона из резюме    
    String location = ctx.getBean("location", Location.class).get(html);    // 6. Получение местожительства человека из резюме  
    String address = ctx.getBean("address", Address.class).get(html);       // 7. Получение почтового адреса из резюме  
    String birthdate = ctx.getBean("birthdate", Birthdate.class).get(html); // 8. Получение дня рождения из резюме
    String relocation = ctx.getBean("relocation", Relocation.class).get(html); // 9. Получение готовности переехать из резюме
    String salary = ctx.getBean("salary", Salary.class).get(html);             // 10. Получение желаемой зарплаты
    String direction = ctx.getBean("direction", Direction.class).get(html);    // 11. Получение желаемой специализации по которой апликант ищет работу
    String education = ctx.getBean("education", Education.class).get(html);    // 12. Получение списка полученных образований аппликанта
    String experience = ctx.getBean("experience", Experience.class).get(html); // 13. Получение списка полученных образований аппликанта
    String citizenship = ctx.getBean("citizenship", Citizenship.class).get(html);// 14. Получение "гражданства"
    String workPermit = ctx.getBean("workPermit", WorkPermit.class).get(html);   // 15. Получение "разрешения на работу"
    String language = ctx.getBean("language", Language.class).get(html);         // 16. Получение "знание языков"
    String skills = ctx.getBean("skills", Skills.class).get(html);               // 17. Получение "ключевых навыков"

    props.put("id", id);
    props.put("fullname", fullname);
    props.put("email", email);    
    props.put("cellphone", cellphone);
    props.put("homephone", homephone);
    props.put("location", location);
    props.put("address", address);
    props.put("birthdate", birthdate);
    props.put("relocation", relocation);
    props.put("salary", salary);
    props.put("direction", direction);
    props.put("education", education);
    props.put("experience", experience);
    props.put("citizenship", citizenship);
    props.put("workPermit", workPermit);
    props.put("language", language);
    props.put("skills", skills);

    return props;
  }
}
