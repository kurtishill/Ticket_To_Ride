package com.example.server.Facade;

import com.example.server.ChatMessage;
import com.example.server.Model.DestinationCard;
import com.example.server.Model.Player;
import com.example.server.Results.ChatResult;
import com.example.server.Results.CreateGameResult;
import com.example.server.Results.DrawDestinationTicketsResult;
import com.example.server.Results.GetGameListResult;
import com.example.server.Results.JoinGameResult;
import com.example.server.Results.LoginResult;
import com.example.server.Results.RegisterResult;
import com.example.server.Results.SelectDestinationTicketsResult;

import java.util.List;

/**
 * Created by ckingsbu on 1/29/18.
 */

public class ServerFacade {
    private LoginService login;
    private RegisterService register;
    private GetGameListService getGameList;
    private JoinGameService joinGame;
    private CreateGameService createGame;
    private DrawDestinationTicketsService drawDestinationTickets;
    private SelectDestinationTicketsService selectDestinationTickets;
    private ChatService chatService;
    public ServerFacade(){
        login = new LoginService();
        register = new RegisterService();
        getGameList= new GetGameListService();
        joinGame = new JoinGameService();
        createGame = new CreateGameService();
        drawDestinationTickets = new DrawDestinationTicketsService();
        selectDestinationTickets = new SelectDestinationTicketsService();
        chatService = new ChatService();
    }
    public CreateGameResult CreateGame(String gameName, Integer maxNumPlayers, String playerColor, String authToken){
        return createGame.CreateGame(gameName, maxNumPlayers, playerColor, authToken);
    }
    public JoinGameResult JoinGame(Integer gameId, String authToken){
        return joinGame.JoinGame(gameId, authToken);
    }
    public GetGameListResult GetGameList(String authToken){
        return getGameList.GetGameList(authToken);
    }
    public RegisterResult Register(String username, String password) throws RegisterService.UserExistsException{
        return register.Register(username,password);
    }
    public LoginResult Login(String username, String password){
        return login.Login(username, password);
    }
    public DrawDestinationTicketsResult DrawDestinationTickets(Player player, int gameId){
        return drawDestinationTickets.draw(player, gameId);
    }
    public SelectDestinationTicketsResult SelectDestinationTickets(Player player, int gameId, List<DestinationCard> selectedRoutes, List<DestinationCard> discardedRoutes){
        return selectDestinationTickets.select(player, gameId, selectedRoutes, discardedRoutes);
    }
    public ChatResult UpdateChat(ChatMessage message, int gameId){
        return chatService.updateChat(message, gameId);
    }
    public ChatResult GetChat(int gameId){
        return chatService.getChat(gameId);
    }
}
