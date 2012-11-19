package ru.connect2me.util.hh.helper;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import junit.framework.TestCase;
import org.apache.commons.io.IOUtils;

/**
 * Проверка входных html-файлов на предмет well-formed
 * @author Зайнуллин Радик
 */
public class CheckTest extends TestCase {
  Map map = new HashMap();

  public CheckTest(String testName) {
    super(testName);
  }

  @Override
  protected void setUp() throws Exception {
    InputStream is = CheckTest.class.getResourceAsStream("/test/input/sample001.txt");
    StringWriter writer = new StringWriter();
    IOUtils.copy(is, writer, "UTF-8");
    map.put(true, writer.toString());
    
    is = CheckTest.class.getResourceAsStream("/test/input/sample002.txt");
    writer = new StringWriter();
    IOUtils.copy(is, writer, "UTF-8");
    map.put(true, writer.toString());
    
    is = CheckTest.class.getResourceAsStream("/test/input/sample003.txt");
    writer = new StringWriter();
    IOUtils.copy(is, writer, "UTF-8");
    map.put(true, writer.toString());    
    
    is = CheckTest.class.getResourceAsStream("/test/input/sample004.txt");
    writer = new StringWriter();
    IOUtils.copy(is, writer, "UTF-8");
    map.put(true, writer.toString());      
  }

  @Override
  protected void tearDown() throws Exception {
    map.clear();
  }

  public void testIsWellFormed() {
    Check instance = new Check();
    for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
      Boolean expected = (Boolean) iterator.next();
      String testData = (String) map.get(expected);
      boolean result = instance.isWellFormed(testData);
      assertEquals("Тест прошел неуспешно", expected.booleanValue(), result);
    }
  }
}