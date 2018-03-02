package views_and_presenters;

import com.example.server.Model.TicketToRideGame;

import java.util.Observable;
import java.util.Observer;

import client_model.ClientModelRoot;
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
        }
    }

    public TicketToRideGame getGame() {
        return mGame;
    }

    // for user
    public boolean didGameStart() {
        if (mGame.getMaxNumPlayers() == mGame.getPlayers().size()) {
            if (mGameView.getGameStatus() != null)
                mGameView.gameStarted("Game " + mGameView.getGameStatus());
            else
                mGameView.gameStarted("Game started");
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
        if (ClientModelRoot.instance().getCurrGame().getTurn() != indexOfUser)
            return false;
        else
            return true;
    }
}
