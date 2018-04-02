package views_and_presenters;

import com.example.server.Model.Player;
import com.example.server.Results.Result;

import java.util.ArrayList;
import java.util.List;

import Network.ServerProxy;
import gui_facade.AddUserService;

/**
 * Created by HillcollegeMac on 1/29/18.
 */

public class LoginPresenter implements ILoginPresenter {

    private ILoginView mLoginView;

    public LoginPresenter(ILoginView v) {
        mLoginView = v;
    }

    public boolean registerPasswordChanged() {
        return mLoginView.getRegisterPassword().length() > 5;
    }

    public boolean confirmPasswordChanged() {
        return mLoginView.getConfirmPassword().equals(mLoginView.getRegisterPassword());
    }

    public boolean loginPasswordChanged() {
        return mLoginView.getLoginPassword().length() > 5;
    }

    public boolean loginUsernameChanged() {
        return mLoginView.getLoginUsername().length() > 0;
    }

    public boolean registerUsernameChanged() {
        return mLoginView.getRegisterUsername().length() > 0 && mLoginView.getRegisterUsername().length() < 11;
    }

    public Result login() {
        List<Object> data = new ArrayList<>();
        ServerProxy.getInstance().setServerHost(mLoginView.getIpAddress());
        data.add(mLoginView.getLoginUsername());
        data.add(mLoginView.getLoginPassword());
        return ServerProxy.getInstance()
                .command("Login", data, null);

    }

    public Result register() {
        List<Object> data = new ArrayList<>();
        ServerProxy.getInstance().setServerHost(mLoginView.getIpAddress());
        data.add(mLoginView.getRegisterUsername());
        data.add(mLoginView.getRegisterPassword());
        return ServerProxy.getInstance()
                .command("Register", data, null);
    }

    public void postExecuteAddUser(Player user) {
        AddUserService.addUser(user);
    }
}
