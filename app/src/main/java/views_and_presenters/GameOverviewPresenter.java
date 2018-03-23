package views_and_presenters;

import com.example.server.Model.DestinationCard;
import com.example.server.Model.Player;
import com.example.server.Model.TicketToRideGame;
import com.example.server.Results.Result;

import java.util.ArrayList;
import java.util.List;

import Network.ServerProxy;
import client_model.ClientModelRoot;
import graph.DestCardResult;
import graph.DestinationCardCalc;
import graph.LongestPathCalc;
import gui_facade.QuitGameService;

/**
 * Created by kurtishill on 3/13/18.
 */

public class GameOverviewPresenter implements IGameOverviewPresenter {

    private IGameOverviewView mGameOverviewView;
    private TicketToRideGame mGame;

    public GameOverviewPresenter(IGameOverviewView v) {
        mGameOverviewView = v;
        mGame = ClientModelRoot.instance().getCurrGame();
    }

    public TicketToRideGame getGame() {
        return mGame;
    }

    public Result quitGame() {
        Integer gameId = mGame.getGameID();
        List<Object> data = new ArrayList<>();
        data.add(gameId);
        Result result = ServerProxy.getInstance()
                .command("DeleteGame", data, ClientModelRoot.instance().getAuthToken());
        return result;
    }

    public void quitGameOnPostExecute() {
        QuitGameService.quitGame();
    }

    public int DestinationCardCalc(){
        List<Player> players = ClientModelRoot.instance().getCurrGame().getPlayers();
        Player currPlayer = null;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getUsername().equals(ClientModelRoot.instance().getUser().getUsername())){
                currPlayer = players.get(i);
                break;
            }
        }
        List<DestinationCard> cards = currPlayer.getDestinationCards();
        int totalPoints = 0;
        DestinationCardCalc cardCalc = new DestinationCardCalc();
        for (int i = 0; i < cards.size(); i++){
            DestCardResult result = cardCalc.Calc(cards.get(i));
            if(result.exists){
                totalPoints += result.pointValue;
            }
            else if(!result.exists){
                totalPoints -= result.pointValue;
            }
        }
        return totalPoints;
    }
    public int LongestPath(){
        LongestPathCalc pathCalc = new LongestPathCalc();
        List<Player> players = ClientModelRoot.instance().getCurrGame().getPlayers();
        int currPlayer = 0;
        for (int i = 0; i < players.size(); i++) {
            players.get(i).setLongestPathLength(pathCalc.LongestPath(players.get(i).getClaimedRoutes()));
            if (players.get(i).getUsername().equals(ClientModelRoot.instance().getUser().getUsername())){
                currPlayer = i;
            }
        }
        int index = 0;
        int largest = 0;
        for (int i = 0; i < players.size(); i++){
            if( largest < players.get(i).getLongestPathLength()){
                index = i;
                largest = players.get(i).getLongestPathLength();
            }
        }
        players.get(index).setHasLongestPath(10);
        return players.get(index).getHasLongestPath();
    }
}
