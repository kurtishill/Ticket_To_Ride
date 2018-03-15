package views_and_presenters;

import com.example.server.Model.City;
import com.example.server.Model.Player;
import com.example.server.Model.Route;
import com.example.server.Results.Result;

import java.util.ArrayList;
import java.util.List;

import Network.ServerProxy;
import client_model.ClientModelRoot;
import gui_facade.GetPlayersService;

/**
 * Created by fryti on 3/14/2018.
 */

public class ClaimRoutePresenter implements IClaimRoutePresenter {
    Route mSelectedRoute;
    List<Route> mAllAvailableRoutes;

    public ClaimRoutePresenter(List<Route> allAvailableRoutes){
        mAllAvailableRoutes = allAvailableRoutes;
    }

    public void selectRoute(String selectedRoute){
        for(int i=0; i<mAllAvailableRoutes.size(); i++){
            if(selectedRoute.equals(mAllAvailableRoutes.get(i).toString()))
                mSelectedRoute = mAllAvailableRoutes.get(i);
        }
    }
    @Override
    public boolean hasEnoughTrains(Route route) {
        int length = route.getLength();
        String color = route.getColor();
        Player currentPlayer = GetPlayersService.getCurrentPlayer();
        int numAppropriatePlayerTrains = 0;
        for(int i=0; i<currentPlayer.getTrainCards().size(); i++){
            //Checks if the train cards the player has are either the correct color or wild
            if(currentPlayer.getTrainCards().get(i).getColor().equals(color) || currentPlayer.getTrainCards().get(i).getColor().equals("wild"))
                numAppropriatePlayerTrains++;
        }
        if(numAppropriatePlayerTrains>=length)
            return true;
        else return false;
    }
    //Same player cannot build on both routes of a double route, so this method returns true if
    //a player already owns one of the two, and therefore should not be allowed to claim the second
    public boolean alreadyOwnsIdenticalRoute(Route route){
        Player currentPlayer = GetPlayersService.getCurrentPlayer();
        City city1 = route.getCity1();
        City city2 = route.getCity2();
        for(int i=0; i<currentPlayer.getClaimedRoutes().size(); i++){
            City ownedCity1 = currentPlayer.getClaimedRoutes().get(i).getCity1();
            City ownedCity2 = currentPlayer.getClaimedRoutes().get(i).getCity2();
            if(city1.equals(ownedCity1) && city2.equals(ownedCity2))
                return true;
        }
        return false;
    }

    @Override
    public Result claimRoute(Route selectedRoute) {
        List<Object> data = new ArrayList<>();
        data.add(ClientModelRoot.instance().getUser().getUsername());
        data.add(ClientModelRoot.instance().getCurrGame().getGameID());
        data.add(mSelectedRoute);
        return ServerProxy.getInstance()
                .command("ClaimRoute",data,null);
    }
}
