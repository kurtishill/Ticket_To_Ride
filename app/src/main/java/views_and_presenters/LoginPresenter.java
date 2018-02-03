package views_and_presenters;

import AsyncTasks.LoginTask;
import AsyncTasks.RegisterTask;
import client_model.ClientModelRoot;

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
        return mLoginView.getRegisterUsername().length() > 0;
    }

    // returns auth token
    public String login() {
        LoginTask loginTask = new LoginTask();
        loginTask.execute(mLoginView.getLoginUsername() , mLoginView.getLoginPassword());
        return ClientModelRoot.instance().getUser().getUsername();
    }

    // returns auth token
    public String register() {
        RegisterTask registerTask = new RegisterTask();
        registerTask.execute(mLoginView.getLoginUsername() , mLoginView.getLoginPassword());
        return ClientModelRoot.instance().getUser().getUsername();
    }
}
