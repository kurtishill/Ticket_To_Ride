package views_and_presenters;

/**
 * Created by HillcollegeMac on 2/7/18.
 */

public interface IGameView {

    void displayToast(String toast);

    void changeTitle(String title);

    void gameStarted();

    String getGameStatus();

    void toggleButtons(boolean toggle);
}
