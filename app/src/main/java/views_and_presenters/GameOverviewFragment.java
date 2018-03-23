package views_and_presenters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hillcollegemac.tickettoride.R;
import com.example.server.Model.Player;
import com.example.server.Results.DeleteGameResult;
import com.example.server.Results.Result;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GameOverviewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameOverviewFragment extends Fragment implements IGameOverviewView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private IGameOverviewPresenter mGameOverviewPresenter;
    private TextView mPlayerNameTextView;
    private Button mQuitGameButton;
    private LinearLayout mPlayerNameColumnLayout,
            mClaimedRoutesPointsLayout,
            mLongestRoutePointsLayout,
            mReachedDestinationPointsLayout,
            mUnreachedDestinationPointsLayout,
            mTotalPointsLayout;

    private OnCloseFragmentListener mListener;

    public GameOverviewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GameOverviewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GameOverviewFragment newInstance(String param1, String param2) {
        GameOverviewFragment fragment = new GameOverviewFragment();
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
        View v = inflater.inflate(R.layout.fragment_game_overview, container, false);

        mGameOverviewPresenter = new GameOverviewPresenter(this);

        mPlayerNameTextView = (TextView) v.findViewById(R.id.player_wins_text_view);

        mQuitGameButton = (Button) v.findViewById(R.id.quit_game_button);
        mQuitGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DeleteGameAsyncTask().execute();
            }
        });

        mPlayerNameColumnLayout = (LinearLayout) v.findViewById(R.id.game_overview_player_column_layout);
        mClaimedRoutesPointsLayout = (LinearLayout) v.findViewById(R.id.game_overview_claimed_route_points_column_layout);
        mLongestRoutePointsLayout = (LinearLayout) v.findViewById(R.id.game_overview_longest_route_points_column_layout);
        mReachedDestinationPointsLayout = (LinearLayout) v.findViewById(R.id.game_overview_reached_destination_points_column_layout);
        mUnreachedDestinationPointsLayout = (LinearLayout) v.findViewById(R.id.game_overview_unreached_destination_points_column_layout);
        mTotalPointsLayout = (LinearLayout) v.findViewById(R.id.game_overview_total_num_points_column_layout);

        displayInformation();

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

    public void displayInformation() {
        List<Player> players = mGameOverviewPresenter.getGame().getPlayers();
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            TextView playerTextView = new TextView(getActivity());
            playerTextView.setText(player.getUsername());
            playerTextView.setTextColor(getResources().getColor(GameResources.getTextColors().get(player.getColor())));
            playerTextView.setTypeface(Typeface.DEFAULT_BOLD);
            mPlayerNameColumnLayout.addView(playerTextView, lp);

            TextView claimedRoutePointsTextView = new TextView(getActivity());
            // todo calc points for claimed routes and replace value in commented section below
            claimedRoutePointsTextView.setText(String.valueOf(player.getNumPoints()));
            claimedRoutePointsTextView.setTextColor(getResources().getColor(R.color.white));
            mClaimedRoutesPointsLayout.addView(claimedRoutePointsTextView, lp);

            TextView longestRoutePointsTextView = new TextView(getActivity());
            // todo calc points for longest route and replace value in commented section below
            //longestRoutePointsTextView.setText(String.valueOf(player.getNumTrainCars()));
            longestRoutePointsTextView.setTextColor(getResources().getColor(R.color.white));
            mLongestRoutePointsLayout.addView(longestRoutePointsTextView, lp);

            TextView reachedDestinationPointsTextView = new TextView(getActivity());
            // todo calc points for reached destinations and replace value in commented section below
            //reachedDestinationPointsTextView.setText(String.valueOf(player.getTrainCards().size()));
            reachedDestinationPointsTextView.setTextColor(getResources().getColor(R.color.white));
            mReachedDestinationPointsLayout.addView(reachedDestinationPointsTextView, lp);

            TextView unreachedDestinationPointsTextView = new TextView(getActivity());
            // todo calc points for unreached destinations and replace value in commented section below
            //unreachedDestinationPointsTextView.setText(String.valueOf(player.getDestinationCards().size()));
            unreachedDestinationPointsTextView.setTextColor(getResources().getColor(R.color.white));
            mUnreachedDestinationPointsLayout.addView(unreachedDestinationPointsTextView, lp);

            TextView totalNumPointsTextView = new TextView(getActivity());
            // todo calc total points and replace value in commented section below
            //totalNumPointsTextView.setText(String.valueOf(player.getNumPoints()));
            totalNumPointsTextView.setTextColor(getResources().getColor(R.color.white));
            mTotalPointsLayout.addView(totalNumPointsTextView, lp);
        }
    }

    private class DeleteGameAsyncTask extends AsyncTask<Void, Void, Result> {
        @Override
        protected Result doInBackground(Void... params) {
            return mGameOverviewPresenter.quitGame();
        }

        @Override
        protected void onPostExecute(Result result) {
            if (result.isSuccess()) {
                mGameOverviewPresenter.quitGameOnPostExecute();
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
            else {
                Toast.makeText(getActivity(), result.getErrorMessage() + " Please try again.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
