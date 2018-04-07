package client_facade;

import client_model.ClientModelRoot;

/**
 * Created by kurtishill on 4/7/18.
 */

public class ToggleGUIUsability {

    public ToggleGUIUsability(){}

    public static void toggle(boolean isServerDown) {
        ClientModelRoot.instance().setServerDown(isServerDown);
    }
}
