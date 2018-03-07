package views_and_presenters;

import com.example.server.Model.DestinationCard;
import com.example.server.Model.TicketToRideGame;
import com.example.server.Results.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HillcollegeMac on 2/15/18.
 */

public interface IDestinationPickerPresenter {

    boolean routeSelected(String selectedRoute);

    Result onClickRoutesChosen();

    Result drawThreeCards();

    void setAllRoutes(List<DestinationCard> allRoutes);
    void updateGame(TicketToRideGame game);
    boolean isRouteAlreadySelected(String route);
    boolean getRouteSelectionChange();
}
