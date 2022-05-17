/**
 * 問題の結果を表現するクラス。
 */
public class QuestionResult {
  /**
   * 出題した問題。
   */
  private final Question question;

  /**
   * 入力した解答。
   */
  private final String answer;

  /**
   * 判定結果。
   */
  private final boolean judge;

  /**
   * コンストラクタ。
   *
   * @param question 出題した問題
   * @param answer   入力した解答
   * @param judge    判定結果
   */
  public QuestionResult(Question question, String answer, boolean judge) {
    this.question = question;
    this.answer = answer;
    this.judge = judge;
  }

  /**
   * 出題した問題を返却する。
   *
   * @return 出題した問題
   */
  public Question getQuestion() {
    return question;
  }

  /**
   * 入力した解答を返却する。
   *
   * @return 入力した解答
   */
  public String getAnswer() {
    return answer;
  }

  /**
   * 判定結果を返却する。
   *
   * @return 判定結果
   */
  public boolean isJudge() {
    return judge;
  }
}
