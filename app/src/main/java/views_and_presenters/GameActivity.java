package views_and_presenters;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hillcollegemac.tickettoride.R;

public class GameActivity extends AppCompatActivity implements IGameView {

    private final static String GAME_ID = "GAME_ID";

    private IGamePresenter mGamePresenter;

    private TextView mWaitingTextView;
    private RelativeLayout mViewLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle("Ticket To Ride");

        mGamePresenter = new GamePresenter(this);

        mWaitingTextView = (TextView) findViewById(R.id.game_activity_waiting_text_view);
        mViewLayout = (RelativeLayout) findViewById(R.id.view_layout);

        mGamePresenter.didGameStart();
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
}
