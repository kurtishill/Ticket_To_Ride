package views_and_presenters;

import com.example.server.Model.TicketToRideGame;

import java.util.List;

/**
 * Created by HillcollegeMac on 1/27/18.
 */

public interface IGameWaitingLobbyPresenter {

    void joinGame(TicketToRideGame game);

    List<TicketToRideGame> getAllGamesList();

    void setAllGamesList(List<TicketToRideGame> list);

    boolean gameSelected();
}
