package graph;

import com.example.server.Model.DestinationCard;
import com.example.server.Model.Route;

import java.util.ArrayList;
import java.util.List;

import client_model.ClientModelRoot;

/**
 * Created by Clayton Kings on 3/17/2018.
 */

public class DestinationCardCalc {

    public DestCardResult Calc(DestinationCard card){
        List<Route> routes = ClientModelRoot.instance().getUser().getClaimedRoutes();
        boolean oneExists = false;
        boolean twoExists = false;
        int start =0;
        int end = 0;
        for(int i = 0; i < routes.size(); i++){
            if (routes.get(i).getCity1().equals(card.getCity1())){
                oneExists = true;
                start = i;
                break;
            }
            //depending on the routes being one way or two ways
        }
        for(int i = 0; i < routes.size(); i++){
            if (routes.get(i).getCity2().equals(card.getCity2())){
                twoExists = true;
                end = i;
                break;
            }
            //depending on the routes being one way or two ways
        }
        if(oneExists && twoExists){
            // start here


            return null;
        }
        else{
            return new DestCardResult(false, 0);
        }
    }
}
