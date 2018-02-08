package views_and_presenters;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hillcollegemac.tickettoride.R;
import com.example.server.Results.LoginResult;
import com.example.server.Results.RegisterResult;
import com.example.server.Results.Result;

public class LoginFragment extends Fragment implements ILoginView {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private EditText mLoginUsernameEditText,
            mLoginPasswordEditText,
            mRegisterUsernameEditText,
            mRegisterPasswordEditText,
            mRegisterConfirmPasswordEditText;
    private Button mLoginButton,
            mRegisterButton;

    private String mParam1;
    private String mParam2;

    private ILoginPresenter mLoginPresenter;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private TextWatcher mRegisterTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (mLoginPresenter != null) {
                boolean b = mLoginPresenter.registerPasswordChanged() &&
                        mLoginPresenter.confirmPasswordChanged() &&
                        mLoginPresenter.registerUsernameChanged();

                enableRegister(b);
            }
        }
    };

    private TextWatcher mLoginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (mLoginPresenter != null) {
                boolean b = mLoginPresenter.loginPasswordChanged() &&
                        mLoginPresenter.loginUsernameChanged();

                enableLogin(b);
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        mLoginPresenter = new LoginPresenter(this);

        mLoginUsernameEditText = (EditText) v.findViewById(R.id.login_username_edit_text);
        mLoginUsernameEditText.addTextChangedListener(mLoginTextWatcher);

        mLoginPasswordEditText = (EditText) v.findViewById(R.id.login_password_edit_text);
        mLoginPasswordEditText.addTextChangedListener(mLoginTextWatcher);

        mRegisterUsernameEditText = (EditText) v.findViewById(R.id.register_username_edit_text);
        mRegisterUsernameEditText.addTextChangedListener(mRegisterTextWatcher);

        mRegisterPasswordEditText = (EditText) v.findViewById(R.id.register_password_edit_text);
        mRegisterPasswordEditText.addTextChangedListener(mRegisterTextWatcher);

        mRegisterConfirmPasswordEditText = (EditText) v.findViewById(R.id.register_confirm_password_edit_text);
        mRegisterConfirmPasswordEditText.addTextChangedListener(mRegisterTextWatcher);

        mLoginButton = (Button) v.findViewById(R.id.login_button);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LoginAsyncTask().execute();
            }
        });

        mRegisterButton = (Button) v.findViewById(R.id.register_button);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LoginAsyncTask().execute();
            }
        });

        return v;
    }

    public void enableLogin(boolean b) {
        mLoginButton.setEnabled(b);
    }

    public void enableRegister(boolean b) {
        mRegisterButton.setEnabled(b);
    }

    public String getLoginUsername() {
        return mLoginUsernameEditText.getText().toString();
    }

    public String getLoginPassword() {
        return mLoginPasswordEditText.getText().toString();
    }

    public String getRegisterUsername() {
        return mRegisterUsernameEditText.getText().toString();
    }

    public String getRegisterPassword() {
        return mRegisterPasswordEditText.getText().toString();
    }

    public String getConfirmPassword() {
        return mRegisterConfirmPasswordEditText.getText().toString();
    }

    public void displayErrorMessage(String s) {
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }

    private class LoginAsyncTask extends AsyncTask<Void, Void, Result> {
        @Override
        protected Result doInBackground(Void... v) {
            if (mLoginButton.isPressed())
                return mLoginPresenter.login();
            else
                return mLoginPresenter.register();
        }

        @Override
        protected void onPostExecute(Result result) {
            if (!result.isSuccess()) {
                displayErrorMessage(result.getErrorMessage());
            }
            else {
                RegisterResult rResult;
                LoginResult lResult;
                if (result.getType().equals("RegisterResult")) {
                    rResult = (RegisterResult) result;
                    mLoginPresenter.postExecuteAddUser(rResult.getPlayer());
                }
                else {
                    lResult = (LoginResult) result;
                    mLoginPresenter.postExecuteAddUser(lResult.getPlayer());
                }
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        }
    }
}
