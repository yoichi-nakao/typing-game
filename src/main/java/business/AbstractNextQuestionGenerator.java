package business;

import java.util.List;

/**
 * 次の問題を生成する抽象クラス。
 *
 * @see NextQuestionGenerator
 */
abstract public class AbstractNextQuestionGenerator implements NextQuestionGenerator {
  /**
   * 出題対象の問題。
   */
  protected final List<Question> questions;

  /**
   * コンストラクタ。
   *
   * @param questions 出題対象の問題
   */
  public AbstractNextQuestionGenerator(List<Question> questions) {
    this.questions = questions;
  }
}
