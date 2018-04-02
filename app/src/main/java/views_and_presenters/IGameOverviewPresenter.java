package views_and_presenters;

import com.example.server.Model.Player;
import com.example.server.Model.TicketToRideGame;
import com.example.server.Results.Result;

/**
 * Created by kurtishill on 3/13/18.
 */

public interface IGameOverviewPresenter {

    TicketToRideGame getGame();

    Result quitGame();

    void quitGameOnPostExecute();
    int DestinationCardCalc(Player currPlayer);
    int LongestPath();
    int UnreachedDestinationCardCalc(Player currPlayer);
}
