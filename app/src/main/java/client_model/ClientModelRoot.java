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

    private List<TicketToRideGame> mGames;
    private Map<String, Player> mPlayers;
    private Player mUser;
    private int mLatestGameId;
    private TicketToRideGame mCurrGame;

    public static ClientModelRoot instance() {
        if (_instance == null)
            _instance = new ClientModelRoot();
        return _instance;
    }

    private ClientModelRoot() {
        mGames = new ArrayList<>();
        mPlayers = new HashMap<>();
        mUser = new Player();
        mLatestGameId = 0;
        mCurrGame = null;
    }

    public List<TicketToRideGame> getGames() {
        return mGames;
    }

    public Map<String, Player> getPlayers() {
        return mPlayers;
    }

    public Player getUser() {
        return mUser;
    }

    public int getLatestGameId() {
        return mLatestGameId;
    }

    public TicketToRideGame getCurrGame() {
        return mCurrGame;
    }

    public void setGames(List<TicketToRideGame> games) {
        mGames = games;
        setChanged();
        notifyObservers(games);
        clearChanged();
    }

    public void setPlayers(Map<String, Player> players) {
        mPlayers = players;
    }

    public void setUser(Player user) {
        mUser = user;
    }

    public void setLatestGameId(int id) {
        mLatestGameId = id;
    }

    public void setCurrGame(TicketToRideGame currGame) {
        mCurrGame = currGame;
    }


    public void addUserToCurrGame() {
        mCurrGame.getPlayers().add(mUser);
    }
}
