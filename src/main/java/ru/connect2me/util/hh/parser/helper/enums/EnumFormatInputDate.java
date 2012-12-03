package ru.connect2me.util.hh.parser.helper.enums;

/**
 * регулярные выражения для преобразований дат в виде 21 декабря 1953 в 21.12.1953
 * то же самое если месяц написан по английски
 * @author Зайнуллин Радик 
 * @since 2012.12.03
 */
public enum EnumFormatInputDate {
  January("(?i)\\((\\d{1,2}) (января|January) (19\\d{2})\\)", "$1.01.$2"),
  February("(?i)\\((\\d{1,2}) (февраля|February) (19\\d{2})\\)", "$1.01.$2"),
  March("(?i)\\((\\d{1,2}) (марта|March) (19\\d{2})\\)", "$1.01.$2"),
  April("(?i)\\((\\d{1,2}) (апреля|April) (19\\d{2})\\)", "$1.01.$2"),
  May("(?i)\\((\\d{1,2}) (мая|May) (19\\d{2})\\)", "$1.01.$2"),
  June("(?i)\\((\\d{1,2}) (июня|June) (19\\d{2})\\)", "$1.01.$2"),
  July("(?i)\\((\\d{1,2}) (июля|July) (19\\d{2})\\)", "$1.01.$2"),
  August("(?i)\\((\\d{1,2}) (августа|August) (19\\d{2})\\)", "$1.01.$2"),
  September("(?i)\\((\\d{1,2}) (сентября|September) (19\\d{2})\\)", "$1.01.$2"),
  October("(?i)\\((\\d{1,2}) (октября|October) (19\\d{2})\\)", "$1.01.$2"),
  November("(?i)\\((\\d{1,2}) (ноября|November) (19\\d{2})\\)", "$1.01.$2"),
  December("(?i)\\((\\d{1,2}) (декабря|December) (19\\d{2})\\)", "$1.01.$2");
  
  private final String regexp;      // искомое выражение
  private final String replacement; // замена

  EnumFormatInputDate(String regexp, String replacement) {
    this.regexp = regexp;
    this.replacement = replacement;
  }

  public String regexp() {
    return regexp;
  }

  public String replacement() {
    StringBuilder sb = new StringBuilder(replacement);
    if (sb.toString().length()<10) sb.insert(0, "0");
    return sb.toString();
  }  
}
