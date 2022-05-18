import java.util.List;

/**
 * タイピングゲームを生成するFactoryクラス。
 */
public class TypingGameFactory {
  /**
   * 引数に指定された難易度のタイピングゲームを生成する。
   *
   * @param difficulty タイピングゲームの難易度
   * @return 生成されたタイピングゲーム
   */
  public static TypingGame generate(TypingGameDifficulty difficulty) {
    List<Question> questions = QuestionFactory.generate(difficulty);
    TypingGameListener listener = new NormalTypingGameListener();
    return new TypingGame(difficulty, questions, listener);
  }
}
