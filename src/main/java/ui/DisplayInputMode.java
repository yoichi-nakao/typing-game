package ui;

import business.*;

import java.util.Arrays;

/**
 * 画面からの入力を受け付けるクラス。
 */
public class DisplayInputMode {
  public TypingGame getInput() {
    TypingGameDifficulty difficulty = displayInputDifficulty();

    TypingGameMode typingGameMode = displayInputMode();

    NextQuestionGeneratorMode calculatorMode = displayInputGeneratorMode();

    return TypingGameFactory.generate(difficulty, typingGameMode, calculatorMode);
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

    int inputDifficultyLevel = StandardInputReaderWrapper.getInputInt(defaultLevel);

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

    int inputDifficultyLevel = StandardInputReaderWrapper.getInputInt(defaultMode);

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

    int inputDifficultyLevel = StandardInputReaderWrapper.getInputInt(defaultMode);

    return NextQuestionGeneratorMode.of(inputDifficultyLevel);
  }
}
