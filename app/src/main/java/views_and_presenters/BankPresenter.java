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

public class BankPresenter implements IBankPresenter {

    private IBankView mBankView;
    private List<TrainCard> mTrainCardDeck;
    private List<TrainCard> mFaceUpTrainCards;
    private List<TrainCard> mSelectedCards;
    private List<TrainCard> mDiscardPile;
    private boolean isDone;

    public BankPresenter(IBankView v) {
        mBankView = v;
        mTrainCardDeck = new ArrayList<>();
        mFaceUpTrainCards = new ArrayList<>();
        mSelectedCards = new ArrayList<>();
        mDiscardPile = new ArrayList<>();
        isDone = false;
        initDecks();
    }

    private void initDecks() {
        mTrainCardDeck = ClientModelRoot.instance().getCurrGame().getDeckTrainCards();
        mFaceUpTrainCards = ClientModelRoot.instance().getCurrGame().getFaceUpCards();
        mDiscardPile = ClientModelRoot.instance().getCurrGame().getDiscardPile();
    }

    public IBankView getBankView() {
        return mBankView;
    }

    public List<TrainCard> getTrainCardDeck() {
        return mTrainCardDeck;
    }

    public List<TrainCard> getFaceUpTrainCards() {
        return mFaceUpTrainCards;
    }

    public List<TrainCard> getSelectedCards() {
        return mSelectedCards;
    }

    public boolean isDone() {
        return isDone;
    }

    public TrainCard faceUpCardSelected(int index) {
        TrainCard selectedCard = mFaceUpTrainCards.get(index);
        if (selectedCard.getColor().equals("wild") && mSelectedCards.size() == 1) {
            mBankView.displayToast("You cannot select a wild card after drawing another card");
            return selectedCard;
        }

        mSelectedCards.add(selectedCard);
        if (selectedCard.getColor().equals("wild"))
            isDone = true;

        if (mSelectedCards.size() == 1 && !selectedCard.getColor().equals("wild"))
            mBankView.displayToast("Please select one more card");
        else if (mSelectedCards.size() == 2) {
            isDone = true;

            // I think this is temporary. We want a call to the server to this for us.
            /*Player user = ClientModelRoot.instance().getUser();
            user.addTrainCard(mSelectedCards.get(0));
            user.addTrainCard(mSelectedCards.get(1));
            mSelectedCards.clear();
            AddUserService.addUser(user);
            TicketToRideGame game = ClientModelRoot.instance().getCurrGame();
            for (int i = 0; i < game.getPlayers().size(); i++) {
                if (user.getUsername().equals(game.getPlayers().get(i).getUsername())) {
                    game.getPlayers().set(i, user);
                }
            }*/
            //ClientModelRoot.instance().changeTurn();
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

    public void deckCardSelected() {
        TrainCard selectedCard = mTrainCardDeck.get(0);
        mSelectedCards.add(selectedCard);
        mTrainCardDeck.remove(0);

        if (mSelectedCards.size() == 1)
            mBankView.displayToast("Please select one more card");
        else if (mSelectedCards.size() == 2) {
            isDone = true;

            // I think this is temporary. We want a call to the server to this for us.
            /*Player user = ClientModelRoot.instance().getUser();
            user.addTrainCard(mSelectedCards.get(0));
            user.addTrainCard(mSelectedCards.get(1));
            mSelectedCards.clear();
            AddUserService.addUser(user);
            TicketToRideGame game = ClientModelRoot.instance().getCurrGame();
            for (int i = 0; i < game.getPlayers().size(); i++) {
                if (user.getUsername().equals(game.getPlayers().get(i).getUsername())) {
                    game.getPlayers().set(i, user);
                }
            }*/
            //ClientModelRoot.instance().changeTurn();
        }
    }

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
