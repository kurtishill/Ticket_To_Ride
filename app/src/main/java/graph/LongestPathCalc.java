package graph;

import com.example.server.Model.City;
import com.example.server.Model.Route;

import java.util.List;

/**
 * Created by Clayton Kings on 3/21/2018.
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
    public int Search(List<Route> graph, int start, boolean begin){
        City city1 = graph.get(start).getCity1();
        City city2 = graph.get(start).getCity2();
        int length = graph.get(start).getLength();
        int side = 0;
        boolean nothingAdded = true;
        int tempGreatest = 0;
        int index = 0;
        graph.get(start).Visit();
        for (int i = 0; i < graph.size(); i++) {
            if (!graph.get(i).IsVisited()) {
                if (graph.get(i).getCity1().equals(city1)) {
                    if (graph.get(i).getLength() > tempGreatest) {
                        tempGreatest = graph.get(i).getLength();
                        index = i;
                        nothingAdded = false;
                        side = 1;
                    }
                }
                else if (graph.get(i).getCity1().equals(city2)) {
                    if (graph.get(i).getLength() > tempGreatest) {
                        tempGreatest = graph.get(i).getLength();
                        index = i;
                        side = 2;
                        nothingAdded = false;
                    }
                }
                else if (graph.get(i).getCity2().equals(city1)) {
                    if (graph.get(i).getLength() > tempGreatest) {
                        tempGreatest = graph.get(i).getLength();
                        index = i;
                        side = 1;
                        nothingAdded = false;
                    }
                }
                else if (graph.get(i).getCity2().equals(city2)) {
                    if (graph.get(i).getLength() > tempGreatest) {
                        tempGreatest = graph.get(i).getLength();
                        index = i;
                        side = 2;
                        nothingAdded = false;
                    }
                }
            }
        }
        int indexSide = 0;
        if(begin){
            tempGreatest = 0;
            for (int i = 0; i < graph.size(); i++) {
                if (!graph.get(i).IsVisited()) {
                    if (side == 1) {
                        if (graph.get(i).getCity1().equals(city2)) {
                            if (graph.get(i).getLength() > tempGreatest) {
                                tempGreatest = graph.get(i).getLength();
                                indexSide = i;
                                nothingAdded = false;
                            }
                        }
                        else if (graph.get(i).getCity2().equals(city2)) {
                            if (graph.get(i).getLength() > tempGreatest) {
                                tempGreatest = graph.get(i).getLength();
                                indexSide = i;
                                nothingAdded = false;
                            }
                        }
                    }
                    else if(side ==2){
                        if (graph.get(i).getCity1().equals(city1)) {
                            if (graph.get(i).getLength() > tempGreatest) {
                                tempGreatest = graph.get(i).getLength();
                                indexSide = i;
                                nothingAdded = false;
                            }
                        }
                        else if (graph.get(i).getCity2().equals(city1)) {
                            if (graph.get(i).getLength() > tempGreatest) {
                                tempGreatest = graph.get(i).getLength();
                                indexSide = i;
                                nothingAdded = false;
                            }
                        }
                    }
                }
            }
        }
        if (nothingAdded){
            return 0;
        }
        return length + Search(graph, index, false) + Search(graph, indexSide, false);
    }
    public int LongestPath(List<Route> graph){
        int tempLargest = 0;
        int largest = 0;
        int start = 0;
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
            tempLargest = Search(graph, start, true);
            if (tempLargest > largest){
                largest = tempLargest;
            }
        }
        return largest;

    }
}
