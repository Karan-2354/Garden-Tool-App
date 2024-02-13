package com.example.plant_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    EditText name,email,password;
    Button signup;
    TextView login;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name=findViewById(R.id.s_name);
        email=findViewById(R.id.s_email);
        password=findViewById(R.id.s_password);
        signup=findViewById(R.id.s_signup);
        login=findViewById(R.id.s_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        db=new DBHelper(this);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Sname=name.getText().toString().trim();
                String Semail=email.getText().toString().trim();
                String Spassword=password.getText().toString().trim();

                if(Sname.equals("") || Semail.equals("") || Spassword.equals(""))
                {
                    Toast.makeText(SignupActivity.this,"Please fill all fields",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Boolean b=db.checkemail(Semail);
                    if(b==false)
                    {
                        Boolean signin=db.insert_data(Sname,Semail,Spassword);
                        if(signin==true)
                        {
                            Toast.makeText(SignupActivity.this,"SignUp Successfully",Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(getApplicationContext(),LoginActivity.class);

                            //startActivity(intent);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(SignupActivity.this,"SignUp Not Successfully",Toast.LENGTH_LONG).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(SignupActivity.this,"User Already Exits \n Please login",Toast.LENGTH_LONG).show();
                    }
                }
                name.setText("");
                email.setText("");
                password.setText("");

            }
        });
    }
}