package com.tamim.funbook;

import static java.security.AccessController.getContext;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.FavViewHolder> {
    private ArrayList<FavItem> favItems;
    private Context context;

    public FavAdapter(ArrayList<FavItem> favItems) {
        this.favItems = favItems;
    }

    @NonNull
    @Override
    public FavViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.fav, parent, false);
        return new FavViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavViewHolder holder, int position) {
        FavItem favItem = favItems.get(position);
        holder.bind(favItem);
    }

    @Override
    public int getItemCount() {
        return favItems.size();
    }

    public class FavViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView, share, fav, threeDot;
        private TextView nameTextView;
        private TextView timeTextView;

        public FavViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.postImages);
            nameTextView = itemView.findViewById(R.id.name);
            timeTextView = itemView.findViewById(R.id.time);
            share = itemView.findViewById(R.id.share);
            fav = itemView.findViewById(R.id.fav);
            threeDot = itemView.findViewById(R.id.threeDot);
        }

        public void bind(FavItem favItem) {
            // Bind FavItem data to views
            nameTextView.setText(favItem.getName());
            timeTextView.setText(favItem.getTime());
            Picasso.get().load(favItem.getImageUrl()).into(imageView);




            fav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Get the position of the item clicked
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        // Retrieve the FavItem object at the clicked position
                        FavItem favItem = favItems.get(position);
                        // Delete the corresponding item from the SQLite database
                        deleteItemFromDatabase(favItem.getId());
                        // Remove the item from the RecyclerView
                        favItems.remove(position);
                        notifyItemRemoved(position);
                    }
                }
            });






        }


    }


    private void deleteItemFromDatabase(int id) {
        // Create or obtain an instance of your DBHelper class
        DBHelper dbHelper = new DBHelper(context);
        // Delete the item with the specified id from the database
        dbHelper.deleteFavorite(id);
        // Close the DBHelper instance to release resources
        dbHelper.close();
    }


    public void updateData(ArrayList<FavItem> favItems) {
        this.favItems.clear();
        this.favItems.addAll(favItems);
        notifyDataSetChanged();
    }



}
