package ui;

import business.Question;
import business.TypingGame;
import business.TypingGameListener;

import java.util.List;

/**
 * アツいタイピングゲームの入出力用のリスナークラス。
 *
 * @see TypingGameListener
 */
public class HotTypingGameListener implements TypingGameListener {
  /**
   * 最初の問題出題時に表示するメッセージ。
   */
  private final String FIRST_RECEIVE_MESSAGE = "回答を入力するんだ！";

  /**
   * 問題出題時に表示するメッセージ。
   */
  private final List<String> receiveMessages = List.of(FIRST_RECEIVE_MESSAGE, "次の問題だ！", "まだまだ行くぞ！");

  /**
   * タイピングゲームの解答判定がOKだった時に表示するメッセージ。
   */
  private final List<String> judgeOKMessages = List.of("正解だ！よくやった！", "OK！やるじゃないか！", "いいぞ！");

  /**
   * タイピングゲームの解答判定がNGだった時に表示するメッセージ。
   */
  private final List<String> judgeNGMessages = List.of("間違っているぞ！どうした！？", "違うぞ！", "惜しい！");

  /**
   * 解答を受け付けた回数。
   */
  private int receiveAnswerCount = 0;

  @Override
  public void onGameStart(TypingGame typingGame) {
    System.out.println("ゲーム開始だ！準備はいいか！？!");
  }

  @Override
  public void onGameEnd(TypingGame typingGame) {
    System.out.println("ゲーム終了だ！おつかれ！！ かかった時間は、" + typingGame.getTimeToCalculate() + "msだ！");
  }

  @Override
  public String receiveAnswer(Question question) {
    String message = receiveAnswerCount == 0 ? FIRST_RECEIVE_MESSAGE : getMessage(receiveMessages);
    System.out.println(message);
    receiveAnswerCount++;
    String prompt = question.getWord() + ": ";
    return StandardInputReader.getInputString(prompt);
  }

  @Override
  public void onJudgeOK(TypingGame typingGame) {
    System.out.println(getMessage(judgeOKMessages));
  }

  @Override
  public void onJudgeNG(TypingGame typingGame) {
    System.out.println(getMessage(judgeNGMessages));
  }

  private String getMessage(List<String> messages) {
    double indexAsDouble = Math.random() * messages.size();
    int index = (int) indexAsDouble;
    return messages.get(index);
  }
}
