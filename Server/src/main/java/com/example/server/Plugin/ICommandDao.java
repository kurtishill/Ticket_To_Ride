package com.example.server.Plugin;

import com.example.server.Model.Player;
import com.example.server.Results.GenericCommand;
import com.example.server.Results.ICommand;
import com.example.server.dto.CommandDTO;


import java.util.List;

/**
 * Created by claytonkingsbury on 4/8/18.
 */

public interface ICommandDao {
    List<CommandDTO> read();
    void create(CommandDTO command);//todo should we return a database access result or something to see if theres an error?
    void update(CommandDTO command);
    void delete(int Id);
}
