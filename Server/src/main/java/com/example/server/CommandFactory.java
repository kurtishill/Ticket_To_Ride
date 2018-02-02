package com.example.server;

import com.example.server.Facade.ServerFacade;
import com.example.server.Results.GenericCommand;

/**
 * Created by fryti on 2/1/2018.
 */

/**
 * Class creates specialized command objects for each function in the server facade
 */
public class CommandFactory {
    public GenericCommand Login(String username, String password){
        return new GenericCommand("ServerFacade", "Login",
                new Class<?>[]{ String.class, String.class }, new Object[]{username, password});
    }
    public GenericCommand Register(String username, String password){
        return new GenericCommand("ServerFacade", "Register",
                new Class<?>[]{ String.class, String.class }, new Object[]{username, password});
    }
    public GenericCommand JoinGame(String username, int gameId){
        return new GenericCommand("ServerFacade", "JoinGame",
                new Class<?>[]{ String.class, Integer.class }, new Object[]{username, gameId});
    }
    public GenericCommand GetGameList(String username){
        return new GenericCommand("ServerFacade", "GetGameList",
                new Class<?>[]{ String.class}, new Object[]{username});
    }
    public GenericCommand CreateGame(String username){
        return new GenericCommand("ServerFacade", "CreateGame",
                new Class<?>[]{ String.class}, new Object[]{username});
    }
}
