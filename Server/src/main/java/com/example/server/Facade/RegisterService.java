package com.example.server.Facade;


import com.example.server.Model.ModelRoot;
import com.example.server.Model.Player;
import com.example.server.Results.RegisterResult;

/**
 * Created by ckingsbu on 1/29/18.
 */

public class RegisterService {
    @SuppressWarnings("serial")
    public static class UserExistsException extends Exception {
        public UserExistsException(){
            System.out.println("Users Exists");
        }
    }
    public RegisterResult Register(String username, String password) throws UserExistsException {
        if(ModelRoot.instance().UserExists(username) ==  null){
            ModelRoot.instance().allPlayer(username, new Player(username, password));
            return new RegisterResult(true, null ,null, null, ModelRoot.instance().UserExists(username) );
        }
        else {
            return new RegisterResult(false, "Username Already Used", null,  "User Exists", null); //throw new UserExistsException(); // might want to just return null instead..
        }
    }
}
