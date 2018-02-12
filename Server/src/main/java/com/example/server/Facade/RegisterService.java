package com.example.server.Facade;


import com.example.server.Model.ModelRoot;
import com.example.server.Model.Player;
import com.example.server.Results.RegisterResult;

/**
 * Created by ckingsbu on 1/29/18.
 */

public class RegisterService {

    public static class UserExistsException extends Exception {
        public UserExistsException(){
            System.out.println("Users Exists");
        }
    }
    public RegisterResult Register(String username, String password) throws UserExistsException {
        if(!ModelRoot.instance().UserNameExists(username)){
            Player player = new Player(username, password);
            ModelRoot.instance().allPlayer(player.getID(), player);
            return new RegisterResult(true, null ,null, null, ModelRoot.instance().UserExists(player.getID()) );
        }
        else {
            return new RegisterResult(false, "Username Already Used", null,  "User Exists", null); //throw new UserExistsException(); // might want to just return null instead..
        }
    }
}
