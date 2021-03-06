package views_and_presenters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.hillcollegemac.tickettoride.R;

import client_model.ClientModelRoot;

public class MainActivity extends AppCompatActivity implements OnCloseFragmentListener {

    private GameWaitingLobbyFragment mGameWaitingLobbyFragment;

    @Override
    public void onClose() {
        mGameWaitingLobbyFragment.toggleViews(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment;

        if (ClientModelRoot.instance().getAuthToken() == null) {
            fragment = new LoginFragment();
            if (getSupportActionBar() != null)
                getSupportActionBar().setTitle("Ticket To Ride: Login or Register");
        }
        else {
            mGameWaitingLobbyFragment = new GameWaitingLobbyFragment();
            fragment = mGameWaitingLobbyFragment;
            if (getSupportActionBar() != null)
                getSupportActionBar().setTitle("Ticket To Ride: Game Waiting Lobby");
        }

        fm.beginTransaction().add(R.id.destination_picker_fragment_container, fragment).commit();
    }


}
