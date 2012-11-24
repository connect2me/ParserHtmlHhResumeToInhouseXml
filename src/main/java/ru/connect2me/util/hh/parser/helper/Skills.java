package ru.connect2me.util.hh.parser.helper;

import java.io.ByteArrayInputStream;
import java.util.regex.*;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import ru.connect2me.util.hh.parser.config.ParserHtmlHhResumeToInhouseXmlException;

/**
 * Получение "ключевых навыков"
 *
 * @author Зайнуллин Радик
 */
public class Skills {
  public String get(String txt) throws ParserHtmlHhResumeToInhouseXmlException {
    Matcher matcher = Pattern.compile("<tbody>.+?Ключевые навыки.+?</tbody>", Pattern.DOTALL | Pattern.CASE_INSENSITIVE).matcher(txt);
    if (matcher.find()) {
      try {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true); // never forget this!
        String skills = matcher.group();
        Document doc = factory.newDocumentBuilder().parse(new ByteArrayInputStream(skills.getBytes("UTF-8")));
        XPath xpath = XPathFactory.newInstance().newXPath();

        NodeList nodes = (NodeList) xpath.evaluate("/tbody/tr/td/div[position()=2]", doc, XPathConstants.NODESET);
        if (nodes.getLength() > 0) {
          String temp = nodes.item(0).getTextContent();
          StringBuilder sbSkills = new StringBuilder();          
          for (String cur: temp.split("\n")) {
            if (!cur.matches("\\s+")) sbSkills.append(cur.trim() + "#");  
          }
          return sbSkills.toString().replaceAll("#$", "");
        } else return "not found";
      } catch (Exception ex) {
        return "not found";
      }
    } else return "not found";
  }
}