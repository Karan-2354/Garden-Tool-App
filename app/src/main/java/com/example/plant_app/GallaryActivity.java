package com.example.plant_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GallaryActivity extends AppCompatActivity {

    ImageView img;
    TextView tx,price,desc,id;
    Button cart,m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallary);

        img=findViewById(R.id.images);
        tx=findViewById(R.id.img_desc);
        price=findViewById(R.id.img_price);
        desc=findViewById(R.id.img_dec);
        id=findViewById(R.id.img_id);
        cart=findViewById(R.id.cartbutton);


        img.setImageResource(getIntent().getIntExtra("imagename",0));
        id.setText(getIntent().getStringExtra("id"));
        tx.setText(getIntent().getStringExtra("name"));
        price.setText(getIntent().getStringExtra("price"));
        desc.setText(getIntent().getStringExtra("desc"));

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDatabase db= Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"cart_db").allowMainThreadQueries().build();
                ProductDao productDao=db.ProductDao();
                Boolean check=productDao.is_exist(Integer.parseInt(id.getText().toString()));
                if(check==false)
                {
                    int pid=Integer.parseInt(id.getText().toString());
                    String pname=tx.getText().toString();
                    int price1= Integer.parseInt(price.getText().toString());
                    //  int qnt=Integer.parseInt(t4.getText().toString());
                    productDao.insertrecord(new Product(pid,pname,price1,1));
                    Toast.makeText(GallaryActivity.this,"Product Inserted Successfully",Toast.LENGTH_LONG).show();

                }
                else
                {

                    Toast.makeText(GallaryActivity.this,"Product Already in Cart",Toast.LENGTH_LONG).show();
                }
                startActivity(new Intent(getApplicationContext(),CartActivity.class));
            }
        });
    }
}