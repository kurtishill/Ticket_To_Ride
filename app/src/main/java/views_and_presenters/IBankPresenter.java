package views_and_presenters;

import com.example.server.Model.TicketToRideGame;
import com.example.server.Model.TrainCard;
import com.example.server.Results.Result;

import java.util.List;

/**
 * Created by HillcollegeMac on 2/16/18.
 */

public interface IBankPresenter {

    IBankView getBankView();

    List<TrainCard> getTrainCardDeck();

    List<TrainCard> getFaceUpTrainCards();

    List<TrainCard> getSelectedCards();

    TrainCard faceUpCardSelected(int index);

    void deckCardSelected();

    Result selectedCards();

    void updateGame(TicketToRideGame game);

    boolean isDone();

}
