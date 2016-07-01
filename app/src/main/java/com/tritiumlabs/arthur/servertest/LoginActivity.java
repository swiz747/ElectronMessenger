package com.tritiumlabs.arthur.servertest;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;


public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    public static Authenticator tempXMPPConnection;
    EditText userName;
    EditText passwordText;
    Button btnLogin;
    TextView signupLink;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = (EditText)findViewById(R.id.input_email);
        passwordText = (EditText)findViewById(R.id.input_password);
        btnLogin = (Button)findViewById(R.id.btn_login);
        signupLink = (TextView)findViewById(R.id.link_signup);

        //TODO: add option to remain logged in/  or remember username and PW

        //TODO: this is for easy testing because im lazy -AB
        userName.setText("Dildo@gmail.com");
        passwordText.setText("fuck123");
        //TODO: there are now 3 places that have this hard coded, we need a global constant or something, maybe pop it in an sqlite table? - AB
        tempXMPPConnection = new Authenticator(this,"tritium","45.35.4.171",5222);

        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
                finish();
            }
        });
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        btnLogin.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        final String user = userName.getText().toString();
        final String password = passwordText.getText().toString();

        // TODO: move to authenticator class for oganization -AB

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        tempXMPPConnection.setLoginCreds(user,password);

                        if(!tempXMPPConnection.login())
                        {
                            onLoginFailed();
                        }
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();

                        progressDialog.dismiss();
                    }
                }, 3000);
    }

    //TODO: holy shit this code is really cool -AB
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                //this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {

        //TODO: how the fuck do we pass the credentials
        btnLogin.setEnabled(true);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        btnLogin.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = userName.getText().toString();
        String password = passwordText.getText().toString();

        if (email.isEmpty() ) {
            userName.setError("enter a valid username");
            valid = false;
        } else {
            userName.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            passwordText.setError(null);
        }

        return valid;
    }
}
