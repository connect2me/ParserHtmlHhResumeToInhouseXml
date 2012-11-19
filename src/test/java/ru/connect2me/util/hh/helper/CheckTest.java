package ru.connect2me.util.hh.helper;

import junit.framework.TestCase;

/**
 *
 * @author r.zaynullin
 */
public class CheckTest extends TestCase {
  
  public CheckTest(String testName) {
    super(testName);
  }
  
  @Override
  protected void setUp() throws Exception {
    super.setUp();
  }
  
  @Override
  protected void tearDown() throws Exception {
    super.tearDown();
  }

  /**
   * Test of isWellFormed method, of class Check.
   */
  public void testIsWellFormed() {
    System.out.println("isWellFormed");
    String inXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><root><p>Hello World!</p></root>";
    Check instance = new Check();
    boolean expResult = true;
    boolean result = instance.isWellFormed(inXml);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
}