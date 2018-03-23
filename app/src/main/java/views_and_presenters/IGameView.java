package views_and_presenters;

import com.example.server.Model.Player;
import com.example.server.Model.Route;

/**
 * Created by HillcollegeMac on 2/7/18.
 */

public interface IGameView {

    void displayToast(String toast);

    void changeTitle(String title);

    String getGameStatus();

    void toggleButtons(boolean toggle);

    void gameStarted(final String toast);

    void drawRouteLine(Route route, Player player);

    void displayPlayerTurn();

    void onStartUp();

    boolean checkForGameOver();

    void endGame();
}
