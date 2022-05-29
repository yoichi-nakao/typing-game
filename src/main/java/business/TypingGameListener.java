package business;

/**
 * タイピングゲームの各イベントで呼び出されるリスナーインターフェース。
 */
public interface TypingGameListener {
  /**
   * タイピングゲーム開始時に呼び出される。
   *
   * @param typingGame 実行中のタイピングゲームのインスタンス
   */
  void onGameStart(TypingGame typingGame);

  /**
   * タイピングゲーム終了時に呼び出される。
   *
   * @param typingGame 実行中のタイピングゲームのインスタンス
   */
  void onGameEnd(TypingGame typingGame);

  /**
   * 入力した解答を返却する。
   *
   * @param question 出題される問題
   * @return 入力した解答
   */
  String receiveAnswer(Question question);

  /**
   * タイピングゲームの解答判定がOKだった時に呼び出される。
   *
   * @param typingGame 実行中のタイピングゲームのインスタンス
   */
  void onJudgeOK(TypingGame typingGame);

  /**
   * タイピングゲームの解答判定がNGだった時に呼び出される。
   *
   * @param typingGame 実行中のタイピングゲームのインスタンス
   */
  void onJudgeNG(TypingGame typingGame);
}
