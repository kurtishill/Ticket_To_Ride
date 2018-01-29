package com.example.hillcollegemac.tickettoride;

import java.util.List;

/**
 * Created by HillcollegeMac on 1/27/18.
 */

public interface ICreateNewGamePresenter {

    List<Integer> getNumPlayersList();

    List<Boolean> getColorList();

    void colorListChanged();

    void cancel();

    void confirmCreateGame();
}
