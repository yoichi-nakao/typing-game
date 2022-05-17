import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * 一回のタイピングゲームを表現するクラス。
 */
public class TypingGame {
  /**
   * タイピングゲームの難易度
   */
  private final TypingGameDifficulty difficulty;

  /**
   * 出題対象の問題。
   */
  private final List<Question> questions;

  /**
   * 出題した問題の履歴
   */
  private final List<QuestionResult> results = new ArrayList<>();

  /**
   * タイピングゲームの開始時間
   */
  private ZonedDateTime startTime;

  /**
   * タイピングゲームの終了時間
   */
  private ZonedDateTime endTime;

  /**
   * 前回出題した問題
   */
  private Question prevQuestion;

  /**
   * コンストラクタ。
   *
   * @param difficulty タイピングゲームの難易度
   * @param questions  出題対象の問題
   */
  public TypingGame(TypingGameDifficulty difficulty, List<Question> questions) {
    this.difficulty = difficulty;
    this.questions = questions;
  }

  /**
   * タイピングゲームを実行する。
   */
  public void execute() {
    System.out.println("Start!");
    startTime = ZonedDateTime.now();

    int numberOfQuestions = getNumberOfQuestions();
    for (int i = 0; i < numberOfQuestions; i++) {
      Question question = getNextQuestion();
      String prompt = question.getWord() + ": ";
      while (true) {
        String input = StandardInputReader.getInputString(prompt);
        boolean judge = question.isSame(input);
        results.add(new QuestionResult(question, input, judge));

        if (judge) {
          System.out.println("OK!");
          break;
        } else {
          System.out.println("MISS...");
        }
      }
    }

    endTime = ZonedDateTime.now();

    System.out.println("Finished. time=" + getTimeToCalculate() + "[ms]");
  }

  /**
   * 出題した問題の履歴を返却する。
   *
   * @return 出題した問題の履歴
   */
  public List<QuestionResult> getResults() {
    return results;
  }

  /**
   * 全問終了するのにかかった時間を返却する。
   *
   * @return 全問終了するのにかかった時間
   */
  public long getTimeToCalculate() {
    return ChronoUnit.MILLIS.between(startTime, endTime);
  }

  /**
   * 引数で指定されたフォーマットで開始時間の文字列表現を返却する。
   *
   * @param format 開始時間の文字列表現のフォーマット
   * @return 開始時間の文字列表現
   */
  public String getStartDateTime(String format) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
    return formatter.format(startTime);
  }

  /**
   * タイピングゲームの難易度が引数で指定された難易度と同じか判定する。
   *
   * @param difficulty 難易度
   * @return タイピングゲームの難易度が引数で指定された難易度と同じ場合は、trueを返却する。
   */
  public boolean isSameDifficulty(TypingGameDifficulty difficulty) {
    return this.difficulty == difficulty;
  }

  /**
   * タイピングゲームが出題する問題数を返却する。
   *
   * @return タイピングゲームが出題する問題数
   */
  private int getNumberOfQuestions() {
    switch (difficulty) {
      case EASY:
        return 5;
      case NORMAL:
        return 10;
      case HARD:
        return 20;
      default:
        throw new RuntimeException("不正な難易度です。");
    }
  }

  /**
   * 次に出題する問題を返却する。
   *
   * @return 次に出題する問題
   */
  private Question getNextQuestion() {
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

  /**
   * タイピングゲームの文字列表現を返却します。（デバッグ用）
   *
   * @return タイピングゲームの文字列表現
   */
  @Override
  public String toString() {
    return "TypingGame[difficulty=" + difficulty + ", timeToCalculate=" + getTimeToCalculate() + "]";
  }
}
