package business;

import java.util.List;

/**
 * 直前の問題と一致させないように次の問題をランダムに生成するクラス。
 *
 * @see AbstractNextQuestionGenerator
 */
public class NotMatchPreviousNextQuestionGenerator extends AbstractNextQuestionGenerator {
  /**
   * 前回出題した問題。
   */
  private Question prevQuestion;

  /**
   * コンストラクタ。
   *
   * @param questions 出題対象の問題
   */
  public NotMatchPreviousNextQuestionGenerator(List<Question> questions) {
    super(questions);
  }

  @Override
  public Question generate() {
    while (true) {
      double indexAsDouble = Math.random() * questions.size();
      int index = (int) indexAsDouble;
      Question question = questions.get(index);
      if (prevQuestion == null || !prevQuestion.isSame(question)) {
        prevQuestion = question;
        return question;
      }
    }
  }
}
