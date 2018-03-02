package views_and_presenters;

import com.example.server.Model.Player;
import com.example.server.Model.TicketToRideGame;

/**
 * Created by kurtis on 3/1/18.
 */

public interface IPlayerStatsPresenter {

    TicketToRideGame getGame();

    Player getUser();
}
