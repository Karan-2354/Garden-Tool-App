package com.example.plant_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    DrawerLayout drawerLayout;
    RecyclerView recview;
    TextView rateview;
    Button check;

    ImageView navigation;
    TextView n_home,n_cart,n_account,n_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        drawerLayout=findViewById(R.id.drawer);
        check=findViewById(R.id.checkout);

        rateview=findViewById(R.id.rateview);
        getroomdata();

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this,AccountActivity.class));
            }
        });

//        drawerLayout=findViewById(R.id.drawer);
//        navigation=findViewById(R.id.navigation);
//        n_home=findViewById(R.id.nhome);
//        n_cart=findViewById(R.id.ncart);
//        n_account=findViewById(R.id.naccount);
//        n_logout=findViewById(R.id.nlogout);
//
//        navigation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openDrawer(drawerLayout);
//            }
//        });
//        n_home.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                redirectActivity(CartActivity.this,HomeActivity.class);
//            }
//        });
//        n_cart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                recreate();
//            }
//        });
//        n_account.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                redirectActivity(CartActivity.this,AccountActivity.class);
//            }
//        });
//
//        n_logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(CartActivity.this,"Logout",Toast.LENGTH_SHORT).show();
//            }
//        });

        bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.cart);


        bottomNavigationView.setOnItemSelectedListener(item ->  {
            switch (item.getItemId())
            {
                case R.id.home:
                    startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

                    return true;

                case R.id.account:
                    startActivity(new Intent(getApplicationContext(),AccountActivity.class));
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                    return true;

                case R.id.cart:

                    return true;

            }
            return false;

        });
    }
    public void Back(View view)
    {
        Intent intent=new Intent(CartActivity.this,HomeActivity.class);
        startActivity(intent);
    }
    public void getroomdata()
    {
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "cart_db").allowMainThreadQueries().build();
        ProductDao productDao = db.ProductDao();

        recview=findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));

        List<Product> products=productDao.getallproduct();

        abc adapter=new abc(products, rateview);
        recview.setAdapter(adapter);

        int sum=0,i;
        for(i=0;i< products.size();i++)
            sum=sum+(products.get(i).getPrice()*products.get(i).getQnt());

        rateview.setText("Total Amount : INR "+sum);
    }
//    public void OnClick(View v) {
//        // do anything whatever you want
//        openDrawer(drawerLayout);
//    }
//    private void openDrawer(DrawerLayout drawerLayout)
//    {
//        drawerLayout.openDrawer(GravityCompat.START);
//    }
//    public void Home(View view)
//    {
//        Intent intent=new Intent(CartActivity.this,HomeActivity.class);
//        startActivity(intent);
//    }
//    public void Cart(View view)
//    {
//        Intent intent=new Intent(CartActivity.this,CartActivity.class);
//        startActivity(intent);
//    }
//    public void Account(View view)
//    {
//        Intent intent=new Intent(CartActivity.this,AccountActivity.class);
//        startActivity(intent);
//    }
//    public void Logout(View v) {
//        Toast.makeText(CartActivity.this,"Logout",Toast.LENGTH_SHORT).show();
//    }

}