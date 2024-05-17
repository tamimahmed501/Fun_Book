package com.tamim.funbook;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

public class home extends Fragment {

    TextView text1, text2, text3, text4, text5, text6;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myView = inflater.inflate(R.layout.fragment_home, container, false);


        text1 = myView.findViewById(R.id.text1);
        text2 = myView.findViewById(R.id.text2);
        text3 = myView.findViewById(R.id.text3);
        text4 = myView.findViewById(R.id.text4);
        text5 = myView.findViewById(R.id.text5);
        text6 = myView.findViewById(R.id.text6);





        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout2, new top());
        fragmentTransaction.commit();


        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                resetColor();

                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout2, new top());
                fragmentTransaction.commit();

                text1.setBackground(getResources().getDrawable(R.drawable.textradious));

            }
        });


        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                resetColor();
                FragmentManager fmx = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransactionx = fmx.beginTransaction();
                fragmentTransactionx.replace(R.id.frameLayout2, new fav());
                fragmentTransactionx.commit();
                text2.setBackground(getResources().getDrawable(R.drawable.textradious));

            }
        });




        text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                resetColor();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout2, new top());
                fragmentTransaction.commit();
                text3.setBackground(getResources().getDrawable(R.drawable.textradious));

            }
        });

        text4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                resetColor();
                FragmentManager fmx = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransactionx = fmx.beginTransaction();
                fragmentTransactionx.replace(R.id.frameLayout2, new mixed());
                fragmentTransactionx.commit();
                text4.setBackground(getResources().getDrawable(R.drawable.textradious));

            }
        });
















        return myView;
    }

    private void resetColor(){


        text1.setBackground(getResources().getDrawable(R.drawable.textradious2));
        text2.setBackground(getResources().getDrawable(R.drawable.textradious2));
        text3.setBackground(getResources().getDrawable(R.drawable.textradious2));
        text4.setBackground(getResources().getDrawable(R.drawable.textradious2));
        text5.setBackground(getResources().getDrawable(R.drawable.textradious2));
        text6.setBackground(getResources().getDrawable(R.drawable.textradious2));


    }
}