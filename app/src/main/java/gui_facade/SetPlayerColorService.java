package gui_facade;

import client_model.ClientModelRoot;

/**
 * Created by fryti on 2/9/2018.
 */

public class SetPlayerColorService {

    public static void setPlayerColor(String color){
        ClientModelRoot.instance().getUser().setColor(color);
        ClientModelRoot.instance().getCurrGame().getAvailableColors().remove(color);
    }
    public static void setPlayerColor(){
        ClientModelRoot.instance().getUser().setColor(ClientModelRoot.instance().getCurrGame().getAvailableColors().get(0));
        ClientModelRoot.instance().getCurrGame().getAvailableColors().remove(0);
    }
}
