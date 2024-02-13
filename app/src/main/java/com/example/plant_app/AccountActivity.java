package com.example.plant_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;

public class AccountActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    DrawerLayout drawerLayout;
    ImageView navigation;
    TextView home,cart,account,logout;
    private final int Rcode=1000;
    ImageView imageView;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    EditText t1,t2,t3,t4;
    DBHelper dbHelper;
    DB db;
    Button update;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.item_done)
        {
            Toast.makeText(getApplicationContext(),"done",Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        drawerLayout=findViewById(R.id.drawer);
        dbHelper = new DBHelper(this);



        gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc= GoogleSignIn.getClient(this,gso);

        t1=findViewById(R.id.a_name);
        t2=findViewById(R.id.a_email);
        t3=findViewById(R.id.a_phone);
        t4=findViewById(R.id.a_add);
        update=findViewById(R.id.update);

        displayProfile();


        GoogleSignInAccount act=GoogleSignIn.getLastSignedInAccount(this);
        if(act!=null)
        {
            String personname=act.getDisplayName();
            String personemail=act.getEmail();
            t1.setText(personname);
            t2.setText(personemail);
        }

        imageView=findViewById(R.id.image_view);
        FloatingActionButton btn=findViewById(R.id.floating_action);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent iGallary =new Intent(Intent.ACTION_PICK);
//                iGallary.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(iGallary,Rcode);
                ImagePicker.with(AccountActivity.this).crop().compress(1024).maxResultSize(1080,1080).start();

               // save();

            }
        });
        db=new DB(this);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                byte[] bytespp=covert(imageView);
                String name=t1.getText().toString().trim();
                String email=t2.getText().toString().trim();
                String phone= t3.getText().toString().trim();
                String add=t4.getText().toString().trim();

                boolean y=db.save(1,bytespp);

                if(y==true)
                {
                    Toast.makeText(AccountActivity.this,"Successfully",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(AccountActivity.this,"Unsuccessfully",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

//    public void save()
//    {
//        byte[] bytespp=covert(imageView);
//        db=new DB(AccountActivity.this);
//        if(db.save(1,bytespp))
//        {
//            Toast.makeText(AccountActivity.this,"Successfully",Toast.LENGTH_LONG).show();
//        }
//        else
//        {
//            Toast.makeText(AccountActivity.this,"Unsuccessfully",Toast.LENGTH_LONG).show();
//        }
//
//    }

    private byte[] covert(ImageView imageView)
    {
        Bitmap bitmap=((BitmapDrawable)imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,80,byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Uri uri =data.getData();
        imageView.setImageURI(uri);

//        if(resultCode==RESULT_OK)
//        {
//            if(requestCode==Rcode)
//            {
//                imageView.setImageURI(data.getData());
//            }
//        }

//        drawerLayout=findViewById(R.id.drawer);
//        navigation=findViewById(R.id.navigation);
//        home=findViewById(R.id.nhome);
//        cart=findViewById(R.id.ncart);
//        account=findViewById(R.id.naccount);
//        logout=findViewById(R.id.nlogout);
//
//        navigation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openDrawer(drawerLayout);
//            }
//        });
//        home.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                redirectActivity(AccountActivity.this,HomeActivity.class);
//            }
//        });
//        cart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                redirectActivity(AccountActivity.this,CartActivity.class);
//            }
//        });
//        account.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                recreate();
//            }
//        });
//
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(AccountActivity.this,"Logout",Toast.LENGTH_SHORT).show();
//            }
//        });

        bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.account);


        bottomNavigationView.setOnItemSelectedListener(item ->  {
            switch (item.getItemId())
            {
                case R.id.home:
                    startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                    return true;

                case R.id.account:
                    return true;

                case R.id.cart:
                    startActivity(new Intent(getApplicationContext(),CartActivity.class));
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                    return true;
            }
            return false;

        });
    }
    public void Back_A(View view)
    {
        Intent intent=new Intent(AccountActivity.this,HomeActivity.class);
        startActivity(intent);
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
//        Intent intent=new Intent(AccountActivity.this,HomeActivity.class);
//        startActivity(intent);
//    }
//    public void Cart(View view)
//    {
//        Intent intent=new Intent(AccountActivity.this,CartActivity.class);
//        startActivity(intent);
//    }
//    public void Account(View view)
//    {
//        Intent intent=new Intent(AccountActivity.this,AccountActivity.class);
//        startActivity(intent);
//    }
//    public void Logout(View v) {
//        Toast.makeText(AccountActivity.this,"Logout",Toast.LENGTH_SHORT).show();
//    }


    public void displayProfile() {
        // Retrieve profile data from the SQLite database
        Info profile = getProfileFromDatabase();

        // Update the UI with the profile data
        if (profile != null) {
            t1.setText(profile.getName());
            t2.setText(profile.getEmail());
        }
    }


    private   Info getProfileFromDatabase() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                "name",
                "email"
        };

      Cursor cursor = db.query(
                "users",
                projection,
                null,
                null,
                null,
                null,
                null
        );

        Info profile = null;

        if (cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            // int age = cursor.getInt(cursor.getColumnIndexOrThrow("age"));
            String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));

            profile = new Info(name, email);
        }

        cursor.close();
        return profile;
    }
}