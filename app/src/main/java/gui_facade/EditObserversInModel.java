package gui_facade;

import java.util.Observer;

import client_model.ClientModelRoot;

/**
 * Created by HillcollegeMac on 2/7/18.
 */

public class EditObserversInModel {

    public static void addObserverToModel(Observer observer) {
        ClientModelRoot.instance().addObserver(observer);
    }

    public static void deleteObserverInModel(Observer observer) {
        ClientModelRoot.instance().deleteObserver(observer);
    }
}
