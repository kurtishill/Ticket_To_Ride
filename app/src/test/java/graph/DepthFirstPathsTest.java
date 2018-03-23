package graph;

import com.example.server.Model.City;
import com.example.server.Model.Route;
import com.example.server.Model.TicketToRideGame;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Clayton Kings on 3/21/2018.
 */
public class DepthFirstPathsTest {
    List<Route> allRoutes;
    @Before
    public void setUp() throws Exception {
        TicketToRideGame testGame= new TicketToRideGame();
        allRoutes = testGame.getAvailableRoutes();
    }

    @Test
    public void searchConnectedGraph() throws Exception {
        DepthFirstPaths search = new DepthFirstPaths();
        List<Route> connected = new ArrayList<>();
        City start = allRoutes.get(1).getCity1();
        City end = allRoutes.get(10).getCity1();
        connected.add(allRoutes.get(1));
        connected.add(allRoutes.get(4));
        connected.add(allRoutes.get(6));
        connected.add(allRoutes.get(8));
        connected.add(allRoutes.get(10));
        boolean found = search.SearchGraph(connected,0, 4,  start, end);
        assertTrue(found);
    }
    @Test
    public void searchUnConnectedGraph() throws Exception {
        DepthFirstPaths search = new DepthFirstPaths();
        List<Route> connected = new ArrayList<>();
        List<Route> unconnected =  new ArrayList<>();
        City start = allRoutes.get(1).getCity1();
        City end = allRoutes.get(10).getCity1();
        unconnected.add(allRoutes.get(1));
        unconnected.add(allRoutes.get(4));
        unconnected.add(allRoutes.get(9));
        unconnected.add(allRoutes.get(8));
        unconnected.add(allRoutes.get(10));
        unconnected.add(allRoutes.get(20));
        boolean notFound = search.SearchGraph(unconnected,0, 4,  start, end);
        assertFalse(notFound);
    }
    @Test
    public void searchCycleGraph() throws Exception {
        DepthFirstPaths search = new DepthFirstPaths();
        List<Route> connected = new ArrayList<>();
        List<Route> unconnected =  new ArrayList<>();
        City start = allRoutes.get(1).getCity1();
        City end = allRoutes.get(10).getCity1();
        connected.add(allRoutes.get(1));
        connected.add(allRoutes.get(4));
        connected.add(allRoutes.get(6));
        connected.add(allRoutes.get(8));
        connected.add(allRoutes.get(10));
        connected.add(allRoutes.get(2));
        connected.add(allRoutes.get(11));
        connected.add(allRoutes.get(13));
        connected.add(allRoutes.get(14));
        connected.add(allRoutes.get(17));
        boolean found = search.SearchGraph(connected,0, 4,  start, end); // with cycles
        assertTrue(found);
    }


}