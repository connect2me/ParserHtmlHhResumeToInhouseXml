package ru.connect2me.util.hh.parser.helper;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import ru.connect2me.util.hh.parser.config.ParserHtmlHhResumeToInhouseXmlException;

/**
 *  Получение "ключевых навыков"
 * 
 * @author Зайнуллин Радик
 */
public class Skills {
  public String get(String txt) throws ParserHtmlHhResumeToInhouseXmlException {
    Matcher matcher = Pattern.compile("<tbody>.+?Ключевые навыки.+?</tbody>", Pattern.DOTALL|Pattern.CASE_INSENSITIVE).matcher(txt);

    String skills = "not found";
    if (matcher.find()) skills = matcher.group();

    StringBuffer sbSkills = new StringBuffer();
    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      factory.setNamespaceAware(true); // never forget this!
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document doc = builder.parse(new ByteArrayInputStream(skills.toString().getBytes("UTF-8")));
      XPath xpath = XPathFactory.newInstance().newXPath();

      NodeList nodes = (NodeList) xpath.evaluate("/tbody/tr/td/div[position()=2]", doc, XPathConstants.NODESET);  
      String temp = "not found";
      String[] skillsList = null;
      ArrayList<String> skillsList1 = new ArrayList<String>(); 
      if (nodes.getLength()>0){
        temp = nodes.item(0).getTextContent();
        temp = temp.replaceFirst("\n+", "");
        skillsList = temp.split("\n");
        for (int i = 0; i < skillsList.length; i++){
          skillsList[i] = skillsList[i].trim();
          if (!skillsList[i].matches("\\s*")) skillsList1.add(skillsList[i]);
        }
        for (String cur : skillsList1) sbSkills.append(cur+"#");
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    skills = sbSkills.toString().replaceAll("#$", "");
    return skills;
  }  
}