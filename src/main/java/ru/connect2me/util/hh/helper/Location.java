package ru.connect2me.util.hh.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ru.connect2me.util.hh.config.ParserHtmlHhResumeToInhouseXmlException;

/**
 * Получение местожительства человека из резюме
 *
 * @author Зайнуллин Радик
 */
public class Location {

  private List<String> towns = new ArrayList<String>();

  public Location() throws ParserHtmlHhResumeToInhouseXmlException {
    try {
      init(Location.class.getResourceAsStream("/rocid.xml"));
    } catch (SAXException | IOException | ParserConfigurationException ex) {
      throw new ParserHtmlHhResumeToInhouseXmlException("Серьезная ошибка: невозможно распарсить справочник по городам - файл rocid.xml.");
    }
  }

  public String get(String txt) throws ParserHtmlHhResumeToInhouseXmlException {
    Matcher matcher = Pattern.compile("resume__personal__name(.*)resume__contacts", Pattern.DOTALL).matcher(txt);
    String location = "not found";
    if (matcher.find()) {
      String huge = matcher.group(1);

      for (String town : towns) {
        if (huge.contains(town)) {
          location = town;
          break;
        }
      }
    }
    return location.trim();
  }

  private void init(InputStream src) throws SAXException, IOException, ParserConfigurationException {
    SAXParserFactory factory = SAXParserFactory.newInstance();
    SAXParser saxParser = factory.newSAXParser();

    DefaultHandler handler = new DefaultHandler() {
      private boolean isName = false;
      @Override
      public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("name")) isName = true;
      }
      @Override
      public void characters(char[] ch, int start, int length) throws SAXException {
        if (isName) towns.add(new String(ch, start, length).trim());
      }
      @Override
      public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("name")) isName = false;
      }
    };
    saxParser.parse(src, handler);
  }
}