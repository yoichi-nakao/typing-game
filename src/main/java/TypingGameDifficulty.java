import java.util.Arrays;

/**
 * タイピングゲームで扱う難易度。
 */
public enum TypingGameDifficulty {
  EASY("Easy", 1, false),
  NORMAL("Normal", 2, true),
  HARD("Hard", 3, false);

  /**
   * 名前。
   */
  private final String name;
  /**
   * 難易度の数値表現。
   */
  private final int level;
  /**
   * デフォルトのレベルかどうか。
   */
  private final boolean defaultLevel;

  /**
   * コンストラクタ。
   *
   * @param name         名前
   * @param level        難易度の数値表現
   * @param defaultLevel デフォルトのレベルかどうか
   */
  TypingGameDifficulty(String name, int level, boolean defaultLevel) {
    this.name = name;
    this.level = level;
    this.defaultLevel = defaultLevel;
  }

  /**
   * 引数で指定された難易度の数値表現に対応するTypingGameDifficultyを生成する。
   *
   * @param level 難易度の数値表現
   * @return TypingGameDifficulty
   */
  public static TypingGameDifficulty of(int level) {
    return Arrays.stream(TypingGameDifficulty.values())
            .filter(d -> d.level == level)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("指定した値のTypingGameDifficultyは存在しません: " + level));
  }

  /**
   * TypingGameDifficultyのデフォルト値を返却する。
   *
   * @return TypingGameDifficultyのデフォルト値
   */
  public static TypingGameDifficulty getDefault() {
    return Arrays.stream(TypingGameDifficulty.values())
            .filter(d -> d.defaultLevel)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("TypingGameDifficultyの初期値は設定されていません。"));
  }

  /**
   * 名前を取得する。
   *
   * @return 名前
   */
  public String getName() {
    return name;
  }

  /**
   * 難易度の数値表現を取得する。
   *
   * @return 難易度の数値表現
   */
  public int getLevel() {
    return level;
  }

  /**
   * 難易度が引数で指定された難易度以下か判定する。
   *
   * @param difficulty 難易度
   * @return 難易度が引数で指定された難易度以下の場合は、trueを返却する。
   */
  public boolean lessThan(TypingGameDifficulty difficulty) {
    return this.level <= difficulty.level;
  }
}
