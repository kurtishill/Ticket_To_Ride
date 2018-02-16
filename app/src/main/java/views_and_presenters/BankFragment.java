package views_and_presenters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hillcollegemac.tickettoride.R;

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

        mBankPresenter = new BankPresenter();

        mFaceUpCardOne = (CardView) v.findViewById(R.id.face_up_card_1);
        mFaceUpCardOne.setBackgroundResource(R.drawable.black_card);

        mFaceUpCardTwo = (CardView) v.findViewById(R.id.face_up_card_2);
        mFaceUpCardThree = (CardView) v.findViewById(R.id.face_up_card_3);
        mFaceUpCardFour = (CardView) v.findViewById(R.id.face_up_card_4);
        mFaceUpCardFive = (CardView) v.findViewById(R.id.face_up_card_5);

        mTrainCarCardDeck = (CardView) v.findViewById(R.id.train_car_card_deck);
        mTrainCarCardDeck.setBackgroundResource(R.drawable.train_car_card_deck);

        return v;
    }
}
