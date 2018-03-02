package views_and_presenters;

import com.example.server.Model.DestinationCard;
import com.example.server.Results.Result;

import java.util.ArrayList;
import java.util.List;

import Network.ServerProxy;
import client_model.ClientModelRoot;
import gui_facade.DrawDestinationTicketsService;
import gui_facade.SelectDestinationTicketsService;

/**
 * Created by HillcollegeMac on 2/15/18.
 */

public class DestinationPickerPresenter implements IDestinationPickerPresenter {
    private List<DestinationCard> mAllRoutes;
    private ArrayList<DestinationCard> mSelectedRoutes;
    private ArrayList<DestinationCard> mDiscardedRoutes;
    private boolean mChange;

    public DestinationPickerPresenter(ArrayList<DestinationCard> routes) {
        mSelectedRoutes = routes;
        mSelectedRoutes = new ArrayList();
        mDiscardedRoutes = new ArrayList();

    }
    public DestinationPickerPresenter() {
        mSelectedRoutes = new ArrayList();
        mSelectedRoutes = new ArrayList();
        mDiscardedRoutes = new ArrayList();

    }

    public boolean routeSelected(String selectedRoute) {
        for(int i=0; i<mAllRoutes.size(); i++) {
            if (mAllRoutes.get(i).toString().equals(selectedRoute)) {

                if (mSelectedRoutes.contains(mAllRoutes.get(i)))
                    mSelectedRoutes.remove(mAllRoutes.get(i));
                mChange = false;
                if (mSelectedRoutes.size() < 1)
                    return false;
                else
                    return true;
            }
            else {
                mChange = true;
                mSelectedRoutes.add(mAllRoutes.get(i));
                return true;
            }
        }

        return false;
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
        //DrawDestinationTicketsService.drawCards();
    }
    public void postExecuteSelectCards(List<DestinationCard> selectedCards, List<DestinationCard> discardedCards){
        //SelectDestinationTicketsService.selectCards(selectedCards, discardedCards);
    }

    public void setAllRoutes(List<DestinationCard> allRoutes){ mAllRoutes = allRoutes;}

    public ArrayList<DestinationCard> getSelectedRoutes() {
        return mSelectedRoutes;
    }

    public Result onClickRoutesChosen() {
        // do something with the routes chosen by the player
        determineDiscardedRoutes();
        List<Object> data = new ArrayList<>();
        data.add(ClientModelRoot.instance().getUser());
        data.add(ClientModelRoot.instance().getCurrGame().getGameID());
        data.add(mSelectedRoutes);
        data.add(mDiscardedRoutes);
        return ServerProxy.getInstance()
                .command("SelectDestinationTickets",data,null);

    }
    private void determineDiscardedRoutes(){
        for(int i=0; i<mAllRoutes.size(); i++){
            if(! mSelectedRoutes.contains(mAllRoutes.get(i)))
                mDiscardedRoutes.add(mAllRoutes.get(i));
        }
    }

    public boolean getRouteSelectionChange() {
        return mChange;
    }
}
