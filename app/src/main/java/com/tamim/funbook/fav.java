package com.tamim.funbook;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class fav extends Fragment {
    // Declare RecyclerView, adapter, and DBHelper
    private RecyclerView recyclerView;
    private FavAdapter favAdapter;
    private DBHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_fav, container, false);

        recyclerView = myView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        dbHelper = new DBHelper(getContext());

        // Retrieve data from SQLite database
        ArrayList<FavItem> favItems = dbHelper.getAllFavItems();

        // Set up RecyclerView adapter
        favAdapter = new FavAdapter(favItems);
        recyclerView.setAdapter(favAdapter);

        return myView;
    }

    // Method to refresh the data
    private void refreshData() {
        ArrayList<FavItem> updatedFavItems = dbHelper.getAllFavItems();
        favAdapter.updateData(updatedFavItems);
    }
}
