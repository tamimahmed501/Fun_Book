package com.tamim.funbook;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

public class top extends Fragment {
    private DBHelper dbHelper;

    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private DownloadManager downloadManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_top, container, false);


        // Initialize the dbHelper object
        dbHelper = new DBHelper(getContext());



        recyclerView = myView.findViewById(R.id.recyclerView);
        swipeRefreshLayout = myView.findViewById(R.id.swipeRefreshLayout);

        myAdapter = new MyAdapter();
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        swipeRefreshLayout.setOnRefreshListener(this::post);

        downloadManager = (DownloadManager) getContext().getSystemService(Context.DOWNLOAD_SERVICE);
        registerDownloadReceiver();

        post();

        return myView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unregisterDownloadReceiver();
    }

    private void registerDownloadReceiver() {
        getContext().registerReceiver(downloadReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    private void unregisterDownloadReceiver() {
        getContext().unregisterReceiver(downloadReceiver);
    }

    private void post() {
        arrayList.clear();
        swipeRefreshLayout.setRefreshing(true);

        String url = "https://wikipediabangla.com/apps/funbook/funbookpost.php?name=";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        swipeRefreshLayout.setRefreshing(false);

                        for (int x = 0; x < response.length(); x++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(x);
                                String name = jsonObject.getString("name");
                                String time = jsonObject.getString("time");
                                String id = jsonObject.getString("id");
                                String uid = jsonObject.getString("uid");
                                String caption = jsonObject.getString("caption");
                                String images = jsonObject.getString("images");

                                HashMap<String, String> hashMap = new HashMap<>();
                                hashMap.put("name", name);
                                hashMap.put("time", time);
                                hashMap.put("id", id);
                                hashMap.put("uid", uid);
                                hashMap.put("caption", caption);
                                hashMap.put("images", images);

                                arrayList.add(hashMap);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        myAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        swipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View myView = layoutInflater.inflate(R.layout.post, parent, false);
            return new MyViewHolder(myView);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            HashMap<String,String> hashMap = arrayList.get(position);
            String namex = hashMap.get("name");
            String timex = hashMap.get("time");
            String id = hashMap.get("id");
            String imagesx = hashMap.get("images");

            holder.name1.setText(namex);
            holder.time.setText(timex);

            Picasso.get()
                    .load(imagesx)
                    .placeholder(R.drawable.load2)
                    .error(R.drawable.load2)
                    .into(holder.postImages);






















            holder.threeDot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                    showCustomDialog(imagesx, id);




                }
            });




            final boolean[] isLikeClicked = {false};

            holder.like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    isLikeClicked[0] = !isLikeClicked[0];


                    if (isLikeClicked[0]) {
                        holder.like.setImageResource(R.drawable.hearts);

                    } else {
                        holder.like.setImageResource(R.drawable.love);
                    }
                }
            });





            final boolean[] isFavClicked = {false};

            holder.fav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    isFavClicked[0] = !isFavClicked[0];


                    if (isFavClicked[0]) {
                        holder.fav.setImageResource(R.drawable.fav2);
                        dbHelper.addFavorite(namex, timex, imagesx);

                    } else {
                        holder.fav.setImageResource(R.drawable.fav);
                    }
                }
            });



        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        private class MyViewHolder extends RecyclerView.ViewHolder {
            TextView name1, time;
            ImageView postImages, like, fav, threeDot, share;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                name1 = itemView.findViewById(R.id.name);
                time = itemView.findViewById(R.id.time);
                postImages = itemView.findViewById(R.id.postImages);
                like = itemView.findViewById(R.id.like);

                fav = itemView.findViewById(R.id.fav);
                threeDot = itemView.findViewById(R.id.threeDot);
                share = itemView.findViewById(R.id.share);
            }
        }
    }

    private BroadcastReceiver downloadReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            long downloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            if (downloadId != -1) {
                DownloadManager.Query query = new DownloadManager.Query();
                query.setFilterById(downloadId);
                Cursor cursor = downloadManager.query(query);
                if (cursor.moveToFirst()) {
                    int statusIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);
                    int status = cursor.getInt(statusIndex);
                    if (status == DownloadManager.STATUS_SUCCESSFUL) {
                        @SuppressLint("Range") String savedImagePath = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
                        openDownloadedFile(context, savedImagePath);
                    } else {
                        // Handle download failure if needed
                    }
                }
                cursor.close();
            }
        }

        private void openDownloadedFile(Context context, String savedImagePath) {
            // Implement logic to open the downloaded image file
            // For simplicity, let's just show a toast message with the file path
            Toast.makeText(context, "Downloaded Successfully", Toast.LENGTH_SHORT).show();
        }
    };

    private void downloadImage(String imageUrl) {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(imageUrl));
        request.setTitle("Downloading Image");
        request.setDescription("Downloading...");
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "image.jpg");
        downloadManager.enqueue(request);
    }

    private void reportPost(String postId) {
        // Implement post report logic here
    }



    private void showCustomDialog(String imageUrl, String postId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View dialogView = getLayoutInflater().inflate(R.layout.downloadalert, null);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();

        TextView textDownload = dialogView.findViewById(R.id.text_download);
        TextView textReport = dialogView.findViewById(R.id.text_report);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView close = dialogView.findViewById(R.id.close);



        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        textDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadImage(imageUrl);
                alertDialog.dismiss();
            }
        });

        textReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reportPost(postId);
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }

}
