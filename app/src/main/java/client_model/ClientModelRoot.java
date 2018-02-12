package client_model;

import com.example.server.Model.Player;
import com.example.server.Model.TicketToRideGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

/**
 * Created by HillcollegeMac on 1/30/18.
 */

public class ClientModelRoot extends Observable {

    private static ClientModelRoot _instance;

    private List<TicketToRideGame> mGamesList;
    private Map<String, Player> mPlayers;
    private Player mUser;
    private String mAuthToken;
    private TicketToRideGame mCurrGame;

    public static ClientModelRoot instance() {
        if (_instance == null)
            _instance = new ClientModelRoot();
        return _instance;
    }

    private ClientModelRoot() {
        mGamesList = new ArrayList<>();
        mPlayers = new HashMap<>();
        mUser = null;
        mAuthToken = null;
        mCurrGame = null;
    }

    public List<TicketToRideGame> getGamesList() {
        return mGamesList;
    }

    public Map<String, Player> getPlayers() {
        return mPlayers;
    }

    public Player getUser() {
        return mUser;
    }

    public TicketToRideGame getCurrGame() {
        return mCurrGame;
    }

    public String getAuthToken() {
        return mAuthToken;
    }

    public void setGames(List<TicketToRideGame> games) {
        mGamesList = games;
        setChanged();
        notifyObservers(this);
        clearChanged();
    }

    public void updateCurrentGame() {
        for (int i = 0; i < mGamesList.size(); i++) {
            if (mCurrGame.getGameID() == mGamesList.get(i).getGameID()) {
                mCurrGame = mGamesList.get(i);
            }
        }
    }

    public void setPlayers(Map<String, Player> players) {
        mPlayers = players;
    }

    public void setUser(Player user) {
        mUser = user;
    }

    public void setAuthToken(String authToken) {
        mAuthToken = authToken;
    }

    public void setCurrGame(TicketToRideGame currGame) {
        mCurrGame = currGame;
    }

    public void addUserToCurrGame() {
        mCurrGame.getPlayers().add(mUser);
    }
}
