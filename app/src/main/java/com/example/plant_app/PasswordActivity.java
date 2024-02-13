package com.example.plant_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PasswordActivity extends AppCompatActivity {

    EditText email;
    Button reset;
    DBHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        email=findViewById(R.id.r_email);
        reset=findViewById(R.id.r_reset);
        db=new DBHelper(this);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Remail=email.getText().toString().trim();

                Boolean check=db.checkemail(Remail);
                if(check==true)
                {
                    Intent intent=new Intent(getApplicationContext(),ResetpasswordActivity.class);
                    intent.putExtra("email",Remail);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(PasswordActivity.this,"User not Exits \n Please signup",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
