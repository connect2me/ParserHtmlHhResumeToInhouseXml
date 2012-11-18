package ru.connect2me.util.hh.helper;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
/**
 *
 * @author r.zaynullin
 */
public class Check {
  
  public Check(){}
  
  public boolean isWellFormed(String inXml) {
    boolean response = true;
    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      factory.setValidating(false);
      factory.setNamespaceAware(true);
      DocumentBuilder builder = factory.newDocumentBuilder();
      builder.setErrorHandler(new SimpleErrorHandler());
      Document document = builder.parse(inXml);
    } catch (ParserConfigurationException | SAXException | IOException ex) {
      Logger.getLogger(Check.class.getName()).log(Priority.FATAL, "Ошибка: Входной файл не является xml well-formed.");
      response = false;
    }
    return response;
  }

  private class SimpleErrorHandler implements ErrorHandler {
    public void warning(SAXParseException e) throws SAXException {
      System.out.println(e.getMessage());
    }
    public void error(SAXParseException e) throws SAXException {
      System.out.println(e.getMessage());
    }
    public void fatalError(SAXParseException e) throws SAXException {
      System.out.println(e.getMessage());
    }
  }
}