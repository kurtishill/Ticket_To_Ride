package com.example.server;

import com.example.server.Model.ChatMessage;
import com.example.server.Model.DestinationCard;
import com.example.server.Model.Player;
import com.example.server.Results.GenericCommand;

import java.util.List;

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
    public GenericCommand JoinGame(int gameId, String authToken){
        return new GenericCommand("com.example.server.Facade.ServerFacade", "JoinGame",
                new Class<?>[]{ Integer.class, String.class }, new Object[]{gameId, authToken});
    }
    public GenericCommand GetGameList(String authToken){
        return new GenericCommand("com.example.server.Facade.ServerFacade", "GetGameList",
                new Class<?>[]{String.class}, new Object[]{authToken});
    }
    public GenericCommand CreateGame(String gameName, Integer maxNumPlayers, String color, String authToken){
        return new GenericCommand("com.example.server.Facade.ServerFacade", "CreateGame",
                new Class<?>[]{String.class, Integer.class, String.class, String.class}, new Object[]{gameName,
                maxNumPlayers, color, authToken});
    }
    public GenericCommand UpdateChat(ChatMessage message, int gameId){
        return new GenericCommand("com.example.server.Facade.ServerFacade", "UpdateChat",
                new Class<?>[]{ChatMessage.class, Integer.class}, new Object[]{message,
                gameId});
    }
    public GenericCommand GetChat(int gameId, String username){
        return new GenericCommand("com.example.server.Facade.ServerFacade", "GetChat",
                new Class<?>[]{Integer.class, String.class}, new Object[]{
                gameId, username});
    }
    public GenericCommand DrawDestinationTicekts(Player player, int gameId){
        return new GenericCommand("com.example.server.Facade.ServerFacade", "DrawDestinationCards",
                new Class<?>[]{Player.class, Integer.class}, new Object[]{player,
                gameId});
    }
    public GenericCommand SelectDestinationTicekts(Player player, int gameId, List<DestinationCard> selectedCards, List<DestinationCard> discardedCards) {
        return new GenericCommand("com.example.server.Facade.ServerFacade", "SelectDestinationCards",
                new Class<?>[]{Player.class, Integer.class, List.class, List.class}, new Object[]{player, gameId, selectedCards, discardedCards});
    }
}
