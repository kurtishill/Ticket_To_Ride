package com.example.server.Facade;

import com.example.server.ChatMessage;
import com.example.server.ClientCommandManager;
import com.example.server.Model.ModelRoot;
import com.example.server.Results.ChatResult;
import com.example.server.Results.GetGameListResult;

import java.util.Map;
import java.util.Set;

/**
 * Created by Clayton Kings on 2/17/2018.
 */

public class ChatService {
    public ChatResult updateChat(ChatMessage message, int gameId){
        try{
            ModelRoot.instance().GameExists(gameId).setChat(message);
            // todo add a command to poll the chats
            return new ChatResult(true, null, null, null,ModelRoot.instance().GameExists(gameId).getChat());
        }
        catch(Exception e){
            return new ChatResult(false, e.getMessage(), null, "Exception",null);
        }
    }
    public ChatResult getChat(int gameId, String username){
        try{
            Map<String, Set<String>> commandMap = ClientCommandManager.instance().getCommands();
            Set<String> commands = commandMap.get(username);
            if (commands != null) {
                if (commands.contains("GetChat")){
                    commandMap.remove("GetChat");
                    ClientCommandManager.instance().setCommands(commandMap);
                    return new ChatResult(true, null, null, null,
                            ModelRoot.instance().GameExists(gameId).getChat());
                }
            }


            return new ChatResult(true, null, null, null,null);
        }
        catch(Exception e){
            return new ChatResult(false, e.getMessage(), null, "Exception",null);
        }
    }
}
