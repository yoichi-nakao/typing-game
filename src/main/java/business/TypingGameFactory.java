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
   * @return 生成されたタイピングゲーム
   */
  public static TypingGame generate(TypingGameDifficulty difficulty, TypingGameMode typingGameMode) {
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
    NextQuestionGenerator calculator = new NotMatchPreviousNextQuestionGenerator(questions);
    return new TypingGame(difficulty, listener, calculator);
  }
}
