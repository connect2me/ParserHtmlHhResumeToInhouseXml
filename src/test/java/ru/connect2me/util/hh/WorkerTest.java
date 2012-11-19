package ru.connect2me.util.hh;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.connect2me.util.hh.config.ParserHtmlHhResumeToInhouseXmlException;
import ru.connect2me.util.hh.config.XMLConfiguration;

/**
 *
 * @author r.zaynullin
 */
@RunWith(JUnit4.class)
public class WorkerTest {
  public WorkerTest() {}

  @BeforeClass
  public static void setUpClass() {}

  @AfterClass
  public static void tearDownClass() {}

  @Before
  public void setUp() {}

  @After
  public void tearDown() {}

  @Rule
  public ExpectedException exception = ExpectedException.none();

  @Test(expected = ParserHtmlHhResumeToInhouseXmlException.class)
  public void executeNullThrowsParserHtmlHhResumeToInhouseXmlException() throws ParserHtmlHhResumeToInhouseXmlException {
    Worker instance = new Worker(new XMLConfiguration(Main.class.getResourceAsStream("/config.xml")));
    instance.execute(null);
  }
  @Test(expected = ParserHtmlHhResumeToInhouseXmlException.class)
  public void executeEmptyThrowsParserHtmlHhResumeToInhouseXmlException() throws ParserHtmlHhResumeToInhouseXmlException {
    Worker instance = new Worker(new XMLConfiguration(Main.class.getResourceAsStream("/config.xml")));
    instance.execute("  ");
  }  
}