package gui_facade;

import client_model.ClientModelRoot;

/**
 * Created by kurtishill on 3/14/18.
 */

public class QuitGameService {

    public static void quitGame() {
        ClientModelRoot.instance().deleteGame();
    }
}
