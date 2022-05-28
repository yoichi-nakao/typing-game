package business.question.factory;

import business.question.Question;
import business.typinggame.TypingGameDifficulty;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 問題を生成するFactoryクラス。
 */
public class QuestionFactory {
  /**
   * 引数に指定された難易度の問題を生成する。
   *
   * @param difficulty 問題の難易度
   * @return 生成された問題
   */
  public static List<Question> generate(TypingGameDifficulty difficulty) {
    return generate().stream().filter(q -> q.lessThan(difficulty)).collect(Collectors.toUnmodifiableList());
  }

  /**
   * 全ての難易度の問題を生成する。
   *
   * @return 全ての難易度の問題
   */
  private static List<Question> generate() {
    return List.of(
            // 基本データ型関連
            new Question("byte", TypingGameDifficulty.EASY),
            new Question("short", TypingGameDifficulty.NORMAL),
            new Question("int", TypingGameDifficulty.EASY),
            new Question("long", TypingGameDifficulty.EASY),
            new Question("float", TypingGameDifficulty.NORMAL),
            new Question("double", TypingGameDifficulty.NORMAL),
            new Question("boolean", TypingGameDifficulty.NORMAL),
            new Question("void", TypingGameDifficulty.NORMAL),

            // 制御構文関連関連
            new Question("if", TypingGameDifficulty.EASY),
            new Question("else", TypingGameDifficulty.EASY),
            new Question("switch", TypingGameDifficulty.NORMAL),
            new Question("case", TypingGameDifficulty.EASY),
            new Question("default", TypingGameDifficulty.NORMAL),
            new Question("for", TypingGameDifficulty.EASY),
            new Question("while", TypingGameDifficulty.NORMAL),
            new Question("do", TypingGameDifficulty.EASY),
            new Question("continue", TypingGameDifficulty.NORMAL),
            new Question("break", TypingGameDifficulty.NORMAL),
            new Question("return", TypingGameDifficulty.NORMAL),
            new Question("try", TypingGameDifficulty.EASY),
            new Question("catch", TypingGameDifficulty.NORMAL),
            new Question("finally", TypingGameDifficulty.NORMAL),
            new Question("throw", TypingGameDifficulty.NORMAL),
            new Question("throws", TypingGameDifficulty.NORMAL),

            // クラス、パッケージ関連
            new Question("package", TypingGameDifficulty.NORMAL),
            new Question("import", TypingGameDifficulty.NORMAL),
            new Question("class", TypingGameDifficulty.NORMAL),
            new Question("interface", TypingGameDifficulty.HARD),
            new Question("extends", TypingGameDifficulty.NORMAL),
            new Question("implements", TypingGameDifficulty.HARD),
            new Question("this", TypingGameDifficulty.EASY),
            new Question("super", TypingGameDifficulty.NORMAL),
            new Question("new", TypingGameDifficulty.EASY),
            new Question("enum", TypingGameDifficulty.EASY),

            // 演算子
            new Question("instanceOf", TypingGameDifficulty.HARD),

            // 修飾子
            new Question("public", TypingGameDifficulty.NORMAL),
            new Question("protected", TypingGameDifficulty.HARD),
            new Question("private", TypingGameDifficulty.NORMAL),
            new Question("final", TypingGameDifficulty.NORMAL),
            new Question("static", TypingGameDifficulty.NORMAL),
            new Question("abstract", TypingGameDifficulty.NORMAL),
            new Question("native", TypingGameDifficulty.NORMAL),
            new Question("synchronized", TypingGameDifficulty.HARD),
            new Question("volatile", TypingGameDifficulty.NORMAL),
            new Question("transient", TypingGameDifficulty.HARD),
            new Question("strictfp", TypingGameDifficulty.NORMAL),

            // リテラル
            new Question("true", TypingGameDifficulty.EASY),
            new Question("false", TypingGameDifficulty.NORMAL),
            new Question("null", TypingGameDifficulty.EASY),

            // その他
            new Question("const", TypingGameDifficulty.NORMAL),
            new Question("goto", TypingGameDifficulty.EASY),
            new Question("assert", TypingGameDifficulty.NORMAL)
    );
  }
}
