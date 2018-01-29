package com.example.hillcollegemac.tickettoride;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import jp.wasabeef.blurry.Blurry;

public class GameWaitingLobbyFragment extends Fragment implements IGameWaitingLobbyView {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView mGameListRecyclerView;
    private Button mCreateGameButton, mJoinGameButton;
    private CreateNewGameFragment mCreateNewGameFragment;

    private RelativeLayout mLayout;

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
        final View view = inflater.inflate(R.layout.fragment_game_waiting_lobby, container, false);

        mGameListRecyclerView = (RecyclerView) view.findViewById(R.id.game_list_recycler_view);
        mLayout = (RelativeLayout) view.findViewById(R.id.game_waiting_lobby_fragment);
        mCreateGameButton = (Button) view.findViewById(R.id.create_game_button);
        mCreateGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCreateNewGameFragment = new CreateNewGameFragment();
                FragmentManager fm = getChildFragmentManager();
                fm.beginTransaction().add(R.id.fragment_container, mCreateNewGameFragment).addToBackStack("void").commit();
            }
        });

        mJoinGameButton = (Button) view.findViewById(R.id.join_game_button);

        return view;
    }

    public void displayGameList(/*list of games*/) {

    }

    //returns a Game object
    public void createNewGame() {

    }

    //returns a Game object
    public void getSelectedGame() {

    }
}
