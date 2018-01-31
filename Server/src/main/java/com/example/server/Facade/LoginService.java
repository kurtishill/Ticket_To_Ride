package com.example.server.Facade;


import com.example.server.Model.ModelRoot;
import com.example.server.Model.Player;
import com.example.server.Results.LoginResult;

/**
 * Created by ckingsbu on 1/29/18.
 */

public class LoginService {
    @SuppressWarnings("serial")
    public static class UserNotExistsException extends Exception {
        public UserNotExistsException(){
            System.out.println("Users Does Not Exist");
        }
    }
    public LoginResult Login(String username, String password) throws UserNotExistsException{
        Player player = ModelRoot.instance().UserExists(username);
        if(player != null){
            if(player.getPassword().equals(password)){
                return new LoginResult(true, null, null, null, username);
            }
            else {
                return new LoginResult(false, "Incorrect Password", null, "Incorrect Password", null);
            }
        }
        else {
            return new LoginResult(false, "No User for that Username", null, "Incorrect Username", null);
        }
    }
}
