package client_model;

import com.example.server.Model.DestinationCard;
import com.example.server.Model.Route;

import java.util.List;

import views_and_presenters.GamePresenter;

/**
 * Created by tnels on 3/14/2018.
 */

public class LastTurnState implements State {
    @Override
    public void placeTrains(GamePresenter gamePresenter) {
        //same as turn state
    }

    @Override
    public boolean routeSelected(GamePresenter gamePresenter, String selectedRoute, List<DestinationCard> mAllRoutes, List<DestinationCard> mSelectedRoutes) {
        //add method from game presenter

        for(int i = 0; i < mAllRoutes.size(); i++) {
            if (mAllRoutes.get(i).toString().equals(selectedRoute)) {

                if (mSelectedRoutes.contains(mAllRoutes.get(i)))
                    mSelectedRoutes.remove(mAllRoutes.get(i));
                else
                    mSelectedRoutes.add(mAllRoutes.get(i));

                if (mSelectedRoutes.size() < 1)
                    return false;
                else
                    return true;
            }
        }
        return false;
    }

    @Override
    public void drawTrainCards(GamePresenter gamePresenter) {
        //same as turn state
    }

    @Override
    public String toString()
    {
        return "lastTurn";
    }
}
