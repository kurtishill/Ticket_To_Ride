package views_and_presenters;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hillcollegemac.tickettoride.R;

public class GameActivity extends AppCompatActivity implements IGameView {

    private IGamePresenter mGamePresenter;

    private TextView mWaitingTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle("Ticket To Ride");

        mGamePresenter = new GamePresenter(this);

        mWaitingTextView = (TextView) findViewById(R.id.game_activity_waiting_text_view);

        // temporary
        if (mGamePresenter.getGame().getPlayers().size() == mGamePresenter.getGame().getMaxNumPlayers())
            changeTitle("");
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
}
