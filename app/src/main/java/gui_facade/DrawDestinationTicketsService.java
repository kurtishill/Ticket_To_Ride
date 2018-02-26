package gui_facade;

import client_model.ClientModelRoot;

/**
 * Created by fryti on 2/25/2018.
 */

public class DrawDestinationTicketsService {

    public static void drawCards(){
        for(int i=0; i<3; i++) {
            if (ClientModelRoot.instance().getCurrGame().getDeckDestinationCards().size() != 0)
                ClientModelRoot.instance().getCurrGame().getDeckDestinationCards().remove(0);
        }
    }
}
