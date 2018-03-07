package views_and_presenters;

import com.example.server.Model.DestinationCard;

import java.util.List;

import client_model.ClientModelRoot;

/**
 * Created by kurtis on 3/2/18.
 */

public class DisplayDestinationCardsPresenter implements IDisplayDestinationCardsPresenter {

    private IDisplayDestinationCardsView mDisplayDestinationCardsView;
    private List<DestinationCard> mDestinationCards;

    public DisplayDestinationCardsPresenter(IDisplayDestinationCardsView v) {
        mDisplayDestinationCardsView = v;
        mDestinationCards = ClientModelRoot.instance().getUser().getDestinationCards();
    }

    public List<DestinationCard> getDestinationCards() {
        return mDestinationCards;
    }
}
