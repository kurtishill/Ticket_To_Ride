package com.example.hillcollegemac.tickettoride;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HillcollegeMac on 1/29/18.
 */

public class CreateNewGamePresenter implements ICreateNewGamePresenter {

    private List<Boolean> playerColors;

    private ICreateNewGameView mCreateNewGameView;

    public CreateNewGamePresenter(ICreateNewGameView v) {
        mCreateNewGameView = v;
    }

    {
        playerColors = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            playerColors.add(true);
        }
    }


    public List<Integer> getNumPlayersList() {
        return null;
    }

    public List<Boolean> getColorList() {
        return null;
    }

    public void colorListChanged() {

    }

    public void cancel() {

    }

    public void confirmCreateGame() {

    }
}
