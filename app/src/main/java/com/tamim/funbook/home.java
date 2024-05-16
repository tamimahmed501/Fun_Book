package com.tamim.funbook;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

public class home extends Fragment {

    TabLayout tabLayout;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myView = inflater.inflate(R.layout.fragment_home, container, false);


        tabLayout = myView.findViewById(R.id.tabLayout);


        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout2, new top());
        fragmentTransaction.commit();


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (position==0) {
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.frameLayout2, new top());
                    fragmentTransaction.commit();

                } else if (position==1) {
                    FragmentManager fmx = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransactionx = fmx.beginTransaction();
                    fragmentTransactionx.replace(R.id.frameLayout2, new fav());
                    fragmentTransactionx.commit();
                } else if (position==2) {
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.frameLayout2, new top());
                    fragmentTransaction.commit();

                } else if (position==3) {
                    FragmentManager fmx = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransactionx = fmx.beginTransaction();
                    fragmentTransactionx.replace(R.id.frameLayout2, new mixed());
                    fragmentTransactionx.commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Do nothing when a tab is unselected
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Handle tab reselection if needed
            }
        });













        return myView;
    }
}