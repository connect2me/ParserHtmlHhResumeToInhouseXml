package ru.connect2me.util.hh.helper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URISyntaxException;
import java.net.URL;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import ru.connect2me.util.hh.Worker;
import ru.connect2me.util.hh.config.ParserHtmlHhResumeToInhouseXmlException;

/**
 * Утилита для получения в ручном режиме выходного файла. Она нужна для просмотра результата обработки определенного
 * резюме или для формирования для тестов образцового выходного файла.
 * Напоминаю результат ищите в папке target
 *
 * @author Зайнуллин Радик
 */
public class UtilMakeOutputSample {

  public static void main(String[] args) throws ParserHtmlHhResumeToInhouseXmlException {
    InputStream is = UtilMakeOutputSample.class.getResourceAsStream("/test/input/sample001.txt");
    StringWriter writer = new StringWriter();
    try {
      IOUtils.copy(is, writer, "UTF-8");
    } catch (IOException ex) {
      System.out.println("Не могу прочесть тестовый файл.");
    }

    String result = new Worker().execute(writer.toString());

    URL url = UtilMakeInputSample.class.getResource("/test/input/newExpectedResult.txt");
    File file = null;
    try {
      file = new File(url.toURI().getPath());
    } catch (URISyntaxException ex) {
      System.out.println("Не могу найти файл "+ url.toString() + ". Создайте пустой файл с этим именем.");
    }
    try {
      FileUtils.writeStringToFile(file, result, "UTF-8");
    } catch (IOException ex) {
      System.out.println("Не могу записать файл "+ url.toString() + ".");
    }
  }
}