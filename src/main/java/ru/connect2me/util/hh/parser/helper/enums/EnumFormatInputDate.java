package ru.connect2me.util.hh.parser.helper.enums;

/**
 * регулярные выражения для преобразований дат в виде 21 декабря 1953 в 21.12.1953
 * то же самое если месяц написан по английски
 * @author Зайнуллин Радик 
 * @since 2012.12.03
 */
public enum EnumFormatInputDate {
  January("(?i)\\((\\d{1,2}) (?:января|of January) (19\\d{2})\\)", "$1.01.$2"),
  February("(?i)\\((\\d{1,2}) (?:февраля|of February) (19\\d{2})\\)", "$1.02.$2"),
  March("(?i)\\((\\d{1,2}) (?:марта|of March) (19\\d{2})\\)", "$1.03.$2"),
  April("(?i)\\((\\d{1,2}) (?:апреля|of April) (19\\d{2})\\)", "$1.04.$2"),
  May("(?i)\\((\\d{1,2}) (?:мая|of May) (19\\d{2})\\)", "$1.05.$2"),
  June("(?i)\\((\\d{1,2}) (?:июня|of June) (19\\d{2})\\)", "$1.06.$2"),
  July("(?i)\\((\\d{1,2}) (?:июля|of July) (19\\d{2})\\)", "$1.07.$2"),
  August("(?i)\\((\\d{1,2}) (?:августа|of August) (19\\d{2})\\)", "$1.08.$2"),
  September("(?i)\\((\\d{1,2}) (?:сентября|of September) (19\\d{2})\\)", "$1.09.$2"),
  October("(?i)\\((\\d{1,2}) (?:октября|of October) (19\\d{2})\\)", "$1.10.$2"),
  November("(?i)\\((\\d{1,2}) (?:ноября|of November) (19\\d{2})\\)", "$1.11.$2"),
  December("(?i)\\((\\d{1,2}) (?:декабря|of December) (19\\d{2})\\)", "$1.12.$2");
  
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
    return replacement;
  }  
}