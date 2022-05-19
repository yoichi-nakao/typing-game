package ui;

import business.*;

import java.util.Arrays;
import java.util.List;

/**
 * コンソールでタイピングゲームを実行するクラス。
 */
public class ConsoleConductor {
  /**
   * タイピングゲームを実行する。
   */
  public void execute() {
    TypingGameHistory history = new TypingGameHistory();

    do {
      TypingGameDifficulty difficulty = displayInputDifficulty();

      TypingGameMode typingGameMode = displayInputMode();

      NextQuestionGeneratorMode calculatorMode = displayInputGeneratorMode();

      TypingGame typingGame = TypingGameFactory.generate(difficulty, typingGameMode, calculatorMode);

      displayCountDown();

      typingGame.execute();

      displayResult(typingGame);

      history.add(typingGame);

      displayRanking(history, difficulty);

    } while (displayInputRetry());
  }

  /**
   * タイピングゲームの難易度入力を表示する。
   *
   * @return タイピングゲームの難易度
   */
  private TypingGameDifficulty displayInputDifficulty() {
    int max = TypingGameDifficulty.values().length;
    int defaultLevel = TypingGameDifficulty.getDefault().getLevel();

    System.out.printf("難易度を選択してください(1-%d) [%d]：%n", max, defaultLevel);
    Arrays.stream(TypingGameDifficulty.values())
            .forEach(d -> System.out.printf(" %d:%s%n", d.getLevel(), d.getName()));

    int inputDifficultyLevel = getInputInt(defaultLevel);

    return TypingGameDifficulty.of(inputDifficultyLevel);
  }

  /**
   * タイピングゲームのモード入力を表示する。
   *
   * @return タイピングゲームのモード
   */
  private TypingGameMode displayInputMode() {
    int max = TypingGameMode.values().length;
    int defaultMode = TypingGameMode.getDefault().getMode();

    System.out.printf("ゲームモードを選択してください(1-%d) [%d]：%n", max, defaultMode);
    Arrays.stream(TypingGameMode.values())
            .forEach(d -> System.out.printf(" %d:%s%n", d.getMode(), d.getName()));

    int inputDifficultyLevel = getInputInt(defaultMode);

    return TypingGameMode.of(inputDifficultyLevel);
  }

  /**
   * タイピングゲームの次の問題の生成モード入力を表示する。
   *
   * @return タイピングゲームの次の問題の生成モード
   */
  private NextQuestionGeneratorMode displayInputGeneratorMode() {
    int max = NextQuestionGeneratorMode.values().length;
    int defaultMode = NextQuestionGeneratorMode.getDefault().getMode();

    System.out.printf("質問の出題方法を選択してください(1-%d) [%d]：%n", max, defaultMode);
    Arrays.stream(NextQuestionGeneratorMode.values())
            .forEach(d -> System.out.printf(" %d:%s%n", d.getMode(), d.getName()));

    int inputDifficultyLevel = getInputInt(defaultMode);

    return NextQuestionGeneratorMode.of(inputDifficultyLevel);
  }

  /**
   * カウントダウンを表示する。
   */
  private void displayCountDown() {
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
   * 回答履歴を表示する。
   *
   * @param typingGame 回答履歴を表示するタイピングゲーム
   */
  private void displayResult(TypingGame typingGame) {
    List<QuestionResult> results = typingGame.getResults();
    for (int i = 0; i < results.size(); i++) {
      QuestionResult result = results.get(i);
      System.out.printf("%3d: 問題: %s\t回答: %s\t判定: %s%n",
              i + 1, result.getQuestion().getWord(), result.getAnswer(), result.isJudge() ? "OK" : "NG");
    }
  }

  /**
   * タイピングゲームのランキングを表示する。
   *
   * @param typingGameHistory タイピングゲームの履歴
   * @param difficulty        タイピングゲームの難易度
   */
  private void displayRanking(TypingGameHistory typingGameHistory, TypingGameDifficulty difficulty) {
    List<TypingGame> sortedTypingGames = typingGameHistory.sortedTypingGames(difficulty, 10);
    for (int i = 0; i < sortedTypingGames.size(); i++) {
      TypingGame typingGame = sortedTypingGames.get(i);
      System.out.printf("%2d: %10d[ms]\t開始時刻: %s%n",
              i + 1, typingGame.getTimeToCalculate(), typingGame.getStartDateTime("yyyy/MM/dd HH:mm:ss"));
    }
  }

  /**
   * リトライ可否の問い合わせを表示する。
   *
   * @return リトライ可否
   */
  private boolean displayInputRetry() {
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
  private int getInputInt(int defaultValue) {
    int input;
    try {
      input = StandardInputReader.getInputInt("> ");
    } catch (NumberFormatException e) {
      input = defaultValue;
    }
    return input;
  }
}
