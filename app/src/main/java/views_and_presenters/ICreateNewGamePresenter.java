package views_and_presenters;

import com.example.server.Model.TicketToRideGame;
import com.example.server.Results.Result;

import java.util.List;

/**
 * Created by HillcollegeMac on 1/27/18.
 */

public interface ICreateNewGamePresenter {

    List<Boolean> getSelectedPlayerColors();

    boolean colorListChanged(int button);

    void cancel();

    Result confirmCreateGame();

    boolean gameNameChanged();

    void addGame(TicketToRideGame game);
}
