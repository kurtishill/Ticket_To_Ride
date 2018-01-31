package views_and_presenters;

import com.example.server.Model.TicketToRideGame;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import client_model.ClientModelRoot;
import gui_facade.JoinGameService;

/**
 * Created by HillcollegeMac on 1/29/18.
 */

public class GameWaitingLobbyPresenter implements IGameWaitingLobbyPresenter, Observer {

    private List<TicketToRideGame> mAllGamesList;
    private IGameWaitingLobbyView mGameWaitingLobbyView;

    public GameWaitingLobbyPresenter(IGameWaitingLobbyView v) {
        mGameWaitingLobbyView = v;
        ClientModelRoot.instance().addObserver(this);
    }

    public void joinGame(TicketToRideGame game) {
        JoinGameService.joinGame(game);

        //TODO: call serverproxy
    }

    public void update(Observable obs, Object obj) {

        // check to see if obs is the observable (in this case ClientModelRoot)
        // that the presenter is observing
        if (obs == ClientModelRoot.instance()) {
            mAllGamesList = ClientModelRoot.instance().getGames();
            mGameWaitingLobbyView.displayGameList();
        }
    }

    public List<TicketToRideGame> getAllGamesList() {
        return mAllGamesList;
    }

    public void setAllGamesList(List<TicketToRideGame> list) {
        mAllGamesList = list;
    }

    public boolean gameSelected() {
        return true;
    }
}
