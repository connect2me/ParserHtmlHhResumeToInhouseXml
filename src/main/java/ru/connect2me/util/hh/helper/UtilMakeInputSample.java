package ru.connect2me.util.hh.helper;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import java.io.File;
import java.net.URL;
import java.util.List;
import org.apache.commons.io.FileUtils;

/**
 * Утилита для ручной загрузки текста (xml) резюме с сайта hh.ru по id.
 * Получите с сайта hh.ru номер резюме, обновите текущий номер в этой утилитке.
 * Обратите внимание - новый (заполненный) файл появится в папке target/... :)
 *
 * @author Зайнуллин Радик
 */
public class UtilMakeInputSample {

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

      // 002e4601020020e0a3000bd22e736563726574
      // dd2b731102012f557b000bd22e786e506d7270
      // e2b8640b02012761b6000bd22e396252644579
      String s = "f6f17d330200358f4d000bd22e736563726574";
       
      HtmlPage page1 = webClient.getPage("http://hh.ru/resume/" + s);
      String xml = page1.asXml();

      URL url = UtilMakeInputSample.class.getResource("/test/input/newSample.txt");
      File file = new File(url.toURI().getPath());
      FileUtils.writeStringToFile(file, xml, "UTF-8");

      webClient.closeAllWindows();
    } catch (Exception e) {
      System.out.println("Не удалось полчить требуемую страницу в виде xml.");
    }
  }
}