package business;

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
   * タイピングゲームのリスナー。
   */
  private final TypingGameListener listener;

  /**
   * 次の問題の生成インターフェース。
   */
  private final NextQuestionGenerator generator;

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
   * コンストラクタ。
   *
   * @param difficulty タイピングゲームの難易度
   * @param listener   タイピングゲームのリスナー
   * @param generator  次の問題の生成インターフェース
   */
  public TypingGame(TypingGameDifficulty difficulty, TypingGameListener listener, NextQuestionGenerator generator) {
    this.difficulty = difficulty;
    this.listener = listener;
    this.generator = generator;
  }

  /**
   * タイピングゲームを実行する。
   */
  public void execute() {
    listener.onGameStart(this);

    startTime = ZonedDateTime.now();

    int numberOfQuestions = getNumberOfQuestions();
    for (int i = 0; i < numberOfQuestions; i++) {
      Question question = generator.generate();
      while (true) {
        String input = listener.receiveAnswer(question);
        boolean judge = question.isSame(input);
        results.add(new QuestionResult(question, input, judge));

        if (judge) {
          listener.onJudgeOK(this);
          break;
        } else {
          listener.onJudgeNG(this);
        }
      }
    }

    endTime = ZonedDateTime.now();

    listener.onGameEnd(this);
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
   * タイピングゲームの文字列表現を返却します。（デバッグ用）
   *
   * @return タイピングゲームの文字列表現
   */
  @Override
  public String toString() {
    return "TypingGame[difficulty=" + difficulty + ", timeToCalculate=" + getTimeToCalculate() + "]";
  }
}
