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
        if (obs == ClientModelRoot.instance()) {
            ClientModelRoot.instance().updateCurrentGame();
            mGame = ClientModelRoot.instance().getCurrGame();
            if (mGame.getPlayers().size() == mGame.getMaxNumPlayers()) {
                didGameStart();
            }
        }
    }

    public TicketToRideGame getGame() {
        return mGame;
    }

    // for user
    public boolean didGameStart() {
        if (mGame.getMaxNumPlayers() == mGame.getPlayers().size()) {
            mGameView.gameStarted();
            mGameView.changeTitle("");
            mGameView.displayToast("Game " + mGameView.getGameStatus());
            mGameView.toggleDrawButtons(true);
            return true;
        }
        return false;
    }
}
