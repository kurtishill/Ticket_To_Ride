package views_and_presenters;

import com.example.server.Model.City;
import com.example.server.Model.Player;
import com.example.server.Model.Route;
import com.example.server.Model.TicketToRideGame;
import com.example.server.Model.TrainCard;
import com.example.server.Results.Result;

import java.util.ArrayList;
import java.util.List;

import Network.ServerProxy;
import client_model.ClientModelRoot;
import gui_facade.AddUserService;
import gui_facade.GetPlayersService;

/**
 * Created by fryti on 3/14/2018.
 */

public class ClaimRoutePresenter implements IClaimRoutePresenter {
    private IClaimRouteView mClaimRouteView;
    private Route mSelectedRoute;
    private List<Route> mAllAvailableRoutes;
    private List<Boolean> mCardsList;
    private Player mUser;

    public ClaimRoutePresenter(IClaimRouteView v) {
        mClaimRouteView = v;
        mAllAvailableRoutes = ClientModelRoot.instance().getCurrGame().getAvailableRoutes();
        mUser = ClientModelRoot.instance().getUser();
    }

    /**
     * Initializes the cards' radio buttons to false when the view is created
     */
    {
        mCardsList = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            mCardsList.add(false);
        }
    }

    public Route getSelectedRoute() {
        return mSelectedRoute;
    }

    public List<Route> getAllAvailableRoutes() {
        return mAllAvailableRoutes;
    }

    public List<Boolean> getCardsList() {
        return mCardsList;
    }

    public Player getUser() {
        return mUser;
    }

    /**
     * Sets the selected route
     * @param selectedRoute which route was selected
     * @param pos the position of the route in the recycler view
     * @return true if the route is a "wild" route
     */
    public boolean selectRoute(Route selectedRoute, int pos) {
        mSelectedRoute = selectedRoute;
        mClaimRouteView.setSelectedRouteTextView("Selected Route: " + pos);
        if (mSelectedRoute.getColor().equals("wild")) {
            mClaimRouteView.displayToast("You selected a gray route! Please choose one color to claim this route.");
            return true;
        }
        return false;
    }
    @Override
    public boolean hasEnoughTrains() {
        int length = mSelectedRoute.getLength();
        String color = mSelectedRoute.getColor();
        int numAppropriatePlayerTrains = 0;
        for(int i=0; i<mUser.getTrainCards().size(); i++){
            //Checks if the train cards the player has are either the correct color or wild
            if(mUser.getTrainCards().get(i).getColor().equals(color) || mUser.getTrainCards().get(i).getColor().equals("wild"))
                numAppropriatePlayerTrains++;
        }

        if(numAppropriatePlayerTrains>=length)
            return true;
        else
            return false;
    }
    //Same player cannot build on both routes of a double route, so this method returns true if
    //a player already owns one of the two, and therefore should not be allowed to claim the second
    public boolean alreadyOwnsIdenticalRoute(){
        City city1 = mSelectedRoute.getCity1();
        City city2 = mSelectedRoute.getCity2();
        for(int i=0; i<mUser.getClaimedRoutes().size(); i++){
            City ownedCity1 = mUser.getClaimedRoutes().get(i).getCity1();
            City ownedCity2 = mUser.getClaimedRoutes().get(i).getCity2();
            if(city1.equals(ownedCity1) && city2.equals(ownedCity2))
                return true;
        }
        return false;
    }

    /**
     * Changes the route's color to the color of cards the player selected to use
     */
    public void changeWildRouteColor() {
        if (mCardsList.get(0))
            mSelectedRoute.setColor("red");
        else if (mCardsList.get(1))
            mSelectedRoute.setColor("blue");
        else if (mCardsList.get(2))
            mSelectedRoute.setColor("yellow");
        else if (mCardsList.get(3))
            mSelectedRoute.setColor("green");
        else if (mCardsList.get(4))
            mSelectedRoute.setColor("black");
        else if (mCardsList.get(5))
            mSelectedRoute.setColor("orange");
        else if (mCardsList.get(6))
            mSelectedRoute.setColor("purple");
        else if (mCardsList.get(7))
            mSelectedRoute.setColor("white");
    }

    public boolean anyCardsSelected() {
        for (int i = 0; i < mCardsList.size(); i++) {
            if (mCardsList.get(i))
                return true;
        }
        return false;
    }

    @Override
    public Result claimRoute() {
        List<Object> data = new ArrayList<>();
        data.add(ClientModelRoot.instance().getUser().getUsername());
        data.add(ClientModelRoot.instance().getCurrGame().getGameID());
        data.add(mSelectedRoute);
        return ServerProxy.getInstance()
                .command("ClaimRoute",data,null);
    }

    public void updateGame(TicketToRideGame game) {
        List<TicketToRideGame> games = ClientModelRoot.instance().getGamesList();
        for (int i = 0; i < games.size(); i++) {
            if (games.get(i).getGameID() == game.getGameID())
                games.set(i, game);
        }

        for (int i = 0; i < game.getPlayers().size(); i++) {
            if (ClientModelRoot.instance().getUser().getUsername().equals(game.getPlayers().get(i).getUsername()))
                AddUserService.addUser(game.getPlayers().get(i));
        }

        ClientModelRoot.instance().setGames(games);
    }

    /**
     * Checks the selected button and unchecks all the others
     * Tells the view which buttons to mark as checked (setRadioButtons)
     * @param button selected button
     */
    public void cardsSelected(int button) {
        for (int i = 0; i < mCardsList.size(); i++) {
            if (mCardsList.get(i))
                mCardsList.set(i, false);
        }

        mCardsList.set(button, true);
        mClaimRouteView.setRadioButtons();
        mClaimRouteView.toggleChooseButton(true);
    }
}
