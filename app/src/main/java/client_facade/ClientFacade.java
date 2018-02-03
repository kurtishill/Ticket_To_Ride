package client_facade;

import com.example.server.Facade.*;
import com.example.server.Results.CreateGameResult;
import com.example.server.Results.GetGameListResult;
import com.example.server.Results.JoinGameResult;
import com.example.server.Results.LoginResult;
import com.example.server.Results.RegisterResult;

import Network.ClientCommunicator;
import Network.ServerProxy;
import client_model.ClientModelRoot;

/**
 * Created by Clayton Kings on 2/2/2018.
 */

public class ClientFacade {
    ClientCommunicator communicator;
    CommandFactory commandFactory;
    public ClientFacade(){
        communicator = new ClientCommunicator();
        commandFactory = new CommandFactory();
    }
    public CreateGameResult CreateGame(){
        return (CreateGameResult) communicator.send(CreateUrl(), commandFactory.CreateGame(ClientModelRoot.instance().getUser().getUsername()));
    } //fixme Im not sure if this will work to convert the types correctly
    public JoinGameResult JoinGame(int gameId){
        return (JoinGameResult) communicator.send(CreateUrl(), commandFactory.JoinGame(ClientModelRoot.instance().getUser().getUsername(), gameId));
    }
    public GetGameListResult GetGameList(){
        return (GetGameListResult) communicator.send(CreateUrl(), commandFactory.GetGameList(ClientModelRoot.instance().getUser().getUsername()));
    }
    public RegisterResult Register(String username, String password) {
        return (RegisterResult) communicator.send(CreateUrl(), commandFactory.Register(username, password));
    }
    public LoginResult Login(String username, String password){
        return (LoginResult) communicator.send(CreateUrl(), commandFactory.Login(username, password));
    }
    private String CreateUrl(){
        return "http://" + ServerProxy.getInstance().getServerHost() + ":"
            + ServerProxy.getInstance().getServerPort() + "/command/"; // fixme where are the server host and port initialized
    }
}
