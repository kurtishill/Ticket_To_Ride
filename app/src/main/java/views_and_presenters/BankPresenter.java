package views_and_presenters;

import com.example.server.Model.TicketToRideGame;
import com.example.server.Model.TrainCard;
import com.example.server.Results.Result;

import java.util.ArrayList;
import java.util.List;

import Network.ServerProxy;
import client_model.ClientModelRoot;
import gui_facade.AddUserService;

/**
 * Created by HillcollegeMac on 2/16/18.
 */

/**
 * The BankPresenter presents the data to be displayed to the BankView
 *
 * DOMAIN:
 *      IBankView: Reference to this presenter's view
 *      TrainCardDeck: Deck of train car cards for the game
 *      FaceUpTrainCards: Face up cards
 *      SelectedCards: Train car cards that the user selects during gameplay
 *      DiscardPile: Cards that are discarded during gameplay.
 *          Can be recycled back into the TrainCardDeck when the TrainCardDeck is exhausted.
 *
 * (@invariant IBankView != null)
 * (@invariant TrainCardDeck.size() > 0 if players don't have all the cards)
 * (@invariant FaceUpTrainCards.size() > 0 if players don't have all the cards)
 * (@invariant SelectedCards.size() >= 0 and size() <= 2)
 * (@invariant DiscardPile.size() <= 110 and size() >= 0)
 */
public class BankPresenter implements IBankPresenter {

    /**
     * a reference to the bank presenter's associated view
     */
    private IBankView mBankView;

    /**
     * a list of the train car cards that constitute the deck of the game
     */
    private List<TrainCard> mTrainCardDeck;

    /**
     * a list of the face up train cards
     */
    private List<TrainCard> mFaceUpTrainCards;

    /**
     * a list of the train car cards that the user selects
     */
    private List<TrainCard> mSelectedCards;

    /**
     * a list of all the train car cards discarded during gameplay
     */
    private List<TrainCard> mDiscardPile;

    /**
     * a boolean value to keep track of whether the user can continue drawing train car cards
     */
    private boolean isDone;

    /**
     * Constructs a new BankPresenter object
     * @param v BankView associated with the presenter
     *
     * {@pre none}
     *
     * {@post mBankView points to a IBankView object}
     * {@post mTrainCardDeck reflects the contents of the deck from the client model}
     * {@post mFaceUpTrainCards reflects the contents of the face up cards from the client model}
     * {@post mSelectedCards != null}
     * {@post mDiscardPile reflects the contents of the discard pile form the client model}
     */
    public BankPresenter(IBankView v) {
        mBankView = v;
        mTrainCardDeck = new ArrayList<>();
        mFaceUpTrainCards = new ArrayList<>();
        mSelectedCards = new ArrayList<>();
        mDiscardPile = new ArrayList<>();
        isDone = false;
        initDecks();
    }

    /**
     * Initializes the TrainCardDeck, FaceUpTrainCards, and DiscardPile to
     * hold the contents that the client model has
     *
     * {@pre none}
     *
     * {@post mTrainCardDeck reflects the contents of the deck from the client model}
     * {@post mFaceUpTrainCards reflects the contents of the face up cards from the client model}
     * {@post mDiscardPile reflects the contents of the discard pile form the client model}
     */
    private void initDecks() {
        mTrainCardDeck = ClientModelRoot.instance().getCurrGame().getDeckTrainCards();
        mFaceUpTrainCards = ClientModelRoot.instance().getCurrGame().getFaceUpCards();
        mDiscardPile = ClientModelRoot.instance().getCurrGame().getDiscardPile();
    }

    /**
     * Getter for the BankView
     * @return the BankView
     */
    public IBankView getBankView() {
        return mBankView;
    }

    /**
     * Getter for the TrainCardDeck
     * @return the TrainCardDeck
     */
    public List<TrainCard> getTrainCardDeck() {
        return mTrainCardDeck;
    }

    /**
     * Getter for the FaceUpTrainCards
     * @return the FaceUpTrainCards
     */
    public List<TrainCard> getFaceUpTrainCards() {
        return mFaceUpTrainCards;
    }

    /**
     * Getter for the SelectedCards
     * @return the SelectedCards
     */
    public List<TrainCard> getSelectedCards() {
        return mSelectedCards;
    }

    /**
     * Is the player done selected cards?
     * @return whether the player is done drawing cards or not
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Called when the player selected a card from the FaceUpTrainCards
     * @param index which card was selected
     * @return a new train card to replace the one that was selected
     *
     * {@pre index must be between 0 and 4 inclusively}
     * {@pre FaceUpTrainCards.size() >= 1 and size() <= 5}
     * {@pre SelectedCards.size() <= 1}
     *
     * {@post FaceUpTrainCards will not have the selected card}
     * {@post FaceUpTrainCards will have a new train card from the deck at index}
     */
    public TrainCard faceUpCardSelected(int index) {
        TrainCard selectedCard = mFaceUpTrainCards.get(index);
        if (selectedCard.getColor().equals("wild") && mSelectedCards.size() == 1) {
            mBankView.displayToast("You cannot select a wild card after drawing another card");
            return selectedCard;
        }

        mBankView.disableCloseButton();
        mSelectedCards.add(selectedCard);
        if (selectedCard.getColor().equals("wild"))
            isDone = true;

        if (mSelectedCards.size() == 1 && !selectedCard.getColor().equals("wild"))
            mBankView.displayToast("Please select one more card");
        else if (mSelectedCards.size() == 2) {
            isDone = true;
        }

        TrainCard newFaceUpCard = mTrainCardDeck.get(0);
        mFaceUpTrainCards.set(index, newFaceUpCard);
        mTrainCardDeck.remove(0);
        ClientModelRoot.instance().getCurrGame().setDeckTrainCards(mTrainCardDeck);
        boolean threeWildsInFaceUpDeck = true;
        boolean resetFaceUpDeck = false;
        int check = 0;
        while (threeWildsInFaceUpDeck) {
            for (int i = 0; i < mFaceUpTrainCards.size(); i++) {
                if (mFaceUpTrainCards.get(i).getColor().equals("wild"))
                    check++;
            }
            if (check < 3) {
                threeWildsInFaceUpDeck = false;
            }
            else {
                check = 0;
                resetFaceUpDeck = true;
                mDiscardPile.addAll(mFaceUpTrainCards);
                mFaceUpTrainCards.clear();
                for (int i = 0; i < 5; i++) {
                     mFaceUpTrainCards.add(mTrainCardDeck.get(i));
                }
                for (int i = 0; i < 5; i++) {
                    mTrainCardDeck.remove(i);
                }
            }
        }

        if (resetFaceUpDeck)
            mBankView.resetFaceUpDeck();

        return mFaceUpTrainCards.get(index);
    }

    /**
     * Called when a player selects a card form the TrainCardDeck
     *
     * {@pre TrainCardDeck.size() > 0}
     * {@pre SelectedCards.size() <= 1}
     *
     * {@post TrainCardDeck will no longer have the selected card at the top}
     * {@post TrainCardDeck newSize will be one less than oldSize}
     */
    public void deckCardSelected() {
        mBankView.disableCloseButton();
        TrainCard selectedCard = mTrainCardDeck.get(0);
        mSelectedCards.add(selectedCard);
        mTrainCardDeck.remove(0);

        if (mSelectedCards.size() == 1)
            mBankView.displayToast("Please select one more card");
        else if (mSelectedCards.size() == 2) {
            isDone = true;
        }
    }

    /**
     * Called when the player has selected two cards or one wild from the FaceUpTrainCards
     * @return result of sending information to the server
     *
     * {@pre SelectedCards.size() == 1 or size() == 2}
     *
     * {@post none}
     */
    public Result selectedCards() {
        List<Object> data = new ArrayList<>();
        data.add(mSelectedCards);
        data.add(mFaceUpTrainCards);
        data.add(mTrainCardDeck);
        data.add(mDiscardPile);
        data.add(ClientModelRoot.instance().getCurrGame().getGameID());
        return ServerProxy.getInstance().command("DrawTwoCardsFromBank", data,
                ClientModelRoot.instance().getAuthToken());
    }

    /**
     * Updates the game after sending information to the server and receiving the result
     * @param game to be updated
     *
     * {@pre none}
     *
     * {@post Client Model has new game information}
     */
    public void updateGame(TicketToRideGame game) {
        List<TicketToRideGame> games = ClientModelRoot.instance().getGamesList();
        for (int i = 0; i < games.size(); i++) {
            if (games.get(i).getGameID() == game.getGameID())
                games.set(i, game);
        }

        for (int i = 0; i < game.getPlayers().size(); i++) {
            if (ClientModelRoot.instance().getUser().getUsername().equals(game.getPlayers().get(i).getUsername()))
                AddUserService.addUser(game.getPlayers().get(i));
        }

        ClientModelRoot.instance().setGames(games);
    }
}
