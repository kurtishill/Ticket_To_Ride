package views_and_presenters;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hillcollegemac.tickettoride.R;
import com.example.server.Model.City;
import com.example.server.Model.Player;
import com.example.server.Model.Route;

import java.util.List;

import client_model.ClientModelRoot;
import client_model.GameOverState;
import client_model.LastTurnState;
import client_model.NotYourTurnState;
import client_model.StartUpState;
import client_model.State;
import client_model.YourTurnState;

public class GameActivity extends AppCompatActivity implements IGameView,
        OnCloseFragmentListener {

    public static final String GAME_START_STATUS = "status";

    private String mGameStatus;

    private IGamePresenter mGamePresenter;

    private TextView mWaitingTextView;

    private ImageView mGameMapImageView;
    private Bitmap mBitmap;
    private Canvas mCanvas;

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
    private ClaimRouteFragment mClaimRouteFragment;
    private PlayerStatsFragment mPlayerStatsFragment;
    private GameHistoryFragment mGameHistoryFragment;
    private ChatFragment mChatFragment;
    private DisplayDestinationCardsFragment mDisplayDestinationCardsFragment;

    private State state;

    public State getState() {
        return state;
    }

    public void changeState(State state) {
        this.state = state;
    }

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

        mGameMapImageView = (ImageView) findViewById(R.id.game_map_image_view);
        mGameMapImageView.post( new Runnable() {
            @Override
            public void run() {
                mBitmap = Bitmap.createBitmap(mGameMapImageView.getWidth(),
                        mGameMapImageView.getHeight(), Bitmap.Config.ARGB_8888);

                mCanvas = new Canvas(mBitmap);
                ifClaimedRoutesExist();
            }
        });

        mPlayerTurnsLayout = (LinearLayout) findViewById(R.id.player_turn_layout);
        displayPlayerTurn();

        state = new StartUpState();

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

                String s = "";
                for(int i = 0; i < ClientModelRoot.instance().getCurrGame().getPlayers().size(); i++)
                {
                    if (ClientModelRoot.instance().getUser().getID().equals(ClientModelRoot.instance().getCurrGame().getPlayers().get(i).getID()))
                    {
                        s = ClientModelRoot.instance().getCurrGame().getPlayers().get(i).getState();
                    }
                }

                if(s.equals("lastTurn") || state.toString().equals("lastTurn")){
                    changeState(new GameOverState());
                    changeToGameOver();
                }
                else
                {
                    checkForLastTurn();
                    toggleButtons(false);
                    if(!state.toString().equals("lastTurn"))
                        changeState(new NotYourTurnState());
                }

                if(checkForGameOver())
                {
                    //TODO add fragment for game over here
                    displayToast("Game Over");
                }
            }
        });

        mPlaceTrainsButton = (Button) findViewById(R.id.place_trains_button);
        mPlaceTrainsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                toggleButtons(false);
                toggleMenu(false);
                FragmentManager fm = getSupportFragmentManager();
                mClaimRouteFragment = new ClaimRouteFragment();
                fm.beginTransaction().replace(R.id.claim_route_fragment_container, mClaimRouteFragment)
                        .addToBackStack(null).commit();
              
              if(state.toString().equals("lastTurn")){
                    changeState(new GameOverState());
                    changeToGameOver();
                }
                else
                {
                    checkForLastTurn();
                    toggleButtons(false);
                    if(!state.toString().equals("lastTurn"))
                        changeState(new NotYourTurnState());
                }

                if(checkForGameOver())
                {
                    //TODO add fragment for game over here
                    displayToast("Game Over");
                }
            }
        });

        mDrawRoutesButton = (Button) findViewById(R.id.draw_routes_button);
        mDrawRoutesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleButtons(false);
                toggleMenu(false);
                FragmentManager fm = getSupportFragmentManager();
                mDestinationPickerFragment = DestinationPickerFragment.newInstance(state);
                fm.beginTransaction().replace(R.id.destination_picker_fragment_container, mDestinationPickerFragment)
                        .addToBackStack(null).commit();

                if(state.toString().equals("lastTurn")){
                    changeState(new GameOverState());
                    changeToGameOver();
                }
                else
                {
                    checkForLastTurn();
                    toggleButtons(false);
                    if(!state.toString().equals("lastTurn"))
                        changeState(new NotYourTurnState());
                }

                if(checkForGameOver())
                {
                    //TODO add fragment for game over here
                    displayToast("Game Over");
                }
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

    public void checkForLastTurn()
    {
        //Checking to see if any player has less than two train cards. If so, make it last turn state.
        for(int i = 0; i < ClientModelRoot.instance().getCurrGame().getPlayers().size(); i++)
        {
            if(ClientModelRoot.instance().getCurrGame().getPlayers().get(i).getNumTrainCars() <= 2)
            {
                for(int k = 0; k < ClientModelRoot.instance().getCurrGame().getPlayers().size(); k++)
                {
                    changeState(new LastTurnState());
                    ClientModelRoot.instance().getCurrGame().getPlayers().get(k).setState("lastTurn");
                    displayToast("Last Turn");
                }
                break;
            }
        }
    }

    public boolean checkForGameOver()
    {
        for(int i = 0; i < ClientModelRoot.instance().getCurrGame().getPlayers().size(); i++)
        {
            if(!ClientModelRoot.instance().getCurrGame().getPlayers().get(i).getState().equals("gameOver"))
                return false;
        }
        return true;
    }

    public void changeToGameOver()
    {
        for(int i = 0; i < ClientModelRoot.instance().getCurrGame().getPlayers().size(); i++)
        {
            if(ClientModelRoot.instance().getUser().getID().equals(ClientModelRoot.instance().getCurrGame().getPlayers().get(i).getID()))
            {
                ClientModelRoot.instance().getCurrGame().getPlayers().get(i).setState("gameOver");
            }
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

    public void drawRouteLine(final Route route, final Player player) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                DrawLine drawLine = new DrawLine();
                drawLine.drawClaimedRoute(route, player);
            }
        });
    }

    public void onStartUp()
    {
        toggleButtons(false);
        changeState(new StartUpState());
        FragmentManager fm = getSupportFragmentManager();
        mDestinationPickerFragment = DestinationPickerFragment.newInstance(state);
        fm.beginTransaction().replace(R.id.destination_picker_fragment_container, mDestinationPickerFragment)
                .addToBackStack(null).commit();
    }

    public void toggleButtons(boolean toggle) {
        // intercept and check to see if it's the users turn or not
        // and to check if the player is in the start up state
        if (toggle) {
            toggle = mGamePresenter.isItUsersTurn();
            if(!state.toString().equals("lastTurn"))
                changeState(new YourTurnState());
            if (mGamePresenter.getUser().getState().equals("startup"))
                toggle = false;
        }
//        else
//        {
//            if(!state.toString().equals("lastTurn") && !state.toString().equals("startup"))
//                changeState(new NotYourTurnState());
//        }

        if(!mGamePresenter.isItUsersTurn() && !state.toString().equals("lastTurn"))
            changeState(new NotYourTurnState());

        final boolean threadToggle = toggle;
        try {
            mDrawCardsButton.setEnabled(toggle);
            mPlaceTrainsButton.setEnabled(toggle);
            mDrawRoutesButton.setEnabled(toggle);
        }
        catch (Exception e) {
            this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mDrawCardsButton.setEnabled(threadToggle);
                    mPlaceTrainsButton.setEnabled(threadToggle);
                    mDrawRoutesButton.setEnabled(threadToggle);
                }
            });
        }
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

    private class DrawLine {

        public DrawLine() {

        }

        public void drawClaimedRoute(Route route, Player player) {
            Paint paint = new Paint();
            paint.setColor(GameResources.getLineColors().get(player.getColor()));
            paint.setStrokeWidth(10);

            mCanvas.drawLine(route.getCity1().getX(), route.getCity1().getY(), route.getCity2().getX(), route.getCity2().getY(), paint);

            mGameMapImageView.setImageBitmap(mBitmap);
        }
    }

    private void ifClaimedRoutesExist() {
        Paint paint = new Paint();
        paint.setStrokeWidth(10);
        List<Player> players = ClientModelRoot.instance().getCurrGame().getPlayers();
        for (int i = 0; i < players.size(); i++) {
            List<Route> claimedRoutes = players.get(i).getClaimedRoutes();
            paint.setColor(GameResources.getLineColors().get(players.get(i).getColor()));
            for (int j = 0; j < claimedRoutes.size(); j++) {
                mCanvas.drawLine(claimedRoutes.get(j).getCity1().getX(), claimedRoutes.get(j).getCity1().getY(),
                        claimedRoutes.get(j).getCity2().getX(), claimedRoutes.get(j).getCity2().getY(), paint);
            }
        }
        mGameMapImageView.setImageBitmap(mBitmap);
    }
}
