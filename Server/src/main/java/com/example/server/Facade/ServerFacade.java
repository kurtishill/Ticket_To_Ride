package com.example.server.Facade;

import com.example.server.Model.ChatMessage;
import com.example.server.Model.DestinationCard;
import com.example.server.Model.Player;
import com.example.server.Model.Route;
import com.example.server.Model.TrainCard;
import com.example.server.Results.ChatResult;
import com.example.server.Results.ClaimRouteResult;
import com.example.server.Results.CreateGameResult;
import com.example.server.Results.DrawDestinationTicketsResult;
import com.example.server.Results.DrawFromBankResult;
import com.example.server.Results.GetGameListResult;
import com.example.server.Results.JoinGameResult;
import com.example.server.Results.LoginResult;
import com.example.server.Results.RegisterResult;
import com.example.server.Results.SelectDestinationTicketsResult;

import java.util.ArrayList;
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
    private DrawFromBankService drawFromBankService;
    private ClaimRouteService claimRouteService;
    public ServerFacade(){
        login = new LoginService();
        register = new RegisterService();
        getGameList= new GetGameListService();
        joinGame = new JoinGameService();
        createGame = new CreateGameService();
        drawDestinationTickets = new DrawDestinationTicketsService();
        selectDestinationTickets = new SelectDestinationTicketsService();
        chatService = new ChatService();
        drawFromBankService = new DrawFromBankService();
        claimRouteService = new ClaimRouteService();
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

    public DrawDestinationTicketsResult DrawDestinationTickets(String player, Integer gameId){
        return drawDestinationTickets.draw(player, gameId);
    }
    public SelectDestinationTicketsResult SelectDestinationTickets(String playerName, Integer gameId, ArrayList<DestinationCard> selectedRoutes, ArrayList<DestinationCard> discardedRoutes){
        return selectDestinationTickets.select(playerName, gameId, selectedRoutes, discardedRoutes);
    }
    public ChatResult UpdateChat(ChatMessage message, Integer gameId){
        return chatService.updateChat(message, gameId);
    }
    public ChatResult GetChat(Integer gameId, String username){
        return chatService.getChat(gameId, username);
    }
    public DrawFromBankResult DrawFromBank(ArrayList<TrainCard> selectedCards, ArrayList<TrainCard> faceUpCards, ArrayList<TrainCard> trainCardDeck,
                                           ArrayList<TrainCard> discardPile, Integer gameId, String authToken) {
        return drawFromBankService.draw(selectedCards, faceUpCards, trainCardDeck, discardPile, gameId, authToken);
    }
    public ClaimRouteResult ClaimRoute(String playerName, Integer gameID, Route route){
        return claimRouteService.claimRoute(playerName, gameID, route);
    }
}
