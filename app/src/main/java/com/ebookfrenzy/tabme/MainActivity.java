package com.ebookfrenzy.tabme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);

    private EditText Name, Password;
    private TextView Info;
    private Button Login;
    private Button SignUp;
    private int counter = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = (EditText)findViewById(R.id.etName);
        Password = (EditText)findViewById(R.id.etPassword);
        Info = (TextView)findViewById(R.id.tvInfo);
        Login = (Button)findViewById(R.id.btnLogin);
        SignUp = (Button)findViewById(R.id.btnSignUp);

        Info.setText("Number of attempts remaining: " + String.valueOf(counter));

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Name.getText().toString(), Password.getText().toString());
                EditText a = (EditText)findViewById(R.id.etName);
                String mob = a.getText().toString();
                EditText b = (EditText)findViewById(R.id.etPassword);
                String pw = b.getText().toString();

                String pword = helper.searchPass(mob);

                if(pw.equals(pword))
                {
                    Intent intent = new Intent(MainActivity.this, ProjectList.class);
                    intent.putExtra("User", mob);
                    startActivity(intent);
                }
                else
                {
                    Toast val = Toast.makeText(MainActivity.this, "Data don't match!" , Toast.LENGTH_SHORT);
                    val.show();
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
}
