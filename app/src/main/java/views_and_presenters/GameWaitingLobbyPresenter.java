package views_and_presenters;

import com.example.server.Model.TicketToRideGame;
import com.example.server.Results.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import Network.ServerProxy;
import client_model.ClientModelRoot;
import gui_facade.EditObserversInModel;
import gui_facade.GetGamesService;
import gui_facade.GetUserService;
import gui_facade.JoinGameService;
import gui_facade.SetPlayerColorService;

/**
 * Created by HillcollegeMac on 1/29/18.
 */

public class GameWaitingLobbyPresenter implements IGameWaitingLobbyPresenter, Observer {

    private List<TicketToRideGame> mAllGamesList;
    private IGameWaitingLobbyView mGameWaitingLobbyView;

    public GameWaitingLobbyPresenter(IGameWaitingLobbyView v) {
        mGameWaitingLobbyView = v;
        EditObserversInModel.addObserverToModel(this);
    }

    public Result joinGame(int gameId) {
        List<Object> data = new ArrayList<>();
        data.add(gameId);
        Result result = ServerProxy.getInstance()
                .command("JoinGame", data, GetUserService.getUser().getID());
        //EditObserversInModel.deleteObserverInModel(this);
        return result;
    }

    public void callJoinGameService(TicketToRideGame game) {
        JoinGameService.joinGame(game);
    }

    public void callSetPlayerColorService(String color){
        SetPlayerColorService.setPlayerColor(color);
    }

    public void callSetPlayerColorService(){
        SetPlayerColorService.setPlayerColor();
    }

    public void update(Observable obs, Object obj) {

        // check to see if obs is the observable (in this case ClientModelRoot)
        // that the presenter is observing
        if (obs == ClientModelRoot.instance()) {
            mAllGamesList = GetGamesService.getGamesList();
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
