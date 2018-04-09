package com.example.server.Plugin;

import com.example.server.Model.Player;

import java.util.List;

/**
 * Created by claytonkingsbury on 4/8/18.
 */

public interface IUserDao {
    List<Player> read();
    void create(Player user);//todo should we return a database access result or something to see if theres an error?
    void update(Player user);
    void delete(int Id);
}
