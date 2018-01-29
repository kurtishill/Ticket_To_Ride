package com.example.hillcollegemac.tickettoride;

import java.util.Observable;

/**
 * Created by HillcollegeMac on 1/27/18.
 */

public interface IGameWaitingLobbyPresenter {

    void joinGame(/*Game object*/);

    void update(Observable obs, Object obj);
}
