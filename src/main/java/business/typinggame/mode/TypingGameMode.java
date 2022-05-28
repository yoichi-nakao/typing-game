package business.typinggame.mode;

import java.util.Arrays;

/**
 * タイピングゲームのモードを表現するクラス。
 */
public enum TypingGameMode {
  NORMAL("ノーマル", 1, true),
  HOT("アツい", 2, false);

  /**
   * 名前。
   */
  private final String name;

  /**
   * モードの数値表現。
   */
  private final int mode;

  /**
   * デフォルトのモードかどうか。
   */
  private final boolean defaultMode;

  /**
   * コンストラクタ。
   *
   * @param name        名前
   * @param mode        タイピングゲームのモード
   * @param defaultMode デフォルトのモードかどうか
   */
  TypingGameMode(String name, int mode, boolean defaultMode) {
    this.name = name;
    this.mode = mode;
    this.defaultMode = defaultMode;
  }

  /**
   * 引数で指定されたモードの数値表現に対応するTypingGameModeを生成する。
   *
   * @param mode モードの数値表現
   * @return business.TypingGameMode
   */
  public static TypingGameMode of(int mode) {
    return Arrays.stream(TypingGameMode.values())
            .filter(m -> m.mode == mode)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("指定した値のTypingGameModeは存在しません: " + mode));
  }

  /**
   * TypingGameModeのデフォルト値を返却する。
   *
   * @return TypingGameModeのデフォルト値
   */
  public static TypingGameMode getDefault() {
    return Arrays.stream(TypingGameMode.values())
            .filter(m -> m.defaultMode)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("TypingGameModeの初期値は設定されていません。"));
  }

  /**
   * 名前を返却する。
   *
   * @return 名前
   */
  public String getName() {
    return name;
  }

  /**
   * モードの数値表現を返却する。
   *
   * @return タイピングゲームのモード
   */
  public int getMode() {
    return mode;
  }
}
