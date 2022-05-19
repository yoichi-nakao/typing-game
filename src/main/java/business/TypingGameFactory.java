package business;

import ui.HotTypingGameListener;
import ui.NormalTypingGameListener;

import java.util.List;

/**
 * タイピングゲームを生成するFactoryクラス。
 */
public class TypingGameFactory {
  /**
   * 引数に指定された難易度のタイピングゲームを生成する。
   *
   * @param difficulty     タイピングゲームの難易度
   * @param typingGameMode タイピングゲームのモード
   * @param generatorMode  タイピングゲームの次の問題の生成モード
   * @return 生成されたタイピングゲーム
   */
  public static TypingGame generate(TypingGameDifficulty difficulty, TypingGameMode typingGameMode, NextQuestionGeneratorMode generatorMode) {
    List<Question> questions = QuestionFactory.generate(difficulty);

    TypingGameListener listener;
    switch (typingGameMode) {
      case NORMAL:
        listener = new NormalTypingGameListener();
        break;
      case HOT:
        listener = new HotTypingGameListener();
        break;
      default:
        throw new RuntimeException("不正なゲームモードです。");
    }

    NextQuestionGenerator generator;
    switch (generatorMode) {
      case NOT_MATCH_PREVIOUS:
        generator = new NotMatchPreviousNextQuestionGenerator(questions);
        break;
      case NOT_MATCH_PAST:
        generator = new NotMatchPastNextQuestionGenerator(questions);
        break;
      default:
        throw new RuntimeException("不正な出題形式モードです。");
    }

    return new TypingGame(difficulty, listener, generator);
  }
}
