package views_and_presenters;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hillcollegemac.tickettoride.R;
import com.example.server.Model.Player;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link PlayerStatsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayerStatsFragment extends Fragment implements IPlayerStatsView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private IPlayerStatsPresenter mPlayerStatsPresenter;

    private TextView mPlayerNameTextView;
    private LinearLayout mPlayerNameColumnLayout,
            mPointsColumnLayout,
            mTrainsColumnLayout,
            mTrainCardsColumnLayout,
            mDestCardsColumnLayout;

    private OnCloseFragmentListener mListener;

    public PlayerStatsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PlayerStatsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PlayerStatsFragment newInstance(String param1, String param2) {
        PlayerStatsFragment fragment = new PlayerStatsFragment();
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
        View v = inflater.inflate(R.layout.fragment_player_stats, container, false);

        mPlayerStatsPresenter = new PlayerStatsPresenter(this);

        mPlayerNameTextView = (TextView) v.findViewById(R.id.player_name_stats_text_view);
        mPlayerNameTextView.setText(mPlayerStatsPresenter.getUser().getUsername());

        mPlayerNameColumnLayout = (LinearLayout) v.findViewById(R.id.stats_player_column_layout);
        mPointsColumnLayout = (LinearLayout) v.findViewById(R.id.stats_points_column_layout);
        mTrainsColumnLayout = (LinearLayout) v.findViewById(R.id.stats_trains_column_layout);
        mTrainCardsColumnLayout = (LinearLayout) v.findViewById(R.id.stats_trains_cards_column_layout);
        mDestCardsColumnLayout = (LinearLayout) v.findViewById(R.id.stats_dest_cards_column_layout);

        displayPlayerStats();

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

    public void displayPlayerStats() {
        List<Player> players = mPlayerStatsPresenter.getGame().getPlayers();
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            TextView playerTextView = new TextView(getActivity());
            playerTextView.setText(player.getUsername());
            playerTextView.setTextColor(getResources().getColor(GameResources.getTextColors().get(player.getColor())));
            mPlayerNameColumnLayout.addView(playerTextView, lp);

            TextView pointsTextView = new TextView(getActivity());
            pointsTextView.setText(player.getNumPoints());
            pointsTextView.setTextColor(getResources().getColor(R.color.white));
            mPointsColumnLayout.addView(pointsTextView, lp);

            TextView trainsTextView = new TextView(getActivity());
            trainsTextView.setText(player.getNumTrainCars());
            trainsTextView.setTextColor(getResources().getColor(R.color.white));
            mTrainsColumnLayout.addView(trainsTextView,lp);

            TextView trainsCardsTextView = new TextView(getActivity());
            /* placeholder */
            trainsCardsTextView.setText("0");
            /*-------------*/
            trainsCardsTextView.setTextColor(getResources().getColor(R.color.white));
            mTrainCardsColumnLayout.addView(trainsCardsTextView, lp);

            TextView destCardsTextView = new TextView(getActivity());
            /* placeholder */
            destCardsTextView.setText("0");
            /*-------------*/
            destCardsTextView.setTextColor(getResources().getColor(R.color.white));
            mDestCardsColumnLayout.addView(destCardsTextView, lp);
        }
    }
}
