package client_model;

import com.example.server.Model.DestinationCard;

import java.util.List;

import views_and_presenters.GamePresenter;

/**
 * Created by tnels on 3/14/2018.
 */

public interface State {

    void placeTrains(GamePresenter gamePresenter);
    boolean routeSelected(GamePresenter gamePresenter, String selectedRoute, List<DestinationCard> mAllRoutes, List<DestinationCard> mSelectedRoutes);
    void drawTrainCards(GamePresenter gamePresenter);

    String toString();
}
