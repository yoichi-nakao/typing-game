/**
 * 通常のタイピングゲームの入出力用のリスナークラス。
 *
 * @see TypingGameListener
 */
public class NormalTypingGameListener implements TypingGameListener {
  @Override
  public void onGameStart(TypingGame typingGame) {
    System.out.println("Start!");
  }

  @Override
  public void onGameEnd(TypingGame typingGame) {
    System.out.println("Finished. time=" + typingGame.getTimeToCalculate() + "[ms]");
  }

  @Override
  public String receiveAnswer(Question question) {
    String prompt = question.getWord() + ": ";
    return StandardInputReader.getInputString(prompt);
  }

  @Override
  public void onJudgeOK(TypingGame typingGame) {
    System.out.println("OK!");
  }

  @Override
  public void onJudgeNG(TypingGame typingGame) {
    System.out.println("MISS...");
  }
}
