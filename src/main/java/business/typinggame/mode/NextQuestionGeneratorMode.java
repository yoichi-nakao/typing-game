package business.typinggame.mode;

import java.util.Arrays;

/**
 * タイピングゲームの次の問題の生成モードを表現するクラス。
 */
public enum NextQuestionGeneratorMode {
  NOT_MATCH_PREVIOUS("直前の問題と一致させない", 1, true),
  NOT_MATCH_PAST("過去の問題と一致しない", 2, false);

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
   * @param mode        モードの数値表現
   * @param defaultMode デフォルトのモードかどうか
   */
  NextQuestionGeneratorMode(String name, int mode, boolean defaultMode) {
    this.name = name;
    this.mode = mode;
    this.defaultMode = defaultMode;
  }

  /**
   * 引数で指定されたモードの数値表現に対応するNextQuestionGeneratorModeを生成する。
   *
   * @param mode モードの数値表現
   * @return business.NextQuestionGeneratorMode
   */
  public static NextQuestionGeneratorMode of(int mode) {
    return Arrays.stream(NextQuestionGeneratorMode.values())
            .filter(c -> c.mode == mode)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("指定した値のNextQuestionGeneratorModeは存在しません: " + mode));
  }

  /**
   * NextQuestionGeneratorModeのデフォルト値を返却する。
   *
   * @return NextQuestionGeneratorModeのデフォルト値
   */
  public static NextQuestionGeneratorMode getDefault() {
    return Arrays.stream(NextQuestionGeneratorMode.values())
            .filter(m -> m.defaultMode)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("NextQuestionGeneratorModeの初期値は設定されていません。"));
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
