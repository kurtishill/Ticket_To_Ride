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
import com.example.server.Model.GameHistory;
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

    private MenuItem mPlayerTurnMenuItem,
            mChatMenuItem,
            mGameHistoryMenuItem,
            mPlayerStatsMenuItem,
            mDestinationsMenuItem;

    private DestinationPickerFragment mDestinationPickerFragment;
    private BankFragment mBankFragment;
    private PlayerStatsFragment mPlayerStatsFragment;
    private GameHistoryFragment mGameHistoryFragment;
    private ChatFragment mChatFragment;
    private DisplayDestinationCardsFragment mDisplayDestinationCardsFragment;

    // from OnCloseFragmentListener interface
    @Override
    public void onClose() {
        toggleButtons(true);
        toggleMenu(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu, menu);

        mPlayerTurnMenuItem = (MenuItem) menu.findItem(R.id.menu_turn);
        mChatMenuItem = (MenuItem) menu.findItem(R.id.menu_chat);
        mGameHistoryMenuItem = (MenuItem) menu.findItem(R.id.menu_history);
        mPlayerStatsMenuItem = (MenuItem) menu.findItem(R.id.menu_stats);
        mDestinationsMenuItem = (MenuItem) menu.findItem(R.id.menu_destinations);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FragmentManager fm = getSupportFragmentManager();
        switch(item.getItemId()) {
            case R.id.menu_stats:
                toggleButtons(false);
                toggleMenu(false);
                mPlayerStatsFragment = new PlayerStatsFragment();
                fm.beginTransaction().replace(R.id.stats_fragment_container, mPlayerStatsFragment)
                        .addToBackStack(null).commit();
                return true;
            case R.id.menu_history:
                toggleButtons(false);
                toggleMenu(false);
                mGameHistoryFragment = new GameHistoryFragment();
                fm.beginTransaction().replace(R.id.game_history_fragment_container, mGameHistoryFragment)
                        .addToBackStack(null).commit();
                return true;
            case R.id.menu_chat:
                toggleButtons(false);
                toggleMenu(false);
                mChatFragment = new ChatFragment();
                fm.beginTransaction().replace(R.id.chat_fragment_container, mChatFragment).addToBackStack(null).commit();
                return true;
            case R.id.menu_turn:
                if (mPlayerTurnsLayout.getVisibility() == View.VISIBLE)
                    mPlayerTurnsLayout.setVisibility(View.INVISIBLE);
                else
                    mPlayerTurnsLayout.setVisibility(View.VISIBLE);
                return true;
            case R.id.menu_destinations:
                toggleButtons(false);
                toggleMenu(false);
                mDisplayDestinationCardsFragment = new DisplayDestinationCardsFragment();
                fm.beginTransaction().replace(R.id.display_destination_cards_fragment_container, mDisplayDestinationCardsFragment)
                        .addToBackStack(null).commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
                toggleButtons(false);
                toggleMenu(false);
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
                //toggleButtons(false);

            }
        });

        mDrawRoutesButton = (Button) findViewById(R.id.draw_routes_button);
        mDrawRoutesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleButtons(false);
                toggleMenu(false);
                FragmentManager fm = getSupportFragmentManager();
                mDestinationPickerFragment = DestinationPickerFragment.newInstance();
                fm.beginTransaction().replace(R.id.destination_picker_fragment_container, mDestinationPickerFragment)
                        .addToBackStack(null).commit();

            }
        });

        if (getIntent() != null ) {
            mGameStatus = getIntent().getStringExtra(GAME_START_STATUS);
            boolean gameStarted = mGamePresenter.didGameStart();
            if (mGamePresenter.getUser().getState().equals("startup") && gameStarted)
                onStartUp();
            if (gameStarted)
                toggleButtons(true);
        }
    }

    public String getGameStatus() {
        return mGameStatus;
    }

    public void changeTitle(String title) {
        mWaitingTextView.setText(title);
    }

    public void displayToast(final String toast) {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
    }

    public void gameStarted(final String toast) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mWaitingTextView.setText("");
                Toast.makeText(GameActivity.this, toast, Toast.LENGTH_SHORT).show();
                mWaitingTextView.setBackgroundColor(0);
                toggleButtons(true);
            }
        });
    }

    public void onStartUp()
    {
        toggleButtons(false);
        FragmentManager fm = getSupportFragmentManager();
        mDestinationPickerFragment = DestinationPickerFragment.newInstance();
        fm.beginTransaction().replace(R.id.destination_picker_fragment_container, mDestinationPickerFragment)
                .addToBackStack(null).commit();
    }

    public void toggleButtons(boolean toggle) {
        // intercept and check to see if it's the users turn or not
        // and to check if the player is in the start up state
        if (toggle) {
            toggle = mGamePresenter.isItUsersTurn();
            if (mGamePresenter.getUser().getState().equals("startup"))
                toggle = false;
        }
        mDrawCardsButton.setEnabled(toggle);
        mPlaceTrainsButton.setEnabled(toggle);
        mDrawRoutesButton.setEnabled(toggle);
    }

    private void toggleMenu(boolean toggle) {
        mPlayerStatsMenuItem.setEnabled(toggle);
        mGameHistoryMenuItem.setEnabled(toggle);
        mChatMenuItem.setEnabled(toggle);
        mDestinationsMenuItem.setEnabled(toggle);
    }

    public void displayPlayerTurn() {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                lp.setMargins(10, 0, 10, 10);

                List<Player> players = mGamePresenter.getGame().getPlayers();

                // if there are player textviews already in the layout
                if (mPlayerTurnsLayout.getChildCount() - 2 > 0) {
                    for (int i = mPlayerTurnsLayout.getChildCount() - 1; i > 1; i--) {
                        mPlayerTurnsLayout.removeViewAt(i);
                    }
                }

                // there are already two children in mPlayerTurnsLayout
                for (int i = mPlayerTurnsLayout.getChildCount() - 2; i < players.size(); i++) {
                    TextView playerView = new TextView(GameActivity.this);
                    playerView.setText(players.get(i).getUsername());
                    playerView.setBackgroundResource(GameResources.getBackgroundColors().get(players.get(i).getColor()));
                    if (players.get(i).equals(players.get(mGamePresenter.getGame().getTurn())))
                        playerView.setTextColor(getResources().getColor(R.color.light_blue));
                    else
                        playerView.setTextColor(getResources().getColor(R.color.white));
                    playerView.setGravity(Gravity.CENTER);
                    mPlayerTurnsLayout.addView(playerView, lp);
                }
            }
        });
    }
}
