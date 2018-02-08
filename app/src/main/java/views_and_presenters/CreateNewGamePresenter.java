package views_and_presenters;

import com.example.server.Model.TicketToRideGame;
import com.example.server.Results.Result;

import java.util.ArrayList;
import java.util.List;

import Network.ServerProxy;
import gui_facade.AddGameToGameListService;
import gui_facade.GetUserService;
import gui_facade.SetCurrGame;

/**
 * Created by HillcollegeMac on 1/29/18.
 */

public class CreateNewGamePresenter implements ICreateNewGamePresenter {

    private List<Boolean> mSelectedPlayerColors;
    private TicketToRideGame mGame;

    private ICreateNewGameView mCreateNewGameView;

    public CreateNewGamePresenter(ICreateNewGameView v) {
        mCreateNewGameView = v;
        mGame = null;
    }

    {
        mSelectedPlayerColors = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            mSelectedPlayerColors.add(false);
        }
    }

    public List<Boolean> getSelectedPlayerColors() {
        return mSelectedPlayerColors;
    }

    public TicketToRideGame getGame() {
        return mGame;
    }

    public boolean colorListChanged(int button) {
        boolean b = false;
        for (int i = 0; i < mSelectedPlayerColors.size(); i++) {
            if (i == button) {
                mSelectedPlayerColors.set(i, true);
                b = true;
            }
            else
                mSelectedPlayerColors.set(i, false);
        }

        mCreateNewGameView.setColorListForCheckedColors(mSelectedPlayerColors);
        return b;
    }

    public void cancel() {
        mGame = null;
    }

    public Result confirmCreateGame() {
        String gameName = mCreateNewGameView.getGameName();
        Integer maxNumPlayers = mCreateNewGameView.getMaxNumPlayers();
        String playerColor = mCreateNewGameView.getPlayerColor();
        List<Object> data = new ArrayList<>();
        data.add(gameName);
        data.add(maxNumPlayers);
        data.add(playerColor);
        Result result = ServerProxy.getInstance("192.168.1.216", "8080")
                .command("CreateGame", data, GetUserService.getUser().getID());
        return result;
    }

    public void addGame(TicketToRideGame game) {
        AddGameToGameListService.addGameToGameList(game);
        SetCurrGame.setCurrGame(game);
    }

    public boolean gameNameChanged() {
        if (mCreateNewGameView.getGameName().length() > 10)
            mCreateNewGameView.displayToast("Name can't be longer than 10 characters");
        return mCreateNewGameView.getGameName().length() > 0 && mCreateNewGameView.getGameName().length() <= 10;
    }
}
