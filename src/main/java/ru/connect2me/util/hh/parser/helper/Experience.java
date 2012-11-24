package ru.connect2me.util.hh.parser.helper;

import java.io.ByteArrayInputStream;
import java.util.regex.*;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import org.w3c.dom.*;
import ru.connect2me.util.hh.parser.config.ParserHtmlHhResumeToInhouseXmlException;
import ru.connect2me.util.hh.parser.helper.enums.EnumCleaningInputForExperience;

/**
 *  Получение списка - опыт работы
 * 
 * @author Зайнуллин Радик
 */
public class Experience {
  public String get(String txt) throws ParserHtmlHhResumeToInhouseXmlException {
    // чистим входной xml
    for (EnumCleaningInputForExperience cur: EnumCleaningInputForExperience.values()) txt = txt.replaceAll(cur.regexp(), "");
    //FileUtils.writeStringToFile(new File("c:\\proj\\XSLT\\src\\main\\resources\\refined.xml"), txt, "UTF-8");

    Matcher matcher = Pattern.compile("<div class=\"resume__experience__item\">((\\s*<div(\\s?class=\\s?\"resume__experience__desc\")?\\s*>.+?</div>\\s*)+\\s*)</div>", Pattern.DOTALL).matcher(txt);
    StringBuilder sbExperience = new StringBuilder("<root>");
    boolean found = false;
    while (matcher.find()) {
      sbExperience.append("<item>");
      sbExperience.append(matcher.group(1));
      sbExperience.append("</item>");
      found = true;
    }    
    sbExperience.append("</root>");
    
    if (!found) return "not found";
    
    StringBuffer sbExperienceRes = new StringBuffer();
    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      factory.setNamespaceAware(true); // never forget this!
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document doc = builder.parse(new ByteArrayInputStream(sbExperience.toString().getBytes("UTF-8")));
      XPath xpath = XPathFactory.newInstance().newXPath();

      NodeList nodes = (NodeList) xpath.evaluate("/root/item", doc, XPathConstants.NODESET);      
      for (int i = 0; i < nodes.getLength(); i++) {
        Document newXmlDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Node node = nodes.item(i);
        Node copyNode = newXmlDocument.importNode(node, true);
        newXmlDocument.appendChild(copyNode);
          
        NodeList nodesPeriod = (NodeList) xpath.evaluate("/item/div[position()=1]", newXmlDocument, XPathConstants.NODESET);
        String period = nodesPeriod.item(0).getTextContent().trim().replaceAll("\r", "").replaceAll("\n", "").replaceAll("\r\n", "").replaceAll("\\s+", " ");
        String fromDate = period.replaceAll("(.+)\\—.*", "$1").trim();
        String toDate = period.replaceAll(".+\\—(.*)", "$1").trim();
        if (toDate.trim().equals("")) toDate = "продолжаю";
        
        // /root/item/div[position()=2]/strong
        NodeList nodesOrganization = (NodeList) xpath.evaluate("/item/div[position()=2]/strong", newXmlDocument, XPathConstants.NODESET);
        String organization = nodesOrganization.item(0).getTextContent().trim().replaceAll("\r", "").replaceAll("\n", "").replaceAll("\r\n", "").replaceAll("\\s+", " ");
        
        // industry /root/item/div[position()=2]/span
        NodeList nodesIndustry = (NodeList) xpath.evaluate("/item/div[position()=2]/span[@class=\"resume__experience__industry\"]", newXmlDocument, XPathConstants.NODESET);
        String industry = "not found"; 
        if (nodesIndustry.getLength()>0) industry = nodesIndustry.item(0).getTextContent().trim().replaceAll("\r", "").replaceAll("\n", "").replaceAll("\r\n", "").replaceAll("\\s+", " ");
        
        NodeList nodesPosition = (NodeList) xpath.evaluate("/item/div[position()=3]/strong", newXmlDocument, XPathConstants.NODESET);
        String position = nodesPosition.item(0).getTextContent().trim().replaceAll("\r", "").replaceAll("\n", "").replaceAll("\r\n", "").replaceAll("\\s+", " ");        
        NodeList nodesAssumption = (NodeList) xpath.evaluate("/item/div[position()=4]", newXmlDocument, XPathConstants.NODESET);
        String assumption = nodesAssumption.item(0).getTextContent().trim().replaceAll("\r", "").replaceAll("\n", "").replaceAll("\r\n", "").replaceAll("\\s+", " ");        
        
        sbExperienceRes.append("fromDate#" + fromDate + "#toDate#" +toDate+ "#period#" + period + "#organization#" + organization  + "#industry#" + industry + "#position#" + position + "#assumption#" + assumption).append("##");
       }
    } catch (Exception ex) {
      // Написать в лог
      return "not found";
    }
    return sbExperienceRes.toString().replaceAll("##$", "");
  }  
}