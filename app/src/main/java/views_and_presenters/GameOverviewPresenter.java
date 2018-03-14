package views_and_presenters;

import com.example.server.Model.TicketToRideGame;
import com.example.server.Results.Result;

import java.util.ArrayList;
import java.util.List;

import Network.ServerProxy;
import client_model.ClientModelRoot;
import gui_facade.QuitGameService;

/**
 * Created by kurtishill on 3/13/18.
 */

public class GameOverviewPresenter implements IGameOverviewPresenter {

    private IGameOverviewView mGameOverviewView;
    private TicketToRideGame mGame;

    public GameOverviewPresenter(IGameOverviewView v) {
        mGameOverviewView = v;
        mGame = ClientModelRoot.instance().getCurrGame();
    }

    public TicketToRideGame getGame() {
        return mGame;
    }

    public Result quitGame() {
        Integer gameId = mGame.getGameID();
        List<Object> data = new ArrayList<>();
        data.add(gameId);
        Result result = ServerProxy.getInstance()
                .command("DeleteGame", data, ClientModelRoot.instance().getAuthToken());
        return result;
    }

    public void quitGameOnPostExecute() {
        QuitGameService.quitGame();
    }
}
