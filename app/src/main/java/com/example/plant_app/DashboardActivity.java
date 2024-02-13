package com.example.plant_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class DashboardActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        drawerLayout=findViewById(R.id.drawer);

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
        Intent intent=new Intent(DashboardActivity.this,HomeActivity.class);
        startActivity(intent);
    }
    public void Cart(View view)
    {
        Intent intent=new Intent(DashboardActivity.this,CartActivity.class);
        startActivity(intent);
    }
    public void Account(View view)
    {
        Intent intent=new Intent(DashboardActivity.this,AccountActivity.class);
        startActivity(intent);
    }
    public void Logout(View v) {
        Toast.makeText(DashboardActivity.this,"Logout",Toast.LENGTH_SHORT).show();
    }

}