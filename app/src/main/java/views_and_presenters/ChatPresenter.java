package views_and_presenters;

import com.example.server.ChatMessage;
import com.example.server.Results.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import Network.ServerProxy;
import client_model.ClientModelRoot;
import gui_facade.EditObserversInModel;
import gui_facade.GetGamesService;
import gui_facade.GetUserService;

/**
 * Created by Clayton Kings on 2/20/2018.
 */

public class ChatPresenter implements IChatPresenter, Observer {
    public IChatView  mChatView;
    public ChatPresenter(IChatView v) {
        mChatView = v;
        EditObserversInModel.addObserverToModel(this);
    }
    @Override
    public Result sendMessage(String message) {
        ChatMessage messageSend = new ChatMessage(message, ClientModelRoot.instance().getUser().getUsername()
                , ClientModelRoot.instance().getUser().getColor());
        List<Object> data = new ArrayList<>();
        data.add(messageSend);
        data.add(ClientModelRoot.instance().getCurrGame().getGameID());
        Result result = ServerProxy.getInstance()
                .command("UpdateChat", data, GetUserService.getUser().getID());
        if(result.isSuccess()){
//            ClientModelRoot.instance().addMessage(messageSend);
        }
        mChatView.displayChat();
        return result;
    }

    @Override
    public void update(Observable obs, Object o) {
        if (obs == ClientModelRoot.instance()) {
            mChatView.displayChat();
        }
    }
}
