package ru.connect2me.util.hh.helper;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Утилита для ручной загрузки текста (xml) резюме с сайта hh.ru по id
 *
 * @author Зайнуллин Радик
 */
public class SampleUtil {
  private static WebClient webClient;
  public static void main(String[] args) {
    WebClient webClient = new WebClient();
    webClient.setJavaScriptEnabled(false);
    webClient.setCssEnabled(false);
    try {
      HtmlPage page = webClient.getPage("http://hh.ru/logon.do");

      List<HtmlForm> formList = page.getForms();

      HtmlForm neededForm = formList.get(1);

      HtmlTextInput user = neededForm.getInputByName("username");
      user.setValueAttribute("a8019111@yandex.ru");
      HtmlPasswordInput pass = neededForm.getInputByName("password");
      pass.setValueAttribute("YDz5iM");

      HtmlSubmitInput submit = (HtmlSubmitInput) neededForm.getElementsByAttribute("input", "name", "action").get(0);
      HtmlPage profilepage = (HtmlPage) submit.click();
      doCrawl();
      webClient.closeAllWindows();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void doCrawl() {
    String s = "002e4601020020e0a3000bd22e736563726574";
    try {
      HtmlPage page = webClient.getPage("http://hh.ru/resume/" + s);
    } catch (IOException | FailingHttpStatusCodeException ex) {
      System.out.println("Не удалось получить резюме.");
    }
  }
}