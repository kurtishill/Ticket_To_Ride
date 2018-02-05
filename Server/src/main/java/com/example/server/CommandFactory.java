package com.example.server;

import com.example.server.Results.GenericCommand;

/**
 * Created by fryti on 2/1/2018.
 */

/**
 * Class creates specialized command objects for each function in the server facade
 */
public class CommandFactory {
    private static CommandFactory _instance;

    public static CommandFactory instance() {
        if (_instance == null)
            _instance = new CommandFactory();
        return _instance;
    }

    public GenericCommand Login(String username, String password){
        return new GenericCommand("com.example.server.Facade.ServerFacade", "Login",
                new Class<?>[]{ String.class, String.class }, new Object[]{username, password});
    }
    public GenericCommand Register(String username, String password){
        return new GenericCommand("com.example.server.Facade.ServerFacade", "Register",
                new Class<?>[]{ String.class, String.class }, new Object[]{username, password});
    }
    public GenericCommand JoinGame(String username, int gameId){
        return new GenericCommand("com.example.server.Facade.ServerFacade", "JoinGame",
                new Class<?>[]{ String.class, Integer.class }, new Object[]{username, gameId});
    }
    public GenericCommand GetGameList(String username){
        return new GenericCommand("com.example.server.Facade.ServerFacade", "GetGameList",
                new Class<?>[]{String.class}, new Object[]{username});
    }
    public GenericCommand CreateGame(String username){
        return new GenericCommand("com.example.server.Facade.ServerFacade", "CreateGame",
                new Class<?>[]{ String.class}, new Object[]{username});
    }
}
