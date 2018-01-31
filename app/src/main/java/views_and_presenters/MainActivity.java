package views_and_presenters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hillcollegemac.tickettoride.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment;

        // if model has auth token for user
        //       load GameWaitingLobbyFragment
        // else
        //       load LoginFragment


        // for now it will just load LoginFragment
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle("Ticket To Ride: Login or Register");
        fragment = new GameWaitingLobbyFragment();
        fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
    }


}
