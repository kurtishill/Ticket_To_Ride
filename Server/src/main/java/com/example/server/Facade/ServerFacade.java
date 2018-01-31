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
    LoginService login;
    RegisterService register;
    GetGameListService getGameList;
    JoinGameService joinGame;
    CreateGameService createGame;
    ServerFacade(){
        login = new LoginService();
        register = new RegisterService();
        getGameList= new GetGameListService();
        joinGame = new JoinGameService();
        createGame = new CreateGameService();
    }
    public CreateGameResult CreateGame(String username){
        return createGame.CreateGame(username);
    }
    public JoinGameResult JoinGame(String username, int gameId){
        return joinGame.JoinGame(username, gameId);
    }
    public GetGameListResult GetGameList(String username){
        return getGameList.GetGameList(username);
    }
    public RegisterResult Register(String username, String password) throws RegisterService.UserExistsException{
        return register.Register(username,password);
    }
    public LoginResult Login(String username, String password) throws LoginService.UserNotExistsException{
        return login.Login(username, password);
    }
}
