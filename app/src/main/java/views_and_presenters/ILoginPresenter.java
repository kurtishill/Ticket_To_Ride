package views_and_presenters;

import com.example.server.Model.Player;
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

    boolean ipAddressChanged();

    Result login();

    Result register();

    void postExecuteAddUser(Player user);
}
