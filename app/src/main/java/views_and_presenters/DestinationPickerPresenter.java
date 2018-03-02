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
    private int numRoutesSelected;

    public DestinationPickerPresenter(ArrayList<DestinationCard> routes) {
        mSelectedRoutes = routes;
        mSelectedRoutes = new ArrayList();
        mDiscardedRoutes = new ArrayList();
        numRoutesSelected=0;
    }
    public DestinationPickerPresenter() {
        mSelectedRoutes = new ArrayList();
        mSelectedRoutes = new ArrayList();
        mDiscardedRoutes = new ArrayList();
        numRoutesSelected=0;
    }

    public boolean routeSelected(String selectedRoute) {
        for(int i=0; i<mAllRoutes.size(); i++) {
            if (mAllRoutes.get(i).toString().equals(selectedRoute)) {

                if (mSelectedRoutes.contains(mAllRoutes.get(i)))
                    mSelectedRoutes.remove(mAllRoutes.get(i));
                else {
                    mChange = true;
                    mSelectedRoutes.add(mAllRoutes.get(i));

                }
                mChange = true;

                if (mSelectedRoutes.size() < 2)
                    return false;
                else
                    return true;
            }

        }

        return false;
    }
    public Result drawThreeCards(){
        //draws the three cards for the user to pick from
        List<Object> data = new ArrayList<>();
        data.add(ClientModelRoot.instance().getUser().getUsername());
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
    public boolean canChoose(){
        if(numRoutesSelected>1)
            return true;
        return false;

    }
    public boolean isRouteAlreadySelected(String route){
        for(int i=0; i<mSelectedRoutes.size(); i++){
            if(mSelectedRoutes.get(i).toString().equals(route))
                return true;
        }
        return false;
    }
    public void setAllRoutes(List<DestinationCard> allRoutes){ mAllRoutes = allRoutes;}

    public ArrayList<DestinationCard> getSelectedRoutes() {
        return mSelectedRoutes;
    }

    public Result onClickRoutesChosen() {
        // do something with the routes chosen by the player
        determineDiscardedRoutes();
        List<Object> data = new ArrayList<>();
        data.add(ClientModelRoot.instance().getUser().getUsername());
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
    public int getNumRoutesSelected(){return numRoutesSelected;}
    public boolean getRouteSelectionChange() {
        return mChange;
    }
}

