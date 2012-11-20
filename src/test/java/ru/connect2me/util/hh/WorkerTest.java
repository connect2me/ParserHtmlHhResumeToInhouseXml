package ru.connect2me.util.hh;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.connect2me.util.hh.config.ParserHtmlHhResumeToInhouseXmlException;
import ru.connect2me.util.hh.helper.CheckTest;
import ru.connect2me.util.hh.util.Check;
import ru.connect2me.util.hh.util.ExpectedComparator;

/**
 *
 * @author r.zaynullin
 */
public class WorkerTest {

  private Map map = new HashMap();

  public WorkerTest() {
  }

  @BeforeClass
  public static void setUpClass() {
  }

  @AfterClass
  public static void tearDownClass() {
  }

  @Before
  public void setUp() {
    try {
      InputStream is = CheckTest.class.getResourceAsStream("/test/input/sample001.txt");
      StringWriter writer = new StringWriter();
      IOUtils.copy(is, writer, "UTF-8");
      String result001 = new Worker().execute(writer.toString());

      
      is = CheckTest.class.getResourceAsStream("/test/input/expectedResult001.txt");
      writer = new StringWriter();
      IOUtils.copy(is, writer, "UTF-8");

      String expected001 = writer.toString();
      map.put(expected001, result001);
      
    } catch (IOException | ParserHtmlHhResumeToInhouseXmlException ex) {
      ex.printStackTrace();
    }
  }

  @After
  public void tearDown() {
    map.clear();
  }

  @Test
  public void testExecute() throws Exception {
    for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
      String expected = (String) iterator.next();
      String testData = (String) map.get(expected);
      int result = new ExpectedComparator().compare(expected, testData);
      assertEquals("Тест прошел неуспешно", 0, result);
    }
  }
}
