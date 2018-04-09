package com.example.server.Plugin;

import com.example.server.Model.Player;
import com.example.server.Model.TicketToRideGame;

import java.util.List;

/**
 * Created by claytonkingsbury on 4/8/18.
 */

public interface IGameDao {
    List<TicketToRideGame> read();
    void create(TicketToRideGame game);//todo should we return a database access result or something to see if theres an error?
    void update(TicketToRideGame game);
    void delete(int Id);
}
