package views_and_presenters;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hillcollegemac.tickettoride.R;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity implements IGameView,
        OnCloseFragmentListener {

    public static final String GAME_START_STATUS = "status";

    private String mGameStatus;

    private IGamePresenter mGamePresenter;

    private TextView mWaitingTextView;
    private RelativeLayout mViewLayout;

    private Button mDrawCardsButton,
            mPlaceTrainsButton,
            mDrawRoutesButton;

    private DestinationPickerFragment mDestinationPickerFragment;
    private BankFragment mBankFragment;

    // from OnCloseFragmentListener interface in DestinationPickerFragment
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
        mViewLayout = (RelativeLayout) findViewById(R.id.view_layout);

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
                //TODO: start fragment
                return true;
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
        mViewLayout.setBackgroundColor(0);
    }

    public void toggleDrawButtons(boolean toggle) {
        mDrawCardsButton.setEnabled(toggle);
        mPlaceTrainsButton.setEnabled(toggle);
        mDrawRoutesButton.setEnabled(toggle);
    }
}
