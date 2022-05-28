package business.typinggame.generator;

import business.question.Question;

/**
 * 次の問題を生成するインターフェース。
 */
public interface NextQuestionGenerator {
  /**
   * 次の問題を生成する。
   *
   * @return 生成した次の問題
   */
  Question generate();
}
