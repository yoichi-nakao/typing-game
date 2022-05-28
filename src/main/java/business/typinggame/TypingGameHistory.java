package business.typinggame;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * タイピングゲームの履歴を表現するクラス。
 */
public class TypingGameHistory {
  /**
   * タイピングゲームの履歴
   */
  private final List<TypingGame> typingGames = new ArrayList<>();

  /**
   * タイピングゲームの履歴に追加する。
   *
   * @param typingGame 追加するタイピングゲーム
   */
  public void add(TypingGame typingGame) {
    typingGames.add(typingGame);
  }

  /**
   * 引数で指定された難易度のタイピングゲームの履歴を降順にソートして、指定した件数だけ返却する。
   *
   * @param difficulty タイピングゲームの難易度
   * @param limit      返却する件数
   * @return 引数で指定された難易度のタイピングゲームの履歴
   */
  public List<TypingGame> sortedTypingGames(TypingGameDifficulty difficulty, int limit) {
    return sortedTypingGames(difficulty).stream()
            .limit(limit)
            .collect(Collectors.toUnmodifiableList());
  }

  /**
   * 引数で指定された難易度のタイピングゲームの履歴を返却する。
   *
   * @param difficulty タイピングゲームの難易度
   * @return 引数で指定された難易度のタイピングゲームの履歴
   */
  public List<TypingGame> sortedTypingGames(TypingGameDifficulty difficulty) {
    return getTypingGames(difficulty).stream()
            .sorted((o1, o2) -> (int) (o1.getTimeToCalculate() - o2.getTimeToCalculate()))
            .collect(Collectors.toUnmodifiableList());
  }

  /**
   * 引数で指定された難易度のタイピングゲームの履歴を返却する。
   *
   * @param difficulty タイピングゲームの難易度
   * @return 引数で指定された難易度のタイピングゲームの履歴
   */
  public List<TypingGame> getTypingGames(TypingGameDifficulty difficulty) {
    return typingGames.stream()
            .filter(tg -> tg.isSameDifficulty(difficulty))
            .collect(Collectors.toUnmodifiableList());
  }
}
