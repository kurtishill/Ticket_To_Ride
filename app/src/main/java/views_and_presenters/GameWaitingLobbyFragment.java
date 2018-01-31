package views_and_presenters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.hillcollegemac.tickettoride.R;
import com.example.server.Model.TicketToRideGame;

import java.util.List;

import gui_facade.GetGamesService;

public class GameWaitingLobbyFragment extends Fragment implements IGameWaitingLobbyView {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView mGameListRecyclerView;
    private GameWaitingLobbyAdapter mAdapter;
    private TextView mGameIDTextView,
            mGameNameTextView,
            mPlayerListTextView,
            mPlayersInGameTextView;

    private Button mCreateGameButton, mJoinGameButton;

    private TicketToRideGame mSelectedGame;

    private IGameWaitingLobbyPresenter mGameWaitingLobbyPresenter;

    private String mParam1;
    private String mParam2;

    public GameWaitingLobbyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GameWaitingLobbyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GameWaitingLobbyFragment newInstance(String param1, String param2) {
        GameWaitingLobbyFragment fragment = new GameWaitingLobbyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private class GameWaitingLobbyHolder extends RecyclerView.ViewHolder{
        private TicketToRideGame mGame;

        private GameWaitingLobbyHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_game_waiting_lobby, parent, false));

            mGameIDTextView = (TextView) itemView.findViewById(R.id.game_id_list_item_text_view);
            mGameNameTextView = (TextView) itemView.findViewById(R.id.game_name_list_item_text_view);
            mPlayerListTextView = (TextView) itemView.findViewById(R.id.game_players_list_item_text_view);
            mPlayersInGameTextView = (TextView) itemView.findViewById(R.id.players_in_game_list_item_text_view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSelectedGame = mGameWaitingLobbyPresenter.getAllGamesList().get(getAdapterPosition());
                    turnOffBackgroundColorsOnRecyclerView();
                    itemView.setBackgroundColor(getResources().getColor(R.color.white));
                    enableJoinGame(mGameWaitingLobbyPresenter.gameSelected());
                }
            });
        }

        private void bind(TicketToRideGame game) {
            mGame = game;
            mGameIDTextView.setText(String.valueOf(mGame.getGameID()));
            mGameNameTextView.setText(game.getName());

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mGame.getPlayers().size(); i++) {
                if (mGame.getPlayers().get(i).getUsername() != null)
                    sb.append(mGame.getPlayers().get(i).getUsername());
                if (i + 1 != mGame.getPlayers().size()) {
                    sb.append(", ");
                }
            }
            mPlayerListTextView.setText(sb.toString());
            String playersInGame = mGame.getPlayers().size() + " / " + mGame.getMaxNumPlayers();
            mPlayersInGameTextView.setText(playersInGame);
        }
    }

    private class GameWaitingLobbyAdapter extends RecyclerView.Adapter<GameWaitingLobbyHolder> {
        private List<TicketToRideGame> mGameList;

        public GameWaitingLobbyAdapter(List<TicketToRideGame> games) {
            mGameList = games;
        }

        @Override
        public GameWaitingLobbyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new GameWaitingLobbyHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(final GameWaitingLobbyHolder holder, int position) {
            TicketToRideGame game = mGameList.get(position);
            holder.bind(game);
        }

        @Override
        public int getItemCount() {
            return mGameList.size();
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
        final View v = inflater.inflate(R.layout.fragment_game_waiting_lobby, container, false);

        mGameWaitingLobbyPresenter = new GameWaitingLobbyPresenter(this);

        mGameListRecyclerView = (RecyclerView) v.findViewById(R.id.game_list_recycler_view);
        mGameListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mCreateGameButton = (Button) v.findViewById(R.id.create_game_button);
        mCreateGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateNewGameFragment fragment = new CreateNewGameFragment();
                FragmentManager fm = getChildFragmentManager();
                fm.beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit();
            }
        });

        mJoinGameButton = (Button) v.findViewById(R.id.join_game_button);
        mJoinGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGameWaitingLobbyPresenter.joinGame(mSelectedGame);
                //TODO: start GameActivity
            }
        });

        return v;
    }

    public TicketToRideGame getSelectedGame() {
        return mSelectedGame;
    }

    public void displayGameList() {
        mGameWaitingLobbyPresenter.setAllGamesList(GetGamesService.getGames());
        mAdapter = new GameWaitingLobbyAdapter(mGameWaitingLobbyPresenter.getAllGamesList());
        mGameListRecyclerView.setAdapter(mAdapter);
        mSelectedGame = null;
        enableJoinGame(false);
    }

    public void enableJoinGame(boolean b) {
        mJoinGameButton.setEnabled(b);
    }

    private void turnOffBackgroundColorsOnRecyclerView() {
        for (int i = 0; i < mGameListRecyclerView.getChildCount(); i++) {
            GameWaitingLobbyHolder holder = (GameWaitingLobbyHolder) mGameListRecyclerView.findViewHolderForAdapterPosition(i);
            holder.itemView.setBackgroundColor(getResources().getColor(R.color.transparent_gray));
        }
    }
}
