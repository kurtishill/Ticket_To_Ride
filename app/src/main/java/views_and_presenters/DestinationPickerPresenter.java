package views_and_presenters;

import com.example.server.Model.DestinationCard;
import com.example.server.Results.Result;

import java.util.ArrayList;
import java.util.List;

import Network.ServerProxy;
import client_model.ClientModelRoot;
import gui_facade.DrawDestinationTicketsService;

/**
 * Created by HillcollegeMac on 2/15/18.
 */

public class DestinationPickerPresenter implements IDestinationPickerPresenter {

    private ArrayList<String> mSelectedRoutes;
    private ArrayList<String> mDiscardedRoutes;
    private boolean mChange;

    public DestinationPickerPresenter(ArrayList<String> routes) {
        mSelectedRoutes = routes;
    }

    public boolean routeSelected(String selectedRoute) {
        if (mSelectedRoutes.contains(selectedRoute)) {
            mSelectedRoutes.remove(selectedRoute);
            mChange = false;
            if (mSelectedRoutes.size() < 1)
                return false;
            else
                return true;
        }
        else {
            mChange = true;
            mSelectedRoutes.add(selectedRoute);
            return true;
        }
    }
    public Result drawThreeCards(){
        //draws the three cards for the user to pick from
        List<Object> data = new ArrayList<>();
        data.add(ClientModelRoot.instance().getUser());
        data.add(ClientModelRoot.instance().getCurrGame().getGameID());
        return ServerProxy.getInstance()
                .command("DrawDestinationTickets", data, null);


    }
    public void postExecuteDrawCards(){
        DrawDestinationTicketsService.drawCards();
    }


    public ArrayList<String> getSelectedRoutes() {
        return mSelectedRoutes;
    }

    public Result onClickRoutesChosen() {
        // do something with the routes chosen by the player


    }

    public boolean getRouteSelectionChange() {
        return mChange;
    }
}
