package views_and_presenters;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hillcollegemac.tickettoride.R;
import com.example.server.Model.DestinationCard;
import com.example.server.Model.TicketToRideGame;
import com.example.server.Results.DrawDestinationTicketsResult;
import com.example.server.Results.LoginResult;
import com.example.server.Results.RegisterResult;
import com.example.server.Results.Result;
import com.example.server.Results.SelectDestinationTicketsResult;

import java.util.ArrayList;
import java.util.List;

import client_model.ClientModelRoot;
import client_model.LastTurnState;
import client_model.StartUpState;
import client_model.State;
import client_model.YourTurnState;

public class DestinationPickerFragment extends Fragment implements IDestinationPickerView {

    private static final String ROUTES = "routes";

    private TextView mRouteOne,
            mRouteTwo,
            mRouteThree,
            mDeckSize;

    private Button mChooseButton;
    private State state;

    private IDestinationPickerPresenter mDestinationPickerPresenter;

    OnCloseFragmentListener mListener;

    public static DestinationPickerFragment newInstance(State state) {
        Bundle args = new Bundle();
        args.putString("state", state.toString());

        DestinationPickerFragment fragment = new DestinationPickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public DestinationPickerFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mListener = (OnCloseFragmentListener) context;
        } catch (ClassCastException ex) {
            throw new ClassCastException(context.toString() + " must implement OnCloseFragmentListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String stringState = savedInstanceState.getString("state");
        switch(stringState)
        {
            case("startup"): this.state = new StartUpState();
            case("yourTurn"): this.state = new YourTurnState();
            case("lastTurn"): this.state = new LastTurnState();
        }

        mDestinationPickerPresenter = new DestinationPickerPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_destination_picker, container, false);

        new DrawDestinationTicketsAsyncTask().execute(); //we want to draw the cards when the view is opened.

        mRouteOne = (TextView) v.findViewById(R.id.destination_one_text_view);
        mRouteOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean select = mDestinationPickerPresenter.routeSelected(mRouteOne.getText().toString(),state);
                if (!select) {
                    mChooseButton.setEnabled(false);
                }
                else
                    mChooseButton.setEnabled(true);

                if (mDestinationPickerPresenter.isRouteAlreadySelected(mRouteOne.getText().toString()))
                    mRouteOne.setBackgroundColor(getResources().getColor(R.color.trans_light_blue));
                else
                    mRouteOne.setBackgroundColor(0);
            }
        });

        mRouteTwo = (TextView) v.findViewById(R.id.destination_two_text_view);
        mRouteTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //select indicates if you can or can't press the select button
                boolean select = mDestinationPickerPresenter.routeSelected(mRouteTwo.getText().toString(), state);
                if (!select) {
                    mChooseButton.setEnabled(false);
                }
                else
                    mChooseButton.setEnabled(true);

                if (mDestinationPickerPresenter.isRouteAlreadySelected(mRouteTwo.getText().toString()))
                    mRouteTwo.setBackgroundColor(getResources().getColor(R.color.trans_light_blue));
                else
                    mRouteTwo.setBackgroundColor(0);
            }
        });

        mRouteThree = (TextView) v.findViewById(R.id.destination_three_text_view);
        mRouteThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean select = mDestinationPickerPresenter.routeSelected(mRouteThree.getText().toString(), state);
                if (!select) {
                    mChooseButton.setEnabled(false);
                }
                else
                    mChooseButton.setEnabled(true);

                if (mDestinationPickerPresenter.isRouteAlreadySelected(mRouteThree.getText().toString()))
                    mRouteThree.setBackgroundColor(getResources().getColor(R.color.trans_light_blue));
                else
                    mRouteThree.setBackgroundColor(0);
            }
        });

        mChooseButton = (Button) v.findViewById(R.id.choose_button);
        mChooseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SelectDestinationTicketsAsyncTask().execute();
                mListener.onClose();
                closeFragment();
            }
        });

        return v;
    }

    private void closeFragment() {
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }

    private class DrawDestinationTicketsAsyncTask extends AsyncTask<Void, Void, Result> {
        @Override
        protected Result doInBackground(Void... v) {
           return mDestinationPickerPresenter.drawThreeCards();
        }

        @Override
        protected void onPostExecute(Result result) {
            if (!result.isSuccess()) {
                displayErrorMessage(result.getErrorMessage());
            }
            else {
                DrawDestinationTicketsResult drawResult = (DrawDestinationTicketsResult) result;
                List<DestinationCard> destinationCards = drawResult.getDestinationCards();
                mDestinationPickerPresenter.setAllRoutes(destinationCards);
                if(destinationCards.size()==3) {
                    mRouteOne.setText(destinationCards.get(0).toString());
                    mRouteTwo.setText(destinationCards.get(1).toString());
                    mRouteThree.setText(destinationCards.get(2).toString());
                }
                else if(destinationCards.size()==2){
                    mRouteOne.setText(destinationCards.get(0).toString());
                    mRouteTwo.setText(destinationCards.get(1).toString());
                    mRouteThree.setText("");
                }
                else if(destinationCards.size()==1){
                    mRouteOne.setText(destinationCards.get(0).toString());
                    mRouteTwo.setText("");
                    mRouteThree.setText("");
                }
                else{
                    closeFragment();
                }
            }
        }
    }
    private class SelectDestinationTicketsAsyncTask extends AsyncTask<Void, Void, Result> {
        @Override
        protected Result doInBackground(Void... v) {
            return mDestinationPickerPresenter.onClickRoutesChosen();
        }

        @Override
        protected void onPostExecute(Result result) {
            if (!result.isSuccess()) {
                displayErrorMessage(result.getErrorMessage());
            }
            else {
                SelectDestinationTicketsResult selectResult = (SelectDestinationTicketsResult)result;
                TicketToRideGame game = selectResult.getGame();
                mDestinationPickerPresenter.updateGame(game);
            }
        }
    }
    public void displayErrorMessage(String s) {
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }
}
