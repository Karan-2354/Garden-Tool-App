package com.example.plant_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class LoginActivity extends AppCompatActivity {

    EditText email,password;
    Button login;
    TextView signup,forgot;
    DBHelper mydb;
    ImageView btn;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc= GoogleSignIn.getClient(this,gso);

        btn=findViewById(R.id.google);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signin();
            }
        });

        email=findViewById(R.id.l_email);
        password=findViewById(R.id.l_password);
        login=findViewById(R.id.l_login);
        signup=findViewById(R.id.l_register);
        forgot=findViewById(R.id.l_forgot);
        mydb=new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),SignupActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Lemail=email.getText().toString().trim();
                String Lpassword=password.getText().toString().trim();

                if(Lemail.equals("") || Lpassword.equals(""))
                {
                    Toast.makeText(LoginActivity.this,"Please enter the Credentials",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Boolean result=mydb.checkemailpassword(Lemail,Lpassword);
                    if(result==true)
                    {
                        Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                        startActivity(intent);

                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this,"Invalid Credentials",Toast.LENGTH_LONG).show();
                    }
                }
                email.setText("");
                password.setText("");
            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),PasswordActivity.class);
                startActivity(intent);

            }
        });

    }
    public void signin()
    {
        Intent signInIntent=gsc.getSignInIntent();
        startActivityForResult(signInIntent,1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1000)
        {
            Task<GoogleSignInAccount> task=GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                navigateSecondActivity();
            } catch (ApiException e) {
                Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_LONG).show();
            }
        }

    }

    private void navigateSecondActivity() {
        finish();
        Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
        startActivity(intent);
    }
}