package views_and_presenters;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hillcollegemac.tickettoride.R;
import com.example.server.Model.Route;
import com.example.server.Results.Result;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ClaimRouteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClaimRouteFragment extends Fragment implements IClaimRouteView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private IClaimRoutePresenter mClaimRoutePresenter;

    private RecyclerView mRouteRecyclerView;
    private TextView mRouteNumTextView;
    private TextView mRouteTextView;
    private RouteAdapter mAdapater;
    private TextView mSelectedRouteTextView;
    private Button mCancelButton;
    private Button mChooseButton;

    private LinearLayout mClaimRouteLayout;
    private LinearLayout mSelectedCardLayout;

    private TextView mRedCardNumTextView,
            mBlueCardNumTextView,
            mYellowCardNumTextView,
            mGreenCardNumTextView,
            mBlackCardNumTextView,
            mOrangeCardNumTextView,
            mPurpleCardNumTextView,
            mWhiteCardNumTextView,
            mWildCardNumTextView;

    private RadioButton mRedCardRadioButton,
            mBlueCardRadioButton,
            mYellowCardRadioButton,
            mGreenCardRadioButton,
            mBlackCardRadioButton,
            mOrangeCardRadioButton,
            mPurpleCardRadioButton,
            mWhiteCardRadioButton,
            mWildCardRadioButton;

    private OnCloseFragmentListener mListener;

    public ClaimRouteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ClaimRouteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ClaimRouteFragment newInstance(String param1, String param2) {
        ClaimRouteFragment fragment = new ClaimRouteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private class RouteHolder extends RecyclerView.ViewHolder {
        private Route mRoute;

        private RouteHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_claim_routes, parent, false));

            mRouteNumTextView = (TextView) itemView.findViewById(R.id.claim_route_num_text_view);
            mRouteTextView = (TextView) itemView.findViewById(R.id.routes_list_item_text_view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    toggleChooseButton(true);
                    if (mClaimRoutePresenter.selectRoute(mClaimRoutePresenter
                            .getAllAvailableRoutes().get(getAdapterPosition()), getAdapterPosition() + 1)) {
                        mSelectedCardLayout.setVisibility(View.VISIBLE);
                        toggleChooseButton(mClaimRoutePresenter.anyCardsSelected());
                        setCardsForUser();
                    }
                    else
                        mSelectedCardLayout.setVisibility(View.INVISIBLE);
                }
            });
        }

        private void bind(Route route) {
            mRoute = route;
            String numText = String.valueOf(getAdapterPosition() + 1) + ")";
            mRouteNumTextView.setText(numText);
            String routeString = mRoute.toString() + " - Color: " + mRoute.getColor();
            mRouteTextView.setText(routeString);
            mRouteTextView.setTextColor(getResources().getColor(R.color.white));
        }
    }

    private class RouteAdapter extends RecyclerView.Adapter<RouteHolder> {
        private List<Route> mRouteList;

        public RouteAdapter(List<Route> routes) {
            mRouteList = routes;
        }

        @Override
        public RouteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new RouteHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(final RouteHolder holder, int position) {
            Route route = mRouteList.get(position);
            holder.setIsRecyclable(false);
            holder.bind(route);
        }

        @Override
        public int getItemCount() {
            return mRouteList.size();
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
        View v = inflater.inflate(R.layout.fragment_claim_route, container, false);

        mClaimRoutePresenter = new ClaimRoutePresenter(this);

        mClaimRouteLayout = (LinearLayout) v.findViewById(R.id.claim_route_layout);

        mRouteRecyclerView = (RecyclerView) v.findViewById(R.id.claim_route_recycler_view);
        mRouteRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        displayRoutes();

        mSelectedRouteTextView = (TextView) v.findViewById(R.id.selected_route_text_view);
        mCancelButton = (Button) v.findViewById(R.id.claim_route_cancel_button);
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeFragment();
                mListener.onClose();
            }
        });

        mChooseButton = (Button) v.findViewById(R.id.claim_route_choose_button);
        mChooseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mClaimRoutePresenter.getSelectedRoute().getColor().equals("wild"))
                    mClaimRoutePresenter.changeWildRouteColor();

                if (mClaimRoutePresenter.hasEnoughTrains() && !mClaimRoutePresenter.alreadyOwnsIdenticalRoute()) {
                    new ClaimRouteAsyncTask().execute();
                }
                else {
                    // resets the "wild" route's color back to wild if the player doesn't have the resources to claim it
                    if (mSelectedCardLayout.getVisibility() == View.VISIBLE)
                        mClaimRoutePresenter.getSelectedRoute().setColor("wild");

                    displayToast("Sorry, you do not meet the requirements to claim this route.");
                }
            }
        });

        mSelectedCardLayout = (LinearLayout) v.findViewById(R.id.select_card_for_claim_route_layout);

        mRedCardNumTextView = (TextView) v.findViewById(R.id.red_card_claim_route_text_view);
        mBlueCardNumTextView = (TextView) v.findViewById(R.id.blue_card_claim_route_text_view);
        mYellowCardNumTextView = (TextView) v.findViewById(R.id.yellow_card_claim_route_text_view);
        mGreenCardNumTextView = (TextView) v.findViewById(R.id.green_card_claim_route_text_view);
        mBlackCardNumTextView = (TextView) v.findViewById(R.id.black_card_claim_route_text_view);
        mOrangeCardNumTextView = (TextView) v.findViewById(R.id.orange_card_claim_route_text_view);
        mPurpleCardNumTextView = (TextView) v.findViewById(R.id.purple_card_claim_route_text_view);
        mWhiteCardNumTextView = (TextView) v.findViewById(R.id.white_card_claim_route_text_view);
        mWildCardNumTextView = (TextView) v.findViewById(R.id.wild_card_claim_route_text_view);

        mRedCardRadioButton = (RadioButton) v.findViewById(R.id.claim_route_red_card_radio_button);
        mRedCardRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClaimRoutePresenter.cardsSelected(0);
            }
        });

        mBlueCardRadioButton = (RadioButton) v.findViewById(R.id.claim_route_blue_card_radio_button);
        mBlueCardRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClaimRoutePresenter.cardsSelected(1);
            }
        });

        mYellowCardRadioButton = (RadioButton) v.findViewById(R.id.claim_route_yellow_card_radio_button);
        mYellowCardRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClaimRoutePresenter.cardsSelected(2);
            }
        });

        mGreenCardRadioButton = (RadioButton) v.findViewById(R.id.claim_route_green_card_radio_button);
        mGreenCardRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClaimRoutePresenter.cardsSelected(3);
            }
        });

        mBlackCardRadioButton = (RadioButton) v.findViewById(R.id.claim_route_black_card_radio_button);
        mBlackCardRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClaimRoutePresenter.cardsSelected(4);
            }
        });

        mOrangeCardRadioButton = (RadioButton) v.findViewById(R.id.claim_route_orange_card_radio_button);
        mOrangeCardRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClaimRoutePresenter.cardsSelected(5);
            }
        });

        mPurpleCardRadioButton = (RadioButton) v.findViewById(R.id.claim_route_purple_card_radio_button);
        mPurpleCardRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClaimRoutePresenter.cardsSelected(6);
            }
        });

        mWhiteCardRadioButton = (RadioButton) v.findViewById(R.id.claim_route_white_card_radio_button);
        mWhiteCardRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClaimRoutePresenter.cardsSelected(7);
            }
        });

        mWildCardRadioButton = (RadioButton) v.findViewById(R.id.claim_route_wild_card_radio_button);
        mWildCardRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClaimRoutePresenter.cardsSelected(8);
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
                    + " must implement OnCloseFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void displayToast(String toast) {
        Toast.makeText(getActivity(), toast, Toast.LENGTH_SHORT).show();
    }

    public void setSelectedRouteTextView(String routeNum) {
        mSelectedRouteTextView.setText(routeNum);
    }

    public void toggleChooseButton(boolean toggle) {
        mChooseButton.setEnabled(toggle);
    }

    public void displayRoutes() {
        mAdapater = new RouteAdapter(mClaimRoutePresenter.getAllAvailableRoutes());
        mRouteRecyclerView.setAdapter(mAdapater);
    }

    private void closeFragment() {
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }

    private void setCardsForUser() {
        int redCards = 0;
        int blueCards = 0;
        int yellowCards = 0;
        int greenCards = 0;
        int blackCards = 0;
        int orangeCards = 0;
        int purpleCards = 0;
        int whiteCards = 0;
        int wildCards = 0;

        for (int i = 0; i < mClaimRoutePresenter.getUser().getTrainCards().size(); i++) {
            switch(mClaimRoutePresenter.getUser().getTrainCards().get(i).getColor()) {
                case "red":
                    redCards += 1;
                    break;
                case "blue":
                    blueCards += 1;
                    break;
                case "yellow":
                    yellowCards += 1;
                    break;
                case "green":
                    greenCards += 1;
                    break;
                case "black":
                    blackCards += 1;
                    break;
                case "orange":
                    orangeCards += 1;
                    break;
                case "purple":
                    purpleCards += 1;
                    break;
                case "white":
                    whiteCards += 1;
                    break;
                case "wild":
                    wildCards += 1;
                    break;
                default:
                    break;
            }
        }

        mRedCardNumTextView.setText(String.valueOf(redCards));
        mBlueCardNumTextView.setText(String.valueOf(blueCards));
        mYellowCardNumTextView.setText(String.valueOf(yellowCards));
        mGreenCardNumTextView.setText(String.valueOf(greenCards));
        mBlackCardNumTextView.setText(String.valueOf(blackCards));
        mOrangeCardNumTextView.setText(String.valueOf(orangeCards));
        mPurpleCardNumTextView.setText(String.valueOf(purpleCards));
        mWhiteCardNumTextView.setText(String.valueOf(whiteCards));
        mWildCardNumTextView.setText(String.valueOf(wildCards));
    }

    public void setRadioButtons() {
        mRedCardRadioButton.setChecked(mClaimRoutePresenter.getCardsList().get(0));
        mBlueCardRadioButton.setChecked(mClaimRoutePresenter.getCardsList().get(1));
        mYellowCardRadioButton.setChecked(mClaimRoutePresenter.getCardsList().get(2));
        mGreenCardRadioButton.setChecked(mClaimRoutePresenter.getCardsList().get(3));
        mBlackCardRadioButton.setChecked(mClaimRoutePresenter.getCardsList().get(4));
        mOrangeCardRadioButton.setChecked(mClaimRoutePresenter.getCardsList().get(5));
        mPurpleCardRadioButton.setChecked(mClaimRoutePresenter.getCardsList().get(6));
        mWhiteCardRadioButton.setChecked(mClaimRoutePresenter.getCardsList().get(7));
        mWildCardRadioButton.setChecked(mClaimRoutePresenter.getCardsList().get(8));
    }

    private class ClaimRouteAsyncTask extends AsyncTask<Void, Void, Result> {
        @Override
        protected Result doInBackground(Void... params) {
            return mClaimRoutePresenter.claimRoute();
        }

        @Override
        protected void onPostExecute(Result result) {
            if (result.isSuccess()) {
                // Todo probably do other stuff here
                displayToast("Congratulations! You claimed a route!");
                closeFragment();
                mListener.onClose();
            }
        }
    }
}
