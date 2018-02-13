package views_and_presenters;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hillcollegemac.tickettoride.R;
import com.example.server.Results.CreateGameResult;
import com.example.server.Results.Result;

import java.util.List;

public class CreateNewGameFragment extends Fragment implements ICreateNewGameView, AdapterView.OnItemSelectedListener {

    private static final String ARG_PARAM1 = "title";

    private TextView mCreateGameTitleTextView;
    private EditText mGameNameEditText;
    private Spinner mNumPlayersSpinner;
    private RadioButton mRedRadioButton,
            mBlueRadioButton,
            mYellowRadioButton,
            mGreenRadioButton,
            mBlackRadioButton;
    private Button mCancelButton, mOkButton;

    private int mMaxNumPlayers;

    private ICreateNewGamePresenter mCreateNewGamePresenter;

    private String mParam1;

    public CreateNewGameFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment CreateNewGameFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateNewGameFragment newInstance(String param1) {
        CreateNewGameFragment fragment = new CreateNewGameFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_create_new_game, container, false);

        mCreateNewGamePresenter = new CreateNewGamePresenter(this);

        mMaxNumPlayers = 0;

        mCreateGameTitleTextView = (TextView) v.findViewById(R.id.create_game_title_text_view);
        mGameNameEditText = (EditText) v.findViewById(R.id.game_name_edit_text);
        mGameNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                boolean b = mCreateNewGamePresenter.gameNameChanged() &&
                        mCreateNewGamePresenter.getSelectedPlayerColors().contains(true);

                enableCreateNewGame(b);
            }
        });

        mNumPlayersSpinner = (Spinner) v.findViewById(R.id.num_players_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.num_players,
                R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mNumPlayersSpinner.setAdapter(adapter);
        mNumPlayersSpinner.setOnItemSelectedListener(this);


        mRedRadioButton = (RadioButton) v.findViewById(R.id.red_player_radio_button);
        mRedRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // set request value for color to red
                boolean b = mCreateNewGamePresenter.colorListChanged(0) &&
                        mCreateNewGamePresenter.gameNameChanged();
                enableCreateNewGame(b);
            }
        });

        mBlueRadioButton = (RadioButton) v.findViewById(R.id.blue_player_radio_button);
        mBlueRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // set request value for color to blue
                boolean b = mCreateNewGamePresenter.colorListChanged(1) &&
                        mCreateNewGamePresenter.gameNameChanged();
                enableCreateNewGame(b);
            }
        });

        mYellowRadioButton = (RadioButton) v.findViewById(R.id.yellow_player_radio_button);
        mYellowRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // set request value for color to yellow
                boolean b = mCreateNewGamePresenter.colorListChanged(2) &&
                        mCreateNewGamePresenter.gameNameChanged();
                enableCreateNewGame(b);
            }
        });

        mGreenRadioButton = (RadioButton) v.findViewById(R.id.green_player_radio_button);
        mGreenRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // set request value for color to green
                boolean b = mCreateNewGamePresenter.colorListChanged(3) &&
                        mCreateNewGamePresenter.gameNameChanged();
                enableCreateNewGame(b);
            }
        });

        mBlackRadioButton = (RadioButton) v.findViewById(R.id.black_player_radio_button);
        mBlackRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // set request value for color to black
                boolean b = mCreateNewGamePresenter.colorListChanged(4) &&
                        mCreateNewGamePresenter.gameNameChanged();
                enableCreateNewGame(b);
            }
        });


        mCancelButton = (Button) v.findViewById(R.id.cancel_button);
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCreateNewGamePresenter.cancel();
                closeFragment();
            }
        });

        mOkButton = (Button) v.findViewById(R.id.ok_button);
        mOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CreateGameAsyncTask().execute();
            }
        });

        return v;
    }

    public String getGameName() {
        return mGameNameEditText.getText().toString();
    }

    public int getMaxNumPlayers() {
        return mMaxNumPlayers;
    }

    public String getPlayerColor() {
        if (mRedRadioButton.isChecked())
            return "red";
        else if (mBlueRadioButton.isChecked())
            return "blue";
        else if (mYellowRadioButton.isChecked())
            return "yellow";
        else if (mGreenRadioButton.isChecked())
            return "green";
        else
            return "black";
    }

    public void setColorListForCheckedColors(List<Boolean> list) {
        mRedRadioButton.setChecked(list.get(0));
        mBlueRadioButton.setChecked(list.get(1));
        mYellowRadioButton.setChecked(list.get(2));
        mGreenRadioButton.setChecked(list.get(3));
        mBlackRadioButton.setChecked(list.get(4));
    }

    public void displayToast(String toast) {
        Toast.makeText(getActivity(), toast, Toast.LENGTH_SHORT).show();
    }

    public void enableCreateNewGame(boolean b) {
        mOkButton.setEnabled(b);
    }

    private void closeFragment() {
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        int option = Integer.parseInt(parent.getItemAtPosition(pos).toString());
        mMaxNumPlayers = option;
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg) {
        // left blank intentionally
    }

    private class CreateGameAsyncTask extends AsyncTask<Void, Void, Result> {
        @Override
        protected Result doInBackground(Void... params) {
            return mCreateNewGamePresenter.confirmCreateGame();
        }

        @Override
        protected void onPostExecute(Result result) {
            if (result.isSuccess()) {
                CreateGameResult createGameResult = (CreateGameResult) result;
                mCreateNewGamePresenter.addGame(createGameResult.getGame());
                closeFragment();
                startActivity(new Intent(getActivity(), GameActivity.class));
            }
            else
                displayToast(result.getErrorMessage());
        }
    }
}
