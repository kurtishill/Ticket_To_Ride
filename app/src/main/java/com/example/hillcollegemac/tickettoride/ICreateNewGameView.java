package com.example.hillcollegemac.tickettoride;

import java.util.List;

/**
 * Created by HillcollegeMac on 1/27/18.
 */

public interface ICreateNewGameView {

    String getGameName();

    int getMaxNumPlayers();

    String getGameColor();

    List<Boolean> getColorList();

    void setNumPlayersList(List<Integer> list);

    void setColorList(List<Boolean> list);
}
