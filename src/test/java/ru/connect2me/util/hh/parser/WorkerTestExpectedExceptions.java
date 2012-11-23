package ru.connect2me.util.hh.parser;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.connect2me.util.hh.parser.config.ParserHtmlHhResumeToInhouseXmlException;

/**
 * Проверка класса Worker на различные входные значения
 * @author Зайнуллин Радик
 */
@RunWith(JUnit4.class)
public class WorkerTestExpectedExceptions {
  public WorkerTestExpectedExceptions() {}

  @BeforeClass
  public static void setUpClass() {}

  @AfterClass
  public static void tearDownClass() {}

  @Before
  public void setUp() {}

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