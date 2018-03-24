package graph;

import com.example.server.Model.City;
import com.example.server.Model.Route;

import java.util.List;

/**
 * Created by Clayton Kings on 3/21/2018.
 * the only issue I see is when there is a loop and a branch off of that it sometimes counts it as a
 * long line and doesnt just take the longest part. Only occurs ehen the loop begins at the longest path owned
 */

public class LongestPathCalc {
    public boolean NotAllvisited(List<Route> graph){
        for (int i = 0; i < graph.size(); i++){
            if (!graph.get(i).IsVisited()){
                return true;
            }
        }
        return false;
    }
    // could search multiple times and take the largest found.. this will probably be more accurate;

    public int LongestPath(List<Route> graph){
        int tempLargest = 0;
        int largest = 0;
        int start = 0;
        if (graph.size() == 1){
            return graph.get(0).getLength();
        }
        while(NotAllvisited(graph)) {
            tempLargest = 0;
            for (int i = 0; i < graph.size(); i++) {
                if(!graph.get(i).IsVisited()){
                    if(graph.get(i).getLength() > tempLargest){
                        tempLargest = graph.get(i).getLength();
                        start = i;
                    }
                }
            }
            tempLargest = SearchGraph(graph, start, graph.get(start).getCity2())
                    + SearchGraph(graph, start, graph.get(start).getCity1()) - graph.get(start).getLength(); // check if its onl one route long
            if (tempLargest > largest){
                largest = tempLargest;
            }
        }
        return largest;

    }
    public int SearchGraph(List<Route> graph, int start, City cStart){
        if(!NotAllvisited(graph)){
            return 0;
        }
        boolean connected = false;
        Route tempRoute = graph.get(start);
        tempRoute.Visit();
        int tempLength = 0;
        int largest = tempRoute.getLength();
        for (int i = 0; i < graph.size(); i++){
            tempLength = 0;
            if (!graph.get(i).IsVisited()) {
                if (graph.get(i).getCity1().equals(cStart)) {
                    tempLength = tempRoute.getLength() + SearchGraph(graph, i, graph.get(i).getCity2());
                } else if (graph.get(i).getCity2().equals(cStart)) {
                    tempLength = tempRoute.getLength() + SearchGraph(graph, i, graph.get(i).getCity1());
                }
            }
            if(tempLength > largest){
                largest = tempLength;
            }
        }

        return largest;
    }
}
