package views_and_presenters;

import com.example.server.Model.TrainCard;

import java.util.List;

/**
 * Created by HillcollegeMac on 2/16/18.
 */

public interface IBankPresenter {

    IBankView getBankView();

    List<TrainCard> getTrainCardDeck();

    List<TrainCard> getFaceUpTrainCards();

    TrainCard faceUpCardSelected(int index);

    void deckCardSelected();

}
