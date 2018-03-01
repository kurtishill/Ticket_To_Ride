package views_and_presenters;

import com.example.server.Model.Player;
import com.example.server.Model.TicketToRideGame;

import client_model.ClientModelRoot;

/**
 * Created by kurtis on 3/1/18.
 */

public class PlayerStatsPresenter implements IPlayerStatsPresenter {

    private IPlayerStatsView mPlayerStatsView;
    private TicketToRideGame mGame;
    private Player mUser;

    public PlayerStatsPresenter(IPlayerStatsView v) {
        mPlayerStatsView = v;
        setGame();
        setUser();
    }

    private void setGame() {
        mGame = ClientModelRoot.instance().getCurrGame();
    }

    private void setUser() {
        mUser = ClientModelRoot.instance().getUser();
    }

    public TicketToRideGame getGame() {
        return mGame;
    }

    public Player getUser() {
        return mUser;
    }

    public IPlayerStatsView getPlayerStatsView() {
        return mPlayerStatsView;
    }
}
