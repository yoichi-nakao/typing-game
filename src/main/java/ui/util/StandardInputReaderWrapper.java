package ui.util;

/**
 * StandardInputReaderのラッパークラス。
 */
public class StandardInputReaderWrapper {
  /**
   * コンソールから入力を受け付け、数値に変換して返却する。
   *
   * @param defaultValue 数値以外が入力された場合のデフォルト値
   * @return 数値に変換された入力値
   */
  public static int getInputInt(int defaultValue) {
    int input;
    try {
      input = StandardInputReader.getInputInt("> ");
    } catch (NumberFormatException e) {
      input = defaultValue;
    }
    return input;
  }
}
