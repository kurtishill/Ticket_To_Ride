package com.example.hillcollegemac.tickettoride;

/**
 * Created by HillcollegeMac on 1/27/18.
 */

public interface ILoginView {

    void enableLogin(boolean b);

    void enableRegister(boolean b);

    String getLoginUsername();

    String getLoginPassword();

    String getRegisterUsername();

    String getRegisterPassword();

    String getConfirmPassword();

    void displayErrorMessage(String s);
}
