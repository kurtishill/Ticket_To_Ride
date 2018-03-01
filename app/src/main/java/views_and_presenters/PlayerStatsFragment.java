package views_and_presenters;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hillcollegemac.tickettoride.R;
import com.example.server.Model.Player;
import com.example.server.Model.Route;

import java.util.ArrayList;
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

    private Button mCloseButton;

    private RecyclerView mRoutesRecyclerView;
    private RouteAdapter mAdapter;
    private TextView mRouteTextView;

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

    private class RouteHolder extends RecyclerView.ViewHolder{
        private Route mRoute;

        private RouteHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_routes, parent, false));

            mRouteTextView = (TextView) itemView.findViewById(R.id.routes_list_item_text_view);
        }

        private void bind(Route route) {
            mRoute = route;
            mRouteTextView.setText(mRoute.toString());
            mRouteTextView.setTextColor(getResources().getColor(R.color.white));
            mRouteTextView.setBackgroundResource(GameResources.getBackgroundColors().get(mRoute.getOwner().getColor()));
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
        View v = inflater.inflate(R.layout.fragment_player_stats, container, false);

        mPlayerStatsPresenter = new PlayerStatsPresenter(this);

        mPlayerNameTextView = (TextView) v.findViewById(R.id.player_name_stats_text_view);
        mPlayerNameTextView.setText(mPlayerStatsPresenter.getUser().getUsername());

        mPlayerNameColumnLayout = (LinearLayout) v.findViewById(R.id.stats_player_column_layout);
        mPointsColumnLayout = (LinearLayout) v.findViewById(R.id.stats_points_column_layout);
        mTrainsColumnLayout = (LinearLayout) v.findViewById(R.id.stats_trains_column_layout);
        mTrainCardsColumnLayout = (LinearLayout) v.findViewById(R.id.stats_trains_cards_column_layout);
        mDestCardsColumnLayout = (LinearLayout) v.findViewById(R.id.stats_dest_cards_column_layout);

        mRoutesRecyclerView = (RecyclerView) v.findViewById(R.id.routes_recyclerview);
        mRoutesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mCloseButton = (Button) v.findViewById(R.id.stats_close_button);
        mCloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onClose();
                closeFragment();
            }
        });

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

    private void closeFragment() {
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
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
            playerTextView.setTypeface(Typeface.DEFAULT_BOLD);
            mPlayerNameColumnLayout.addView(playerTextView, lp);

            TextView pointsTextView = new TextView(getActivity());
            pointsTextView.setText(String.valueOf(player.getNumPoints()));
            pointsTextView.setTextColor(getResources().getColor(R.color.white));
            mPointsColumnLayout.addView(pointsTextView, lp);

            TextView trainsTextView = new TextView(getActivity());
            trainsTextView.setText(String.valueOf(player.getNumTrainCars()));
            trainsTextView.setTextColor(getResources().getColor(R.color.white));
            mTrainsColumnLayout.addView(trainsTextView,lp);

            TextView trainsCardsTextView = new TextView(getActivity());
            trainsCardsTextView.setText(String.valueOf(player.getTrainCards().size()));
            trainsCardsTextView.setTextColor(getResources().getColor(R.color.white));
            mTrainCardsColumnLayout.addView(trainsCardsTextView, lp);

            TextView destCardsTextView = new TextView(getActivity());
            destCardsTextView.setText(String.valueOf(player.getDestinationCards().size()));
            destCardsTextView.setTextColor(getResources().getColor(R.color.white));
            mDestCardsColumnLayout.addView(destCardsTextView, lp);

            displayRoutes();
        }
    }

    private void displayRoutes() {
        List<Player> players = mPlayerStatsPresenter.getGame().getPlayers();
        List<Route> routes = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            routes.addAll(players.get(i).getClaimedRoutes());
        }
        mAdapter = new RouteAdapter(routes);
        mRoutesRecyclerView.setAdapter(mAdapter);
    }
}
