package client_model;

import com.example.server.Model.DestinationCard;

import java.util.List;

import views_and_presenters.GamePresenter;

/**
 * Created by tnels on 3/14/2018.
 */

public class GameOverState implements State {
    @Override
    public void placeTrains(GamePresenter gamePresenter) {
        return;
    }

    @Override
    public boolean routeSelected(GamePresenter gamePresenter, String selectedRoute, List<DestinationCard> mAllRoutes, List<DestinationCard> mSelectedRoutes) {
        return false;
    }

    @Override
    public void drawTrainCards(GamePresenter gamePresenter) {
        return;
    }

    @Override
    public String toString()
    {
        return "gameOver";
    }
}
