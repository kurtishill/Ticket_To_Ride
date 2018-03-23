package graph;

import com.example.server.Model.City;
import com.example.server.Model.DestinationCard;
import com.example.server.Model.Player;
import com.example.server.Model.Route;

import java.util.ArrayList;
import java.util.List;

import client_model.ClientModelRoot;

/**
 * Created by Clayton Kings on 3/17/2018.
 */

public class DestinationCardCalc {
    public void reset(List<Route> graph){
        for (int i = 0; i < graph.size(); i++) {
            graph.get(i).Reset();
        }
    }
    public DestCardResult Calc(DestinationCard card){
        List<Player> players = ClientModelRoot.instance().getCurrGame().getPlayers();
        Player currPlayer = null;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getUsername().equals(ClientModelRoot.instance().getUser().getUsername())){
                currPlayer = players.get(i);
                break;
            }
        }
        List<Route> routes = currPlayer.getClaimedRoutes();
        reset(routes);
        boolean oneExists = false;
        boolean twoExists = false;
        City startCity;
        City endCity;
        int start =0;
        int end = 0;
        int points  = 0;
        for(int i = 0; i < routes.size(); i++){
            if (routes.get(i).getCity1().equals(card.getCity1())){
                oneExists = true;
                start = i;
                break;
            }
            if (routes.get(i).getCity2().equals(card.getCity1())){
                oneExists = true;
                start = i;
                break;
            }
            //depending on the routes being one way or  two ways
        }
        for(int i = 0; i < routes.size(); i++){
            if (routes.get(i).getCity2().equals(card.getCity2())){
                twoExists = true;
                end = i;
                break;
            }
            if (routes.get(i).getCity1().equals(card.getCity2())){
                twoExists = true;
                end = i;
                break;
            }
            //depending on the routes being one way or two ways
        }
        if(oneExists && twoExists){
            // start her
            DepthFirstPaths paths = new DepthFirstPaths();
            boolean found = paths.SearchGraph(routes, start, end, card.getCity1(), card.getCity2());
            if (found){
                points = card.getPointValue();
            }
            return new DestCardResult(found, points);


            //return null;
        }
        else{
            return new DestCardResult(false, 0);
        }
    }
}
