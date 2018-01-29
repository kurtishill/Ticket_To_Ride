package com.example.hillcollegemac.tickettoride;

/**
 * Created by HillcollegeMac on 1/27/18.
 */

public interface IGameWaitingLobbyView {

    void displayGameList(/*list of games*/);

    //returns a Game object
    void createNewGame();

    //returns a Game object
    void getSelectedGame();
}
