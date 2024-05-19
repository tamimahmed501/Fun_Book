package com.tamim.funbook;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class profile extends Fragment {

    TextView profilepic, mypost;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_profile, container, false);

        profilepic = myView.findViewById(R.id.profilepic);
        mypost = myView.findViewById(R.id.mypost);



        profilepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetColor();
                profilepic.setBackground(getResources().getDrawable(R.drawable.profiletextradious));
                profilepic.setTextColor(getResources().getColor(R.color.white));

            }
        });


        mypost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetColor();
                mypost.setBackground(getResources().getDrawable(R.drawable.profiletextradious));
                mypost.setTextColor(getResources().getColor(R.color.white));

            }
        });



        return myView;
    }


    private void resetColor(){

        mypost.setBackground(getResources().getDrawable(R.drawable.profilelayradious));
        profilepic.setBackground(getResources().getDrawable(R.drawable.profilelayradious));
        profilepic.setTextColor(getResources().getColor(R.color.blue));
        mypost.setTextColor(getResources().getColor(R.color.blue));


    }

}