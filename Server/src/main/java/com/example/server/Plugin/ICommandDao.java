package com.example.server.Plugin;

import com.example.server.Model.Player;
import com.example.server.Results.GenericCommand;
import com.example.server.Results.ICommand;


import java.util.List;

/**
 * Created by claytonkingsbury on 4/8/18.
 */

public interface ICommandDao {
    List<ICommand> read();
    void create(ICommand command);//todo should we return a database access result or something to see if theres an error?
    void update(ICommand command);
    void delete(int Id);
}
