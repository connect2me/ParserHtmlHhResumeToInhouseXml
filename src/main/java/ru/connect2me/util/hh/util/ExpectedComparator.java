package ru.connect2me.util.hh.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * Сравниваем полученный (в результате обработки входного тестового xml) реальный xml и 
 * подготовленного (методом пристального разглядывания) должного выходного xml.
 * Вообще-то данные готовятся так - берется исходный входной файл и анализируется простым просмотром (глазами).
 * Готовиться (идеальный) выходной файл. Его-то и сравнивают с нашим (сгенерированным) выходным файлом.
 * 
 * @author Зайнуллин Радик
 */
public class ExpectedComparator implements Comparator<String>{

  @Override
  public int compare(String expected, String real) {
    // Формируем документы
    Document expectedDoc = getDocFromStr(expected); 
    Document realDoc = getDocFromStr(real); 
    // Формируем список параметров по которым будет производится сравнение
    String[] arr = new String[]{"id"};
    // 
    for (String parameter: arr){
      if (!extract(expectedDoc, parameter).equals(extract(realDoc, parameter))) return -1;
    }
    return 0;
  }
 
  public Document getDocFromStr(String inXml) {
    Document document = null;
    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      factory.setValidating(false);
      factory.setFeature("http://xml.org/sax/features/namespaces", false);
      factory.setFeature("http://xml.org/sax/features/validation", false);
      factory.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
      factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
      
      DocumentBuilder builder = factory.newDocumentBuilder();
      builder.setErrorHandler(new SimpleErrorHandler());
      document = builder.parse(new ByteArrayInputStream(inXml.getBytes("UTF-8")));
    } catch (ParserConfigurationException | SAXException | IOException ex) {
      System.out.println("Не могу сформировать org.w3c.dom.Document из входной строки.");
    }
    return document;
  }

  private String extract(Document doc, String par) {
    return "";
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