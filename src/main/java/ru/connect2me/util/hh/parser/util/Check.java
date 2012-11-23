package ru.connect2me.util.hh.parser.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 *
 * @author r.zaynullin
 */
public class Check {
  public Check() {}

  public boolean isWellFormed(String inXml) {
    boolean response = true;
    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      factory.setValidating(false);
      factory.setFeature("http://xml.org/sax/features/namespaces", false);
      factory.setFeature("http://xml.org/sax/features/validation", false);
      factory.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
      factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
      
      DocumentBuilder builder = factory.newDocumentBuilder();
      builder.setErrorHandler(new SimpleErrorHandler());
      Document document = builder.parse(new ByteArrayInputStream(inXml.getBytes("UTF-8")));
    } catch(ParserConfigurationException ex) {
      response = false;
    }catch(SAXException ex) {
      response = false;
    }catch(IOException ex) {
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