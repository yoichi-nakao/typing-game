package ui;

import business.QuestionResult;
import business.TypingGame;
import business.TypingGameDifficulty;
import business.TypingGameHistory;

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
      DisplayInputMode displayInputMode = new DisplayInputMode();
      TypingGame typingGame = displayInputMode.getInput();

      displayCountDown();

      typingGame.execute();

      displayResult(typingGame);

      history.add(typingGame);

      displayRanking(history, typingGame.getDifficulty());

    } while (displayInputRetry());
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

    int inputRetry = StandardInputReaderWrapper.getInputInt(defaultValue);

    return inputRetry == 1;
  }
}
