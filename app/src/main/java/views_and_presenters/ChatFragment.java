package views_and_presenters;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.server.ChatMessage;
import com.example.server.Model.TicketToRideGame;

import java.util.List;

/**
 * Created by Clayton Kings on 2/17/2018.
 */

public class ChatFragment extends Fragment implements IChatView {
    private RecyclerView mGameListRecyclerView;
    private Button send;
    public static GameWaitingLobbyFragment newInstance(String param1, String param2) {
        GameWaitingLobbyFragment fragment = new GameWaitingLobbyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private class GameWaitingLobbyHolder extends RecyclerView.ViewHolder{
        private ChatMessage message;

        private GameWaitingLobbyHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_chat, parent, false));

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

    private class GameWaitingLobbyAdapter extends RecyclerView.Adapter<GameWaitingLobbyFragment.GameWaitingLobbyHolder> {
        private List<TicketToRideGame> mGameList;

        public GameWaitingLobbyAdapter(List<TicketToRideGame> games) {
            mGameList = games;
        }

        @Override
        public GameWaitingLobbyFragment.GameWaitingLobbyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new GameWaitingLobbyFragment.GameWaitingLobbyHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(final GameWaitingLobbyFragment.GameWaitingLobbyHolder holder, int position) {
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
                new GameWaitingLobbyFragment.JoinGameAsyncTask().execute();
            }
        });

        mSelectedGameTextView = (TextView) v.findViewById(R.id.game_selected_text_view);

        return v;
    }
    @Override
    public String getMessage() {
        return null;
    }
}
