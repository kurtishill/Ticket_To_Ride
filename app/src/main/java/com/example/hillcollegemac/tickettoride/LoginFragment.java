package com.example.hillcollegemac.tickettoride;

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
                // call an asyncTask to call the presenter to do login stuff
                // and to load the GameWaitingLobbyFragment with
                // startActivity(new Intent(getActivity(), MainActivity.class));

            }
        });

        mRegisterButton = (Button) v.findViewById(R.id.register_button);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // call an asyncTask to call the presenter to do register stuff
                // and to load the GameWaitingLobbyFragment with
                // startActivity(new Intent(getActivity(), MainActivity.class));
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
}
