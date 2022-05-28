package business.question;

import business.typinggame.TypingGameDifficulty;

/**
 * 問題を表現するクラス。
 */
public class Question {
  /**
   * 単語。
   */
  private final String word;

  /**
   * 難易度。
   */
  private final TypingGameDifficulty difficulty;

  /**
   * コンストラクタ。
   *
   * @param word       単語
   * @param difficulty 難易度
   */
  public Question(String word, TypingGameDifficulty difficulty) {
    this.word = word;
    this.difficulty = difficulty;
  }

  /**
   * 問題の単語を取得する。
   *
   * @return 問題の単語
   */
  public String getWord() {
    return word;
  }

  /**
   * 問題の難易度が引数の難易度以下か判定する。
   *
   * @param difficulty 問題の難易度
   * @return 問題の難易度が引数の難易度以下だった場合は、trueを返却する
   */
  public boolean lessThan(TypingGameDifficulty difficulty) {
    return this.difficulty.lessThan(difficulty);
  }

  /**
   * 問題の難易度が引数の問題の単語と同じか判定する。
   *
   * @param word 問題の単語
   * @return 問題の難易度が引数の問題の単語と同じ場合はtrueを返却する
   */
  public boolean isSame(String word) {
    return this.word.equals(word);
  }

  /**
   * 問題の難易度が引数の問題の単語と同じか判定する。
   *
   * @param question 問題の単語
   * @return 問題の難易度が引数の問題の単語と同じ場合はtrueを返却する
   */
  public boolean isSame(Question question) {
    return isSame(question.word);
  }

  /**
   * 問題の文字列表現を返却します。（デバッグ用）
   *
   * @return 問題の文字列表現
   */
  @Override
  public String toString() {
    return "Question[word=" + word + ", difficulty=" + difficulty + "]";
  }
}
