package views_and_presenters;

import com.example.server.Model.Player;
import com.example.server.Model.Route;
import com.example.server.Model.TicketToRideGame;
import com.example.server.Results.Result;

import java.util.List;

/**
 * Created by fryti on 3/14/2018.
 */

public interface IClaimRoutePresenter {
    boolean hasEnoughTrains();

    boolean alreadyOwnsIdenticalRoute();

    void changeWildRouteColor();

    boolean anyCardsSelected();

    Result claimRoute();

    void updateGame(TicketToRideGame game);

    boolean selectRoute(Route selectedRoute, int pos);

    Route getSelectedRoute();

    List<Route> getAllAvailableRoutes();

    void cardsSelected(int button);

    List<Boolean> getCardsList();

    Player getUser();
}
