package views_and_presenters;

import com.example.server.Results.Result;

/**
 * Created by HillcollegeMac on 1/27/18.
 */

public interface ILoginPresenter {

    boolean registerPasswordChanged();

    boolean confirmPasswordChanged();

    boolean loginPasswordChanged();

    boolean loginUsernameChanged();

    boolean registerUsernameChanged();

    // returns auth token
    /*String login();

    // returns auth token
    String register();*/

    Result login();

    Result register();
}
