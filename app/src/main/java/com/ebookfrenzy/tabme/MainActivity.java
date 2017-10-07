package com.ebookfrenzy.tabme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);

    private EditText Mobile, Password;
    private TextView Info;
    private Button Login;
    private Button SignUp;
    private int counter = 5;
    LoginButton fbLogin;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Mobile = (EditText)findViewById(R.id.etMobile);
        Password = (EditText)findViewById(R.id.etPassword);
        Info = (TextView)findViewById(R.id.tvInfo);
        Login = (Button)findViewById(R.id.btnLogin);
        SignUp = (Button)findViewById(R.id.btnSignUp);
        fbLogin = (LoginButton) findViewById(R.id.login_button);
        fbLogin.setReadPermissions("email", "public_profile");

        callbackManager = CallbackManager.Factory.create();
        fbLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                String userID = loginResult.getAccessToken().getUserId();
                GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        displayUserInfo(object);
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields" , "first_name , last_name , email , id");
                graphRequest.setParameters(parameters);
                graphRequest.executeAsync();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        Info.setText("Number of attempts remaining: " + String.valueOf(counter));

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            validate(Mobile.getText().toString(), Password.getText().toString());
            String mobile = Mobile.getText().toString();
            String passwprd = Password.getText().toString();

            String retreivedPassword = helper.searchPass(mobile);
            if(retreivedPassword != null && retreivedPassword.equals(passwprd)) {
                Intent intent = new Intent(MainActivity.this, ProjectList.class);
                intent.putExtra("User", mobile);
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "Data don't match!" , Toast.LENGTH_SHORT).show();
            }

            }
        });
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUP();
            }
        });


    }


    private void validate(String userName, String userPassword){

        if((userName.equals("Admin")) && (userPassword.equals("1234")))
        {
            Intent intent = new Intent(MainActivity.this, ProjectList.class);
            startActivity(intent);
        }
        else
        {
            counter--;
            Info.setText("Attempts remaining:" + String.valueOf(counter));
            if(counter == 0)
            {
                Login.setEnabled(false);
            }
        }
    }
    private void signUP()
    {
        Intent intent1 = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity((intent1));

    }
    public void displayUserInfo(JSONObject object)
    {
        String first_name, last_name, email, id;
        first_name ="";
        last_name = "";
        email = "";
        id = "";
        try{
            first_name = object.getString("first_name");
            last_name = object.getString("last_name");
            email = object.getString("email");
            id = object.getString("id");

        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        TextView tv_name, tv_email, tv_id;
        tv_name = (TextView)findViewById(R.id.tv_name);
        tv_email = (TextView) findViewById(R.id.tv_email);
        tv_id = (TextView) findViewById(R.id.tv_id);

        tv_name.setText(first_name + "" + last_name);
        tv_email.setText("Email: " + email );
        tv_id.setText("ID:" + id);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
