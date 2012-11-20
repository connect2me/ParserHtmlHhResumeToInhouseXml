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
  public void setUp() throws IOException, ParserHtmlHhResumeToInhouseXmlException {
    InputStream is = CheckTest.class.getResourceAsStream("/test/input/sample001.txt");
    StringWriter writer = new StringWriter();
    IOUtils.copy(is, writer, "UTF-8");
    String result001 = new Worker().execute(writer.toString());
    
    is = CheckTest.class.getResourceAsStream("/test/input/expectedResult001.txt");
    writer = new StringWriter();
    IOUtils.copy(is, writer, "UTF-8");
    String expected001 = new Worker().execute(writer.toString());
    
    map.put(expected001, result001);
  }
  
  @After
  public void tearDown() {
    map.clear();
  }

  @Test
  public void testExecute() throws Exception {
    Check instance = new Check();
    for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
      Boolean expected = (Boolean) iterator.next();
      String testData = (String) map.get(expected);
      boolean result = instance.isWellFormed(testData);
      assertEquals("Тест прошел неуспешно", expected.booleanValue(), result);
    }
  }
}
