package ru.connect2me.util.hh;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.connect2me.util.hh.config.ParserHtmlHhResumeToInhouseXmlException;
import ru.connect2me.util.hh.helper.CheckTest;

/**
 * Проверка класса Worker на различные входные значения
 * @author Зайнуллин Радик
 */
@RunWith(JUnit4.class)
public class WorkerTest {
  public WorkerTest() {}

  @BeforeClass
  public static void setUpClass() {}

  @AfterClass
  public static void tearDownClass() {}

  @Before
  public void setUp() throws IOException, ParserHtmlHhResumeToInhouseXmlException {
    InputStream is = CheckTest.class.getResourceAsStream("/test/input/sample001.txt");
    StringWriter writer = new StringWriter();
    IOUtils.copy(is, writer, "UTF-8");
    String result001 = new Worker().execute(writer.toString());
    
    
  }

  @After
  public void tearDown() {}

  @Test(expected = ParserHtmlHhResumeToInhouseXmlException.class)
  public void executeNullThrowsParserHtmlHhResumeToInhouseXmlException() throws ParserHtmlHhResumeToInhouseXmlException {
    new Worker().execute(null);
  }
  @Test(expected = ParserHtmlHhResumeToInhouseXmlException.class)
  public void executeEmptyThrowsParserHtmlHhResumeToInhouseXmlException() throws ParserHtmlHhResumeToInhouseXmlException {
    new Worker().execute("  ");
  }  
}