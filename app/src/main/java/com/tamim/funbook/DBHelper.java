package com.tamim.funbook;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "FavoritesDB";
    private static final String TABLE_NAME = "favorites";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_TIME = "time";
    private static final String KEY_IMAGE_URL = "image_url";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_TIME + " TEXT,"
                + KEY_IMAGE_URL + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addFavorite(String name, String time, String imageUrl) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_TIME, time);
        values.put(KEY_IMAGE_URL, imageUrl);
        long id = db.insert(TABLE_NAME, null, values);
        db.close();

        // Log the saved data with the inserted ID
        Log.d("DBHelper", "Saved item with ID: " + id + ", Name: " + name + ", Time: " + time + ", Image URL: " + imageUrl);
    }

    @SuppressLint("Range")
    public ArrayList<FavItem> getAllFavItems() {
        ArrayList<FavItem> favItems = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                FavItem favItem = new FavItem();
                favItem.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                favItem.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                favItem.setTime(cursor.getString(cursor.getColumnIndex(KEY_TIME)));
                favItem.setImageUrl(cursor.getString(cursor.getColumnIndex(KEY_IMAGE_URL)));
                favItems.add(favItem);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return favItems;
    }

    public void deleteFavorite(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

}
