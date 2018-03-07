package views_and_presenters;

import com.example.server.Model.TicketToRideGame;
import com.example.server.Results.Result;

import java.util.List;

/**
 * Created by HillcollegeMac on 1/27/18.
 */

public interface IGameWaitingLobbyPresenter {

    Result joinGame(int gameId);

    void callJoinGameService(TicketToRideGame game);

    List<TicketToRideGame> getAllGamesList();

    void setAllGamesList(List<TicketToRideGame> list);

    boolean gameSelected();

    void setCurrGame(TicketToRideGame game);
}
