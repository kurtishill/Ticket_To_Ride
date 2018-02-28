package views_and_presenters;

import com.example.server.Model.DestinationCard;
import com.example.server.Results.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HillcollegeMac on 2/15/18.
 */

public interface IDestinationPickerPresenter {

    boolean routeSelected(String selectedRoute);

    ArrayList<DestinationCard> getSelectedRoutes();

    Result onClickRoutesChosen();

    Result drawThreeCards();

    void postExecuteDrawCards();
    void postExecuteSelectCards(List<DestinationCard> selectedCards, List<DestinationCard> discardedCards);
    void setAllRoutes(List<DestinationCard> allRoutes);

    boolean getRouteSelectionChange();
}
