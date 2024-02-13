package com.example.plant_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ResetpasswordActivity extends AppCompatActivity {

    TextView email;
    EditText password,repassword;
    Button reset;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpassword);

        email=findViewById(R.id.reset_username);
        password=findViewById(R.id.reset_password);
        repassword=findViewById(R.id.re_reset_password);
        reset=findViewById(R.id.reset);
        db=new DBHelper(this);

        Intent intent=getIntent();
        email.setText(intent.getStringExtra("email"));

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Remail = email.getText().toString().trim();
                String Rpassword = password.getText().toString().trim();
                String Repassword = repassword.getText().toString().trim();

                if (Rpassword.equals(Repassword)) {

                    Boolean check = db.updatepassword(Remail, Rpassword);
                    if (check == true) {
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        Toast.makeText(ResetpasswordActivity.this, "Password Update Successfully", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(ResetpasswordActivity.this, "Password Update Not Successfully", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(ResetpasswordActivity.this, "Password  Not Matching", Toast.LENGTH_LONG).show();

                }
            }

        });
    }
}