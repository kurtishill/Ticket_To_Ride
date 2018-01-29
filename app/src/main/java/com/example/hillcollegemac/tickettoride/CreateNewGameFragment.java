package com.example.hillcollegemac.tickettoride;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import java.util.List;

public class CreateNewGameFragment extends Fragment implements ICreateNewGameView {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private EditText mGameNameEditText;
    private Spinner mNumPlayersSpinner;
    private RadioButton mRedRadioButton,
            mBlueRadioButton,
            mYellowRadioButton,
            mGreenRadioButton,
            mBlackRadioButton;
    private Button mCancelButton, mOkButton;

    private ICreateNewGamePresenter mCreateNewGamePresenter;

    private String mParam1;
    private String mParam2;

    public CreateNewGameFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateNewGameFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateNewGameFragment newInstance(String param1, String param2) {
        CreateNewGameFragment fragment = new CreateNewGameFragment();
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
        View v = inflater.inflate(R.layout.fragment_create_new_game, container, false);

        mCreateNewGamePresenter = new CreateNewGamePresenter(this);

        mGameNameEditText = (EditText) v.findViewById(R.id.game_name_edit_text);

        mNumPlayersSpinner = (Spinner) v.findViewById(R.id.num_players_spinner);


        mRedRadioButton = (RadioButton) v.findViewById(R.id.red_player_radio_button);
        mBlueRadioButton = (RadioButton) v.findViewById(R.id.blue_player_radio_button);
        mYellowRadioButton = (RadioButton) v.findViewById(R.id.yellow_player_radio_button);
        mGreenRadioButton = (RadioButton) v.findViewById(R.id.green_player_radio_button);
        mBlackRadioButton = (RadioButton) v.findViewById(R.id.black_player_radio_button);

        //setColorList(mCreateNewGamePresenter.getColorList());


        mCancelButton = (Button) v.findViewById(R.id.cancel_button);
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeFragment();
            }
        });

        mOkButton = (Button) v.findViewById(R.id.ok_button);

        return v;
    }

    public String getGameName() {
        return null;
    }

    public int getMaxNumPlayers() {
        return 0;
    }

    public String getGameColor() {
        return null;
    }

    public List<Boolean> getColorList() {
        return null;
    }

    public void setNumPlayersList(List<Integer> list) {

    }

    public void setColorList(List<Boolean> list) {
        mRedRadioButton.setEnabled(list.get(0));
        mBlueRadioButton.setEnabled(list.get(1));
        mYellowRadioButton.setEnabled(list.get(2));
        mGreenRadioButton.setEnabled(list.get(3));
        mBlackRadioButton.setEnabled(list.get(4));
    }

    private void closeFragment() {
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }
}
