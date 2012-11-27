package ru.connect2me.util.hh.parser.helper;

import java.io.ByteArrayInputStream;
import java.util.regex.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.*;
import org.w3c.dom.*;
import ru.connect2me.util.hh.parser.config.ParserHtmlHhResumeToInhouseXmlException;

/**
 *  Получение списка полученных образований аппликанта
 * 
 * @author Зайнуллин Радик
 */
public class Education {
  public String getRussian(String txt) throws ParserHtmlHhResumeToInhouseXmlException {
    Matcher matcher = Pattern.compile("Образование.+<table.+<tbody>(.+)</tbody>.+</table>.+Знание языков", Pattern.DOTALL).matcher(txt);
    if (matcher.find()) {
      StringBuilder sb = new StringBuilder();      
      String education = "<root>" + matcher.group(1) + "</root>";
      try {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true); // never forget this!
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new ByteArrayInputStream(education.getBytes("UTF-8")));
        XPath xpath = XPathFactory.newInstance().newXPath();

        NodeList nodes = (NodeList) xpath.evaluate("/root/tr", doc, XPathConstants.NODESET);
        for (int j = 0; j < nodes.getLength(); j++) {
          Document newXmlDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
          Node node = nodes.item(j);
          Node copyNode = newXmlDocument.importNode(node, true);
          newXmlDocument.appendChild(copyNode);
          NodeList nodesYear = (NodeList) xpath.evaluate("/tr/td/text()", newXmlDocument, XPathConstants.NODESET);
          NodeList nodesOrganization = (NodeList) xpath.evaluate("/tr/td/div[count(@*)=0]", newXmlDocument, XPathConstants.NODESET);
          NodeList nodesSpecialty = (NodeList) xpath.evaluate("/tr/td/div[@class='resume__education__org']", newXmlDocument, XPathConstants.NODESET);
          String year = nodesYear.item(0).getTextContent().trim().replaceAll("\r\n|\r|\n", "");
          String organization = nodesOrganization.item(0).getTextContent().trim().replaceAll("\r\n|\r|\n", "");
          String specialty = nodesSpecialty.item(0).getTextContent().trim().replaceAll("\r\n|\r|\n", "");
          sb.append("year#" + year + "#organization#" + organization + "#specialty#" + specialty).append("##");
        }
      } catch (Exception ex) {
        //throw new ParserHtmlHhResumeToInhouseXmlException("Ошибка при разборе документа для нахождения Education " + ex.getMessage());
        return "not found";
      }
      return sb.toString().replaceAll("##$", "");
    } else return "not found";
   }  
   public String getEnglish(String txt) throws ParserHtmlHhResumeToInhouseXmlException {
    Matcher matcher = Pattern.compile("Education.+<table.+<tbody>(.+)</tbody>.+</table>.+Languages", Pattern.DOTALL).matcher(txt);
    if (matcher.find()) {
      StringBuilder sb = new StringBuilder();      
      String education = "<root>" + matcher.group(1) + "</root>";
      try {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true); // never forget this!
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new ByteArrayInputStream(education.getBytes("UTF-8")));
        XPath xpath = XPathFactory.newInstance().newXPath();

        NodeList nodes = (NodeList) xpath.evaluate("/root/tr", doc, XPathConstants.NODESET);
        for (int j = 0; j < nodes.getLength(); j++) {
          Document newXmlDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
          Node node = nodes.item(j);
          Node copyNode = newXmlDocument.importNode(node, true);
          newXmlDocument.appendChild(copyNode);
          NodeList nodesYear = (NodeList) xpath.evaluate("/tr/td/text()", newXmlDocument, XPathConstants.NODESET);
          NodeList nodesOrganization = (NodeList) xpath.evaluate("/tr/td/div[count(@*)=0]", newXmlDocument, XPathConstants.NODESET);
          NodeList nodesSpecialty = (NodeList) xpath.evaluate("/tr/td/div[@class='resume__education__org']", newXmlDocument, XPathConstants.NODESET);
          String year = nodesYear.item(0).getTextContent().trim().replaceAll("\r\n|\r|\n", "");
          String organization = nodesOrganization.item(0).getTextContent().trim().replaceAll("\r\n|\r|\n", "");
          String specialty = nodesSpecialty.item(0).getTextContent().trim().replaceAll("\r\n|\r|\n", "");
          sb.append("year#" + year + "#organization#" + organization + "#specialty#" + specialty).append("##");
        }
      } catch (Exception ex) {
        //throw new ParserHtmlHhResumeToInhouseXmlException("Ошибка при разборе документа для нахождения Education " + ex.getMessage());
        return "not found";
      }
      return sb.toString().replaceAll("##$", "");
    } else return "not found";
  }
  
  public String get(String txt) throws ParserHtmlHhResumeToInhouseXmlException {
    String russian = getRussian(txt);
    String english = getEnglish(txt);
    if (!russian.equals("not found")) return russian;
    else return english;
  }
}