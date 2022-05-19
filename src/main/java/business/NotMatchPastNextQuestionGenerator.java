package business;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 過去の問題と一致しないように次の問題をランダムに生成するクラス。
 *
 * @see AbstractNextQuestionGenerator
 */
public class NotMatchPastNextQuestionGenerator extends AbstractNextQuestionGenerator {
  /**
   * 過去に出題した問題の履歴。
   */
  private final Set<Question> askedQuestions = new HashSet<>();

  /**
   * コンストラクタ。
   *
   * @param questions 出題対象の問題
   */
  public NotMatchPastNextQuestionGenerator(List<Question> questions) {
    super(questions);
  }

  @Override
  public Question generate() {
    while (true) {
      double indexAsDouble = Math.random() * questions.size();
      int index = (int) indexAsDouble;
      Question question = questions.get(index);
      boolean questionNotContains = askedQuestions.stream().noneMatch(q -> q.isSame(question));
      if (questionNotContains) {
        askedQuestions.add(question);
        return question;
      }
    }
  }
}
