package views_and_presenters;

import com.example.server.Model.ChatMessage;
import com.example.server.Model.Player;
import com.example.server.Results.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import Network.ServerProxy;
import client_model.ClientModelRoot;
import gui_facade.EditObserversInModel;
import gui_facade.GetUserService;

/**
 * Created by Clayton Kings on 2/20/2018.
 */

public class ChatPresenter implements IChatPresenter, Observer {
    private  IChatView  mChatView;

    public ChatPresenter(IChatView v) {
        mChatView = v;
        EditObserversInModel.addObserverToModel(this);
    }

    @Override
    public Result sendMessage(String message) {
        List<Object> data = new ArrayList<>();
        data.add(message);
        data.add(ClientModelRoot.instance().getUser().getUsername());
        data.add(ClientModelRoot.instance().getUser().getColor());
        data.add(ClientModelRoot.instance().getCurrGame().getGameID());
        Result result = ServerProxy.getInstance()
                .command("UpdateChat", data, GetUserService.getUser().getID());
        return result;
    }

    @Override
    public void update(Observable obs, Object obj) {
        if (obj.equals(ClientModelRoot.instance().getCurrGame().getChat())) {
            mChatView.displayChat();
        }
    }

    public void setChatList(List<ChatMessage> chatMessages) {
        ClientModelRoot.instance().setChat(chatMessages);
    }

    public Player getUser() {
        return ClientModelRoot.instance().getUser();
    }

    public void close() {
        EditObserversInModel.deleteObserverInModel(this);
    }
}
