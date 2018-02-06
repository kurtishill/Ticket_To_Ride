package gui_facade;

import com.example.server.Model.Player;

import client_model.ClientModelRoot;

/**
 * Created by HillcollegeMac on 2/5/18.
 */

public class AddUserService {

    public static void addUser(Player user) {
        ClientModelRoot.instance().setUser(user);
        ClientModelRoot.instance().setAuthToken(user.getID());
    }
}
