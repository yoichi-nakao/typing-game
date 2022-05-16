/**
 * タイピングゲームを実行するクラス。
 */
public class Main {
  /**
   * タイピングゲームのエントリーポイント。
   *
   * @param args タイピングゲームの引数
   */
  public static void main(String[] args) {
    TypingGameHistory history = new TypingGameHistory();

    do {
      TypingGameDifficulty difficulty = displayInputDifficulty();

      TypingGame typingGame = TypingGameFactory.generate(difficulty);

      displayCountDown();

      typingGame.execute();

      history.add(typingGame);

      displayRanking(history, difficulty);

    } while (displayInputRetry());
  }

  /**
   * タイピングゲームの難易度入力を表示する。
   *
   * @return タイピングゲームの難易度
   */
  private static TypingGameDifficulty displayInputDifficulty() {
    int max = TypingGameDifficulty.values().length;
    int defaultLevel = TypingGameDifficulty.getDefault().getLevel();

    System.out.printf("難易度を選択してください(1-%d) [%d]：%n", max, defaultLevel);
    for (TypingGameDifficulty difficulty : TypingGameDifficulty.values()) {
      System.out.printf(" %d:%s%n", difficulty.getLevel(), difficulty.getName());
    }

    int inputDifficultyLevel = getInputInt(defaultLevel);

    return TypingGameDifficulty.of(inputDifficultyLevel);
  }

  /**
   * カウントダウンを表示する。
   */
  private static void displayCountDown() {
    int count = 5;
    while (count != 0) {
      System.out.print(count);
      int dotCount = 0;
      while (dotCount < 10) {
        try {
          Thread.sleep(100);
          System.out.print(".");
          dotCount++;
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
      count--;
      System.out.println();
    }
  }

  /**
   * タイピングゲームのランキングを表示する。
   *
   * @param typingGameHistory タイピングゲームの履歴
   * @param difficulty        タイピングゲームの難易度
   */
  private static void displayRanking(TypingGameHistory typingGameHistory, TypingGameDifficulty difficulty) {
    TypingGame[] sortedTypingGames = typingGameHistory.sortedTypingGames(difficulty);
    int i = 0;
    while (i < sortedTypingGames.length && i < 10) {
      TypingGame typingGame = sortedTypingGames[i];
      System.out.printf("%2d: %10d[ms]%n", i + 1, typingGame.getTimeToCalculate());
      i++;
    }
  }

  /**
   * リトライ可否の問い合わせを表示する。
   *
   * @return リトライ可否
   */
  private static boolean displayInputRetry() {
    int defaultValue = 2;

    System.out.printf("リトライしますか？(1:する 2:しない) [%d]：%n", defaultValue);

    int inputRetry = getInputInt(defaultValue);

    return inputRetry == 1;
  }

  /**
   * コンソールから入力を受け付け、数値に変換して返却する。
   *
   * @param defaultValue 数値以外が入力された場合のデフォルト値
   * @return 数値に変換された入力値
   */
  private static int getInputInt(int defaultValue) {
    int input;
    try {
      input = StandardInputReader.getInputInt("> ");
    } catch (NumberFormatException e) {
      input = defaultValue;
    }
    return input;
  }
}
