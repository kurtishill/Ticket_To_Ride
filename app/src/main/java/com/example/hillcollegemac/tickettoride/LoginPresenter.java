package com.example.hillcollegemac.tickettoride;

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
        return null;
    }

    // returns auth token
    public String register() {
        return null;
    }
}
