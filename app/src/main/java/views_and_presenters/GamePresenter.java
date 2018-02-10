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

    public void update(Observable obs, Object obj) {
        if (obs == ClientModelRoot.instance()) {
            ClientModelRoot.instance().updateCurrentGame();
            mGame = ClientModelRoot.instance().getCurrGame();
            if (GetGamesService.getCurrGame().getPlayers().size() ==
                    GetGamesService.getCurrGame().getMaxNumPlayers()) {
                mGameView.changeTitle("Game Started");
                mGameView.displayToast("Game started");
            }
        }
    }

    public TicketToRideGame getGame() {
        return mGame;
    }
}
