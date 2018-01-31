package gui_facade;

import com.example.server.Model.Player;

import client_model.ClientModelRoot;

/**
 * Created by HillcollegeMac on 1/30/18.
 */

public class GetUserService {

    public static Player getUser() {
        return ClientModelRoot.instance().getUser();
    }
}
