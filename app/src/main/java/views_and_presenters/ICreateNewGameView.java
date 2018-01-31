package views_and_presenters;

import java.util.List;

/**
 * Created by HillcollegeMac on 1/27/18.
 */

public interface ICreateNewGameView {

    String getGameName();

    int getMaxNumPlayers();

    String getPlayerColor();

    void setColorListForAvailableColors(List<Boolean> list);

    void setColorListForCheckedColors(List<Boolean> list);

    void enableCreateNewGame(boolean b);
}
