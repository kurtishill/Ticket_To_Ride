package views_and_presenters;

import java.util.List;

/**
 * Created by HillcollegeMac on 1/27/18.
 */

public interface ICreateNewGamePresenter {

    List<Boolean> getSelectedPlayerColors();

    boolean colorListChanged(int button);

    void cancel();

    void confirmCreateGame();

    boolean gameNameChanged();
}
