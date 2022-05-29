import ui.ConsoleConductor;

/**
 * タイピングゲームを実行するクラス。
 */
public class Main {
  /**
   * タイピングゲームのエントリーポイント。
   *
   * @param args タイピングゲームの引数
   */
  public static void main(String[] args) {
    ConsoleConductor conductor = new ConsoleConductor();
    conductor.execute();
  }
}
