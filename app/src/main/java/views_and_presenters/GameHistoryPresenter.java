package views_and_presenters;

import com.example.server.Model.GameHistory;

import java.util.List;

import client_model.ClientModelRoot;

/**
 * Created by kurtis on 3/2/18.
 */

public class GameHistoryPresenter implements IGameHistoryPresenter {

    private IGameHistoryView mGameHistoryView;
    private List<GameHistory> mGameHistoryList;

    public GameHistoryPresenter(IGameHistoryView v) {
        mGameHistoryView = v;
        mGameHistoryList = ClientModelRoot.instance().getCurrGame().getGameHistoryList();
    }

    public List<GameHistory> getGameHistoryList() {
        return mGameHistoryList;
    }
}
