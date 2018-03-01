package views_and_presenters;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hillcollegemac.tickettoride.R;
import com.example.server.Model.Player;

import java.util.List;

public class GameActivity extends AppCompatActivity implements IGameView,
        OnCloseFragmentListener {

    public static final String GAME_START_STATUS = "status";

    private String mGameStatus;

    private IGamePresenter mGamePresenter;

    private TextView mWaitingTextView;

    private LinearLayout mPlayerTurnsLayout;

    private Button mDrawCardsButton,
            mPlaceTrainsButton,
            mDrawRoutesButton;

    private DestinationPickerFragment mDestinationPickerFragment;
    private BankFragment mBankFragment;

    // from OnCloseFragmentListener interface
    @Override
    public void onClose() {
        toggleDrawButtons(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle("Ticket To Ride");

        mGamePresenter = new GamePresenter(this);

        mWaitingTextView = (TextView) findViewById(R.id.game_activity_waiting_text_view);

        mPlayerTurnsLayout = (LinearLayout) findViewById(R.id.player_turn_layout);
        displayPlayerTurn();

        mDrawCardsButton = (Button) findViewById(R.id.draw_cards_button);
        mDrawCardsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleDrawButtons(false);
                FragmentManager fm = getSupportFragmentManager();
                mBankFragment = new BankFragment();
                fm.beginTransaction().replace(R.id.bank_fragment_container, mBankFragment)
                        .addToBackStack(null).commit();
            }
        });

        mPlaceTrainsButton = (Button) findViewById(R.id.place_trains_button);
        mPlaceTrainsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //toggleDrawButtons(false);
            }
        });

        mDrawRoutesButton = (Button) findViewById(R.id.draw_routes_button);
        mDrawRoutesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleDrawButtons(false);
//                ArrayList<String> routes = new ArrayList<>();
//                //TODO: add routes that the player can pick here

                FragmentManager fm = getSupportFragmentManager();
                mDestinationPickerFragment = DestinationPickerFragment.newInstance();
                fm.beginTransaction().replace(R.id.destination_picker_fragment_container, mDestinationPickerFragment)
                        .addToBackStack(null).commit();

            }
        });

        if (getIntent() != null ) {
            mGameStatus = getIntent().getStringExtra(GAME_START_STATUS);
            mGamePresenter.didGameStart();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_stats:
                //TODO: start fragment
                return true;
            case R.id.menu_history:
                //TODO: start fragment
                return true;
            case R.id.menu_chat:
                toggleDrawButtons(false);
                ChatFragment mChatFragment = new ChatFragment();
                FragmentManager fm = getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.chat_fragment_container, mChatFragment).addToBackStack(null).commit();
                return true;
            case R.id.menu_turn:
                if (mPlayerTurnsLayout.getVisibility() == View.VISIBLE)
                    mPlayerTurnsLayout.setVisibility(View.INVISIBLE);
                else
                    mPlayerTurnsLayout.setVisibility(View.VISIBLE);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public String getGameStatus() {
        return mGameStatus;
    }

    public void changeTitle(String title) {
        mWaitingTextView.setText(title);
    }

    public void displayToast(final String toast) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(GameActivity.this, toast, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void gameStarted() {
        mWaitingTextView.setBackgroundColor(0);
    }

    public void toggleDrawButtons(boolean toggle) {
        mDrawCardsButton.setEnabled(toggle);
        mPlaceTrainsButton.setEnabled(toggle);
        mDrawRoutesButton.setEnabled(toggle);
    }

    private void displayPlayerTurn() {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        lp.setMargins(10, 10, 10, 0);

        List<Player> players = mGamePresenter.getGame().getPlayers();
        for (int i = 0; i < players.size(); i++) {
            TextView playerView = new TextView(this);
            playerView.setText(players.get(i).getUsername());
            playerView.setBackgroundResource(GameResources.getBackgroundColors().get(players.get(i).getColor()));
            playerView.setTextColor(getResources().getColor(R.color.white));
            playerView.setGravity(Gravity.CENTER);
            mPlayerTurnsLayout.addView(playerView, lp);
        }
    }
}
