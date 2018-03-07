package views_and_presenters;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.hillcollegemac.tickettoride.R;
import com.example.server.Model.DestinationCard;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link DisplayDestinationCardsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DisplayDestinationCardsFragment extends Fragment implements IDisplayDestinationCardsView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private IDisplayDestinationCardsPresenter mDisplayDestinationCardsPresenter;

    private RecyclerView mRecyclerView;
    private Button mCloseButton;
    private TextView mDestinationCardListItemTextView;
    private DisplayDestinationCardsAdapter mAdapter;

    private OnCloseFragmentListener mListener;

    public DisplayDestinationCardsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DisplayDestinationCardsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DisplayDestinationCardsFragment newInstance(String param1, String param2) {
        DisplayDestinationCardsFragment fragment = new DisplayDestinationCardsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private class DisplayDestinationCardsHolder extends RecyclerView.ViewHolder{
        private DestinationCard mDestinationCard;

        private DisplayDestinationCardsHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_destination_card, parent, false));

            mDestinationCardListItemTextView = (TextView) itemView.findViewById(R.id.destination_card_list_item_text_view);
        }

        private void bind(DestinationCard destinationCard) {
            mDestinationCard = destinationCard;

            mDestinationCardListItemTextView.setText(mDestinationCard.toString());
            mDestinationCardListItemTextView.setTextColor(getResources().getColor(R.color.white));
            mDestinationCardListItemTextView.setBackgroundResource(R.color.trans_brown);
        }
    }

    private class DisplayDestinationCardsAdapter extends RecyclerView.Adapter<DisplayDestinationCardsHolder> {
        private List<DestinationCard> mDestinationCards;

        public DisplayDestinationCardsAdapter(List<DestinationCard> destinationCards) {
            mDestinationCards = destinationCards;
        }

        @Override
        public DisplayDestinationCardsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new DisplayDestinationCardsHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(final DisplayDestinationCardsHolder holder, int position) {
            DestinationCard destinationCard = mDestinationCards.get(position);
            holder.bind(destinationCard);
        }

        @Override
        public int getItemCount() {
            return mDestinationCards.size();
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
        View v = inflater.inflate(R.layout.fragment_display_destination_cards, container, false);

        mDisplayDestinationCardsPresenter = new DisplayDestinationCardsPresenter(this);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.display_destination_cards_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        displayDestinations();

        mCloseButton = (Button) v.findViewById(R.id.display_destination_cards_close_button);
        mCloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onClose();
                closeFragment();
            }
        });

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCloseFragmentListener) {
            mListener = (OnCloseFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void closeFragment() {
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }

    public void displayDestinations() {
        mAdapter = new DisplayDestinationCardsAdapter(mDisplayDestinationCardsPresenter.getDestinationCards());
        mRecyclerView.setAdapter(mAdapter);
    }
}
