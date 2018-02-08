package com.example.server.Facade;

import com.example.server.Results.CreateGameResult;
import com.example.server.Results.GetGameListResult;
import com.example.server.Results.JoinGameResult;
import com.example.server.Results.LoginResult;
import com.example.server.Results.RegisterResult;

/**
 * Created by ckingsbu on 1/29/18.
 */

public class ServerFacade {
    private LoginService login;
    private RegisterService register;
    private GetGameListService getGameList;
    private JoinGameService joinGame;
    private CreateGameService createGame;
    public ServerFacade(){
        login = new LoginService();
        register = new RegisterService();
        getGameList= new GetGameListService();
        joinGame = new JoinGameService();
        createGame = new CreateGameService();
    }
    public CreateGameResult CreateGame(String gameName, Integer maxNumPlayers, String playerColor, String authToken){
        return createGame.CreateGame(gameName, maxNumPlayers, playerColor, authToken);
    }
    public JoinGameResult JoinGame(Integer gameId, String authToken){
        return joinGame.JoinGame(gameId, authToken);
    }
    public GetGameListResult GetGameList(String username){
        return getGameList.GetGameList(username);
    }
    public RegisterResult Register(String username, String password) throws RegisterService.UserExistsException{
        return register.Register(username,password);
    }
    public LoginResult Login(String username, String password){
        return login.Login(username, password);
    }
}
