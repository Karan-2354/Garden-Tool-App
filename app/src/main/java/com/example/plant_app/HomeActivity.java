package com.example.plant_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    ImageView navigation;
    DrawerLayout drawerLayout;
    TextView home,cart,account,logout;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    EditText t1,t2;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        drawerLayout=findViewById(R.id.drawer);
        logout=findViewById(R.id.logout);

        gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc= GoogleSignIn.getClient(this,gso);
        dbHelper = new DBHelper(this);

        t1=findViewById(R.id.a_name);
        t2=findViewById(R.id.a_email);






        // navigation=findViewById(R.id.ClickMenu);

//        navigation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // do anything whatever you want
//                opeDrawer(drawerLayout);
//            }
//        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });


        bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);


        bottomNavigationView.setOnItemSelectedListener(item ->  {
            switch (item.getItemId())
            {
                case R.id.home:
                    return true;

                case R.id.account:
                    startActivity(new Intent(getApplicationContext(),AccountActivity.class));
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                    return true;

                case R.id.cart:
                    startActivity(new Intent(getApplicationContext(),CartActivity.class));
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                    return true;

            }
            return false;

        });
        RecyclerView recyclerView=findViewById(R.id.recycleview);
        List<photo> photo=new ArrayList<>();

        photo.add(new photo(R.drawable.shovel));
        photo.add(new photo(R.drawable.boots));
        photo.add(new photo(R.drawable.flowerpot));
        photo.add(new photo(R.drawable.hose));
        photo.add(new photo(R.drawable.gloves));
        photo.add(new photo(R.drawable.fence));
        photo.add(new photo(R.drawable.fertilizer));
        photo.add(new photo(R.drawable.grass));
        photo.add(new photo(R.drawable.hedge));
        photo.add(new photo(R.drawable.lawn));
        photo.add(new photo(R.drawable.pruners));
        photo.add(new photo(R.drawable.rake));
        photo.add(new photo(R.drawable.watering));
        photo.add(new photo(R.drawable.wheelie));

        List<Item> item=new ArrayList<Item>();
        item.add(new Item("1","Shovel","230","He was working with a pick and shovel"));
        item.add(new Item("2","Boots","250","He wiped his boots dry with an old rag."));
        item.add(new Item("3","Flower-pots","120","The window looked out on a flower bed."));
        item.add(new Item("4","Hose","150","I ran for the garden hose and filled the watering can."));
        item.add(new Item("5","Gloves","70","I put on my gardening gloves and dig in the soil."));
        item.add(new Item("6","Fence","70","The dog made a leap over the fence."));
        item.add(new Item("7","Fertilizer","230","The organic fertilizer shall keep the soil in good heart."));
        item.add(new Item("8","Grass","250","The grass is greener on the other side."));
        item.add(new Item("9","Hedge","120","He decorated the tree with a hedge shears."));
        item.add(new Item("10","Lawn","150","We need a lawn mower to cut the grass."));
        item.add(new Item("11","Pruners","230","An old gardener was upon the lawn, with a pair of pruners/ pruning shears, looking after some bushes."));
        item.add(new Item("12","Rake","250","In the spring I use the rake to make a good seed bed."));
        item.add(new Item("13","Watering can","120","I dilute the fertilizer in my watering can and sprinkle it over plants and soil."));
        item.add(new Item("14","Recycle bin","150","We put our wheelie bin/ recycling bin out to be emptied every Thursday morning."));

        Myadapter adapter = new Myadapter(getApplicationContext(), item, photo);

        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);

    }
    public void OnClick(View v) {
        // do anything whatever you want
        openDrawer(drawerLayout);
    }
    private void openDrawer(DrawerLayout drawerLayout)
    {
        drawerLayout.openDrawer(GravityCompat.START);
    }
    public void Home(View view)
    {
        Intent intent=new Intent(HomeActivity.this,HomeActivity.class);
        startActivity(intent);
    }
    public void Cart(View view)
    {
        Intent intent=new Intent(HomeActivity.this,CartActivity.class);
        startActivity(intent);
    }
    public void Account(View view)
    {

        Intent intent=new Intent(HomeActivity.this,AccountActivity.class);
        startActivity(intent);
    }
    private void signOut() {
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                finish();
                startActivity(new Intent(HomeActivity.this,MainActivity.class));

            }
        });
    }


}