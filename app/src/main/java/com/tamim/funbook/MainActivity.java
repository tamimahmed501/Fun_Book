package com.tamim.funbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;

import me.ibrahimsn.lib.OnItemSelectedListener;
import me.ibrahimsn.lib.SmoothBottomBar;

public class MainActivity extends AppCompatActivity {


    ImageView imageView;
    SmoothBottomBar smoothBottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        smoothBottomBar = findViewById(R.id.smoothbottomber);
        imageView = findViewById(R.id.imageView);





        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, new home());
        fragmentTransaction.commit();





        smoothBottomBar.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public boolean onItemSelect(int i) {
                if (i==0) {
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.frameLayout, new home());
                    fragmentTransaction.commit();

                } else if (i==1) {
                    FragmentManager fmx = getSupportFragmentManager();
                    FragmentTransaction fragmentTransactionx = fmx.beginTransaction();
                    fragmentTransactionx.replace(R.id.frameLayout, new fav());
                    fragmentTransactionx.commit();
                } else if (i==2) {
                    FragmentManager fmy = getSupportFragmentManager();
                    FragmentTransaction fragmentTransactiony = fmy.beginTransaction();
                    fragmentTransactiony.replace(R.id.frameLayout, new home()); // Assuming there is a dashboard fragment class
                    fragmentTransactiony.commit();
                } else if (i==3) {
                    FragmentManager fmy = getSupportFragmentManager();
                    FragmentTransaction fragmentTransactiony = fmy.beginTransaction();
                    fragmentTransactiony.replace(R.id.frameLayout, new fav()); // Assuming there is a dashboard fragment class
                    fragmentTransactiony.commit();
                }
                return true;
            }
        });

















    }
}