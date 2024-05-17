package com.tamim.funbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    BottomNavigationView bottomNavigationView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        imageView = findViewById(R.id.imageView);

        // Initial fragment transaction
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, new home());
        fragmentTransaction.commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();

                switch (item.getItemId()) {
                    case R.id.home:
                        fragmentTransaction.replace(R.id.frameLayout, new home());
                        break;
                    case R.id.fav:
                        fragmentTransaction.replace(R.id.frameLayout, new fav());
                        break;
                    case R.id.popular:
                        fragmentTransaction.replace(R.id.frameLayout, new home());
                        break;
                    case R.id.celebrity:
                        fragmentTransaction.replace(R.id.frameLayout, new fav());
                        break;
                    case R.id.profile:
                        fragmentTransaction.replace(R.id.frameLayout, new home());
                        break;
                    default:
                        return false;
                }

                fragmentTransaction.commit();
                return true;
            }
        });
    }
}
