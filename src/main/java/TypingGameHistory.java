import java.util.Arrays;
import java.util.Comparator;

/**
 * タイピングゲームの履歴を表現するクラス。
 */
public class TypingGameHistory {
  /**
   * タイピングゲームの履歴
   */
  private TypingGame[] typingGames;

  /**
   * タイピングゲームの履歴に追加する。
   *
   * @param typingGame 追加するタイピングゲーム
   */
  public void add(TypingGame typingGame) {
    if (typingGames == null) {
      typingGames = new TypingGame[1];
      typingGames[0] = typingGame;
      return;
    }

    TypingGame[] newHistory = new TypingGame[typingGames.length + 1];
    System.arraycopy(typingGames, 0, newHistory, 0, typingGames.length);
    newHistory[typingGames.length] = typingGame;
    typingGames = newHistory;
  }

  /**
   * 引数で指定された難易度のタイピングゲームの履歴を降順にソートして返却する。
   *
   * @param difficulty タイピングゲームの難易度
   * @return 引数で指定された難易度のタイピングゲームの履歴
   */
  public TypingGame[] sortedTypingGames(TypingGameDifficulty difficulty) {
    TypingGame[] targetTypingGames = getTypingGames(difficulty);
    Arrays.sort(targetTypingGames, new Comparator<TypingGame>() {
      @Override
      public int compare(TypingGame o1, TypingGame o2) {
        return (int) (o1.getTimeToCalculate() - o2.getTimeToCalculate());
      }
    });
    return targetTypingGames;
  }

  /**
   * 引数で指定された難易度のタイピングゲームの履歴を返却する。
   *
   * @param difficulty タイピングゲームの難易度
   * @return 引数で指定された難易度のタイピングゲームの履歴
   */
  private TypingGame[] getTypingGames(TypingGameDifficulty difficulty) {
    int count = 0;
    for (TypingGame typingGame : typingGames) {
      if (typingGame.isSameDifficulty(difficulty)) {
        count++;
      }
    }

    TypingGame[] targetTypingGames = new TypingGame[count];
    int i = 0;
    for (TypingGame typingGame : typingGames) {
      if (typingGame.isSameDifficulty(difficulty)) {
        targetTypingGames[i] = typingGame;
        i++;
      }
    }

    return targetTypingGames;
  }
}
