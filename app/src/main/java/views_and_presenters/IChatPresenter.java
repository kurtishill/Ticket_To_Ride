package views_and_presenters;

import com.example.server.Model.ChatMessage;
import com.example.server.Model.Player;
import com.example.server.Results.Result;

import java.util.List;

/**
 * Created by Clayton Kings on 2/17/2018.
 */

public interface IChatPresenter {

    Result sendMessage(String message);

    void setChatList(List<ChatMessage> chatMessages);

    Player getUser();

    void close();
}
