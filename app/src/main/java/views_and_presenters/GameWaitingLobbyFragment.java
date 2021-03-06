package views_and_presenters;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hillcollegemac.tickettoride.R;
import com.example.server.Model.TicketToRideGame;
import com.example.server.Results.JoinGameResult;
import com.example.server.Results.Result;

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
            mPlayersInGameTextView,
            mSelectedGameTextView;

    private Button mCreateGameButton, mJoinGameButton;

    private TicketToRideGame mSelectedGame;

    private IGameWaitingLobbyPresenter mGameWaitingLobbyPresenter;
    private ICreateNewGameView mCreateNewGameView;

    private TextView mServerDownTextView;
    private RelativeLayout mGameWaitingLobbyLayout;

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
                    String selectedGameText = "Selected Game: " + mSelectedGame.getName();
                    mSelectedGameTextView.setText(selectedGameText);
                    enableJoinGame(mGameWaitingLobbyPresenter.gameSelected());
                    Toast.makeText(getActivity(), "Game \"" + mSelectedGame.getName() + "\" selected", Toast.LENGTH_SHORT).show();
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
            holder.setIsRecyclable(false);
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

        displayGameList();

        mCreateGameButton = (Button) v.findViewById(R.id.create_game_button);
        mCreateGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleViews(false);
                CreateNewGameFragment fragment = new CreateNewGameFragment();
                FragmentManager fm = getChildFragmentManager();
                fm.beginTransaction().replace(R.id.destination_picker_fragment_container, fragment).addToBackStack(null).commit();
            }
        });

        mJoinGameButton = (Button) v.findViewById(R.id.join_game_button);
        mJoinGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new JoinGameAsyncTask().execute();
            }
        });

        mSelectedGameTextView = (TextView) v.findViewById(R.id.game_selected_text_view);

        mServerDownTextView = (TextView) v.findViewById(R.id.server_down_text_view_game_lobby);
        mGameWaitingLobbyLayout = (RelativeLayout) v.findViewById(R.id.game_waiting_lobby_fragment);

        return v;
    }

    public TicketToRideGame getSelectedGame() {
        return mSelectedGame;
    }

    public void displayGameList() {
        mGameWaitingLobbyPresenter.setAllGamesList(GetGamesService.getGamesList());
        mAdapter = new GameWaitingLobbyAdapter(mGameWaitingLobbyPresenter.getAllGamesList());
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mGameListRecyclerView.setAdapter(mAdapter);
            }
        });
    }

    public void enableJoinGame(boolean b) {
        mJoinGameButton.setEnabled(b);
    }

    private class JoinGameAsyncTask extends AsyncTask<Void, Void, Result> {
        @Override
        protected Result doInBackground(Void... params) {
            return mGameWaitingLobbyPresenter.joinGame(mSelectedGame.getGameID());
        }

        @Override
        protected void onPostExecute(Result result) {
            if (result.isSuccess()) {
                JoinGameResult joinGameResult = (JoinGameResult) result;
                Intent intent = new Intent(getActivity(), GameActivity.class);
                if (joinGameResult.getGame() != null) {
                    intent.putExtra(GameActivity.GAME_START_STATUS, "started");
                    mGameWaitingLobbyPresenter.callJoinGameService(joinGameResult.getGame());
                }

                // user is already in the game
                else if (joinGameResult.getGame() == null) {
                    intent.putExtra(GameActivity.GAME_START_STATUS, "resumed");
                    mGameWaitingLobbyPresenter.setCurrGame(mSelectedGame);
                }
                startActivity(intent);
            }
            else
                Toast.makeText(getActivity(), result.getErrorMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void toggleViews(final boolean toggle) {
        mCreateGameButton.setEnabled(toggle);
        if (toggle && mSelectedGame != null)
            mJoinGameButton.setEnabled(true);
        else
            mJoinGameButton.setEnabled(false);
        for (int i = 0; i < mGameListRecyclerView.getChildCount(); i++) {
            mGameListRecyclerView.getChildAt(i).setEnabled(toggle);
        }
    }

    public void toggleGUIUsability(final boolean toggle) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (toggle) {
                    mServerDownTextView.setVisibility(View.VISIBLE);
                }
                else {
                    mServerDownTextView.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
}
