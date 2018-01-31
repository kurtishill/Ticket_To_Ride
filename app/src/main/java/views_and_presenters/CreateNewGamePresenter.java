package views_and_presenters;

import com.example.server.Model.TicketToRideGame;

import java.util.ArrayList;
import java.util.List;

import gui_facade.AddGameToGameListService;
import gui_facade.GetAvailablePlayerColorsService;
import gui_facade.GetGamesService;
import gui_facade.GetUserService;

/**
 * Created by HillcollegeMac on 1/29/18.
 */

public class CreateNewGamePresenter implements ICreateNewGamePresenter {

    private List<Boolean> mAvailablePlayerColors;
    private List<Boolean> mCurrAvailablePlayerColors;
    private TicketToRideGame mGame;

    private ICreateNewGameView mCreateNewGameView;

    public CreateNewGamePresenter(ICreateNewGameView v) {
        mCreateNewGameView = v;
        mGame = null;
    }

    {
        mAvailablePlayerColors = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            mAvailablePlayerColors.add(true);
        }
        mCurrAvailablePlayerColors = mAvailablePlayerColors;
    }

    public List<Boolean> getPlayerColors() {
        return mCurrAvailablePlayerColors;
    }

    public TicketToRideGame getGame() {
        return mGame;
    }

    public void setPlayerColors() {
        mAvailablePlayerColors = GetAvailablePlayerColorsService.getPlayerColors();
    }

    public boolean colorListChanged(int button) {
        for (int i = 0; i < mCurrAvailablePlayerColors.size(); i++) {
            if (i == button)
                mCurrAvailablePlayerColors.set(i, true);
            else
                mCurrAvailablePlayerColors.set(i, false);
        }

        mCreateNewGameView.setColorListForCheckedColors(mCurrAvailablePlayerColors);
        return true;
    }

    public void cancel() {
        mGame = null;
    }

    public void confirmCreateGame() {
        // this line of code is commented out because there is no current method to create a user
        // so this line of code causes a NodePointerException when trying to get the user
        //ClientModelRoot.instance().getUser().setColor(mCreateNewGameView.getPlayerColor());
        List<TicketToRideGame> currListOfGames = GetGamesService.getGames();
        int largestID = -1;
        if (currListOfGames.size() > 0)
            largestID = currListOfGames.get(currListOfGames.size() - 1).getGameID();
        mGame = new TicketToRideGame(GetUserService.getUser(),
                mCreateNewGameView.getGameName(),
                largestID + 1,
                mCreateNewGameView.getMaxNumPlayers());
        AddGameToGameListService.addGameToGameList(mGame);
        mGame = null;
    }

    public boolean gameNameChanged() {
        return mCreateNewGameView.getGameName().length() > 0;
    }
}
