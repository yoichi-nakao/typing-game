package business;

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
