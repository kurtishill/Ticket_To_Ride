package views_and_presenters;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.hillcollegemac.tickettoride.R;
import com.example.server.Model.TicketToRideGame;
import com.example.server.Model.TrainCard;
import com.example.server.Results.DrawFromBankResult;
import com.example.server.Results.Result;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link BankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BankFragment extends Fragment implements IBankView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private IBankPresenter mBankPresenter;

    private CardView mFaceUpCardOne,
            mFaceUpCardTwo,
            mFaceUpCardThree,
            mFaceUpCardFour,
            mFaceUpCardFive,
            mTrainCarCardDeck;

    private Button mCloseButton;

    private OnCloseFragmentListener mListener;

    public BankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BankFragment newInstance(String param1, String param2) {
        BankFragment fragment = new BankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mListener = (OnCloseFragmentListener) context;
        } catch (ClassCastException ex) {
            throw new ClassCastException(context.toString() + " must implement OnCloseFragmentListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_bank, container, false);

        mBankPresenter = new BankPresenter(this);

        mFaceUpCardOne = (CardView) v.findViewById(R.id.face_up_card_1);
        mFaceUpCardOne.setBackgroundResource(GameResources.getCardBackground()
                .get(mBankPresenter.getFaceUpTrainCards().get(0).getColor()));
        mFaceUpCardOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TrainCard newCard = mBankPresenter.faceUpCardSelected(0);
                if (newCard.getColor().equals("null")) {
                    mFaceUpCardOne.setBackgroundResource(GameResources.getCardBackground().get("null"));
                }
                else {
                    mFaceUpCardOne.setBackgroundResource(GameResources.getCardBackground()
                            .get(newCard.getColor()));
                }
                if (mBankPresenter.isDone())
                    new selectCardsAsyncTask().execute();
            }
        });

        mFaceUpCardTwo = (CardView) v.findViewById(R.id.face_up_card_2);
        mFaceUpCardTwo.setBackgroundResource(GameResources.getCardBackground()
                .get(mBankPresenter.getFaceUpTrainCards().get(1).getColor()));
        mFaceUpCardTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TrainCard newCard = mBankPresenter.faceUpCardSelected(1);
                if (newCard.getColor().equals("null")) {
                    mFaceUpCardTwo.setBackgroundResource(GameResources.getCardBackground().get("null"));
                }
                else {
                    mFaceUpCardTwo.setBackgroundResource(GameResources.getCardBackground()
                            .get(newCard.getColor()));
                }
                if (mBankPresenter.isDone())
                    new selectCardsAsyncTask().execute();
            }
        });

        mFaceUpCardThree = (CardView) v.findViewById(R.id.face_up_card_3);
        mFaceUpCardThree.setBackgroundResource(GameResources.getCardBackground()
                .get(mBankPresenter.getFaceUpTrainCards().get(2).getColor()));
        mFaceUpCardThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TrainCard newCard = mBankPresenter.faceUpCardSelected(2);
                if (newCard.getColor().equals("null")) {
                    mFaceUpCardThree.setBackgroundResource(GameResources.getCardBackground().get("null"));
                }
                else {
                    mFaceUpCardThree.setBackgroundResource(GameResources.getCardBackground()
                            .get(newCard.getColor()));
                }
                if (mBankPresenter.isDone())
                    new selectCardsAsyncTask().execute();
            }
        });

        mFaceUpCardFour = (CardView) v.findViewById(R.id.face_up_card_4);
        mFaceUpCardFour.setBackgroundResource(GameResources.getCardBackground()
                .get(mBankPresenter.getFaceUpTrainCards().get(3).getColor()));
        mFaceUpCardFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TrainCard newCard = mBankPresenter.faceUpCardSelected(3);
                if (newCard.getColor().equals("null")) {
                    mFaceUpCardFour.setBackgroundResource(GameResources.getCardBackground().get("null"));
                }
                else {
                    mFaceUpCardFour.setBackgroundResource(GameResources.getCardBackground()
                            .get(newCard.getColor()));
                }
                if (mBankPresenter.isDone())
                    new selectCardsAsyncTask().execute();
            }
        });

        mFaceUpCardFive = (CardView) v.findViewById(R.id.face_up_card_5);
        mFaceUpCardFive.setBackgroundResource(GameResources.getCardBackground()
                .get(mBankPresenter.getFaceUpTrainCards().get(4).getColor()));
        mFaceUpCardFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TrainCard newCard = mBankPresenter.faceUpCardSelected(4);
                if (newCard.getColor().equals("null")) {
                    mFaceUpCardFive.setBackgroundResource(GameResources.getCardBackground().get("null"));
                }
                else {
                    mFaceUpCardFive.setBackgroundResource(GameResources.getCardBackground()
                            .get(newCard.getColor()));
                }
                if (mBankPresenter.isDone())
                    new selectCardsAsyncTask().execute();
            }
        });

        mTrainCarCardDeck = (CardView ) v.findViewById(R.id.train_car_card_deck);
        mTrainCarCardDeck.setBackgroundResource(R.drawable.train_car_card_deck);
        mTrainCarCardDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBankPresenter.deckCardSelected();
                if (mBankPresenter.isDone())
                    new selectCardsAsyncTask().execute();
            }
        });

        mCloseButton = (Button) v.findViewById(R.id.bank_close_button);
        mCloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onClose();
                closeFragment();
            }
        });

        return v;
    }

    public void displayToast(final String toast) {
        Toast.makeText(getActivity(), toast, Toast.LENGTH_SHORT).show();
    }

    public void resetFaceUpDeck() {
        mFaceUpCardOne.setBackgroundResource(GameResources.getCardBackground()
                .get(mBankPresenter.getFaceUpTrainCards().get(0).getColor()));
        mFaceUpCardTwo.setBackgroundResource(GameResources.getCardBackground()
                .get(mBankPresenter.getFaceUpTrainCards().get(1).getColor()));
        mFaceUpCardThree.setBackgroundResource(GameResources.getCardBackground()
                .get(mBankPresenter.getFaceUpTrainCards().get(2).getColor()));
        mFaceUpCardFour.setBackgroundResource(GameResources.getCardBackground()
                .get(mBankPresenter.getFaceUpTrainCards().get(3).getColor()));
        mFaceUpCardFive.setBackgroundResource(GameResources.getCardBackground()
                .get(mBankPresenter.getFaceUpTrainCards().get(4).getColor()));
    }

    public void disableCloseButton() {
        mCloseButton.setEnabled(false);
    }

    public void disableCards() {
        mFaceUpCardOne.setEnabled(false);
        mFaceUpCardTwo.setEnabled(false);
        mFaceUpCardThree.setEnabled(false);
        mFaceUpCardFour.setEnabled(false);
        mFaceUpCardFive.setEnabled(false);
    }

    private void closeFragment() {
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }

    private class selectCardsAsyncTask extends AsyncTask<Void, Void, Result> {
        @Override
        protected Result doInBackground(Void... params) {
            return mBankPresenter.selectedCards();
        }

        @Override
        protected void onPostExecute(Result result) {
            DrawFromBankResult drawFromBankResult = (DrawFromBankResult) result;
            TicketToRideGame game = drawFromBankResult.getGame();
            mBankPresenter.updateGame(game);

            mListener.onClose();
            closeFragment();
        }
    }
}
