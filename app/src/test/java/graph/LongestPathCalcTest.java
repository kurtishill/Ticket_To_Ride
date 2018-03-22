package graph;

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
public class LongestPathCalcTest {
    List<Route> allRoutes;
    @Before
    public void setUp() throws Exception {
        TicketToRideGame testGame= new TicketToRideGame();
        allRoutes = testGame.getAvailableRoutes();
    }

    @Test
    public void longestPath() throws Exception {
        List<Route> graph = new ArrayList<>();
        LongestPathCalc calc = new LongestPathCalc();
        graph.add(allRoutes.get(1)); // 3
        graph.add(allRoutes.get(4)); // 5
        graph.add(allRoutes.get(6)); // 6
        graph.add(allRoutes.get(8)); // 1
        graph.add(allRoutes.get(10)); // 1
        graph.add(allRoutes.get(21));// 5
        graph.add(allRoutes.get(28)); // 4
        int longestPath = calc.LongestPath(graph);
        assertEquals(longestPath, 16);
    }
    @Test
    public void longestPathWBranches() throws Exception {
        List<Route> graph = new ArrayList<>();
        LongestPathCalc calc = new LongestPathCalc();
        graph.add(allRoutes.get(1)); // 3 // shorter branch
        graph.add(allRoutes.get(4)); // 5 // shorter branch
        graph.add(allRoutes.get(6)); // 6
        graph.add(allRoutes.get(8)); // 1
        graph.add(allRoutes.get(10)); // 1
        graph.add(allRoutes.get(19)); // 3 longer branch
        graph.add(allRoutes.get(85)); // 5 longer branch
        graph.add(allRoutes.get(82)); // 2 longer branch
        graph.add(allRoutes.get(86)); // 4 longer branch
        graph.add(allRoutes.get(21));// 5
        graph.add(allRoutes.get(28)); // 4
        int longestPath = calc.LongestPath(graph);
        assertEquals(longestPath, 22);
    }

}