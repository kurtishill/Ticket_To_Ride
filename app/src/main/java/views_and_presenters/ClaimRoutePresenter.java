package views_and_presenters;

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
