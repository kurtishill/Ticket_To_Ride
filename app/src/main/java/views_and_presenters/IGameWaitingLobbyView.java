package views_and_presenters;

import com.example.server.Model.TicketToRideGame;

/**
 * Created by HillcollegeMac on 1/27/18.
 */

public interface IGameWaitingLobbyView {

    //returns a Game object
    TicketToRideGame getSelectedGame();

    void displayGameList();

    void enableJoinGame(boolean b);

    void toggleViews(boolean toggle);

    void toggleGUIUsability(boolean toggle);
}
