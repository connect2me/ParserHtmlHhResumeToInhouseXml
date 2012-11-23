package ru.connect2me.util.hh.parser.helper.enums;

public enum EnumCleaningInputForExperience {
 /**
 *  набор регулярных выражений для чистки исходного xml (чтобы упростить дальнейший поиск)
 * 
 * @author Зайнуллин Радик
 */
  SCRIPT_LIQUIDATION("(?is)<script\\s?.*?>.+?</script>", "Вырезаем все скрипты"),
  ONCLICK_LIQUIDATION("(?si)\\sonclick\\s*=\\s*\"(\\\\.|[^\\\\\"])*\"\\s*", "убираем все onclick выражения");
  private final String regexp;      // регулярное выражение
  private final String description; // разьяснение

  EnumCleaningInputForExperience(String regexp, String description) {
    this.regexp = regexp;
    this.description = description;
  }

  public String regexp() {
    return regexp;
  }

  public String description() {
    return description;
  }
}