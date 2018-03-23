package views_and_presenters;

import com.example.server.Model.Player;
import com.example.server.Model.Route;
import com.example.server.Model.TicketToRideGame;
import com.example.server.Results.ClientCommand;

import java.util.Observable;
import java.util.Observer;

import client_model.ClientModelRoot;
import client_model.StartUpState;
import client_model.State;
import gui_facade.EditObserversInModel;
import gui_facade.GetGamesService;

/**
 * Created by HillcollegeMac on 2/7/18.
 */

public class GamePresenter implements IGamePresenter, Observer {

    private TicketToRideGame mGame;
    private IGameView mGameView;

    public GamePresenter(IGameView v) {
        mGameView = v;
        mGame = GetGamesService.getCurrGame();
        EditObserversInModel.addObserverToModel(this);
    }


    // for other clients
    public void update(Observable obs, Object obj) {
        if (obj.equals(ClientModelRoot.instance().getGamesList())) {
            ClientModelRoot.instance().updateCurrentGame();
            TicketToRideGame checkGame = mGame;
            mGame = ClientModelRoot.instance().getCurrGame();
            if (mGame.getPlayers().size() == mGame.getMaxNumPlayers() &&
                    checkGame.getPlayers().size() < mGame.getPlayers().size()) {
                didGameStart();
            }

            // stuff for the game to be updated with
            mGameView.displayPlayerTurn();

            mGameView.toggleButtons(isItUsersTurn());

            mGameView.checkForLastTurn();
            //if true start game over fragment??
        }
        //Draw all claimed routes on the map, iterating through players who possess claimed routes
        for(int i=0; i< ClientModelRoot.instance().getCurrGame().getPlayers().size(); i++){
            for(int j=0; j<ClientModelRoot.instance().getCurrGame().getPlayers().get(i).getClaimedRoutes().size(); j++){
                Player thisPlayer = ClientModelRoot.instance().getCurrGame().getPlayers().get(i);
                Route thisRoute = ClientModelRoot.instance().getCurrGame().getPlayers().get(i).getClaimedRoutes().get(j);
                mGameView.drawRouteLine(thisRoute, thisPlayer);
            }
        }
    }

    public TicketToRideGame getGame() {
        return mGame;
    }

    // for user
    public boolean didGameStart() {
        if (mGame.getMaxNumPlayers() == mGame.getPlayers().size()) {
            if (getUser().getState().equals("startup"))
                mGameView.onStartUp();
            if (mGameView.getGameStatus() != null) {
                mGameView.gameStarted("Game " + mGameView.getGameStatus());
                if (mGameView.getGameStatus().equals("started"))
                    return true;
            }
            else {
                mGameView.gameStarted("Game started");
            }
            return true;
        }
        return false;
    }

    public boolean isItUsersTurn() {
        int indexOfUser = 0;
        for (int i = 0; i < mGame.getPlayers().size(); i++) {
            if (mGame.getPlayers().get(i).getUsername().equals(ClientModelRoot.instance().getUser().getUsername())) {
                indexOfUser = i;
                break;
            }
        }

        mGameView.checkForLastTurn();

        if (ClientModelRoot.instance().getCurrGame().getTurn() != indexOfUser)
            return false;
        else
            return true;
    }

    public Player getUser() {
        return ClientModelRoot.instance().getUser();
    }
}
