package graph;

/**
 * Created by Clayton Kings on 3/20/2018.
 */

import com.example.server.Model.City;
import com.example.server.Model.Route;

import java.util.List;


public class DepthFirstPaths {



    public boolean SearchGraph(List<Route> graph, int start, int end, City cStart, City cEnd){
        City temp = cStart;
        boolean connected = false;
        Route tempRoute = graph.get(start);
        tempRoute.Visit();

        if (tempRoute.getCity1().equals(temp)){
            temp = tempRoute.getCity2();
        }
        else{
            temp = tempRoute.getCity1();
        }
        for (int i = 0; i < graph.size(); i++){
            if (!graph.get(i).IsVisited()) {
                if (graph.get(i).getCity1().equals(temp)) {
                    if (graph.get(i).getCity2().equals(cEnd)){
                        return true;
                    }
                    else {
                        return SearchGraph(graph, i, end, graph.get(i).getCity1(), cEnd);
                    }
                } else if (graph.get(i).getCity2().equals(temp)) {
                    if (graph.get(i).getCity1().equals(cEnd)){
                        return true;
                    }
                    else {
                        return SearchGraph(graph, i, end, graph.get(i).getCity2(), cEnd);
                    }
                }
            }
        }

        return false;
    }


    // depth first search from v

}

