package views_and_presenters;

import com.example.server.Model.GameHistory;

import java.util.List;

/**
 * Created by kurtis on 3/2/18.
 */

public interface IGameHistoryPresenter {

    List<GameHistory> getGameHistoryList();
}
