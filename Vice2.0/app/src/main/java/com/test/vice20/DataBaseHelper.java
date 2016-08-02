package com.test.vice20;

import android.content.ClipData;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.test.vice20.Models.Item;

import java.util.List;

/**
 * Created by nolbertoarroyo on 8/1/16.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Favorites.db";

    // created DataBaseHelper constructor
    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //get instance method for DataBaseHelper
    private static DataBaseHelper instance;

    public static DataBaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DataBaseHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES_FAVORITES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_ENTRIES_ARTICLES);
        onCreate(db);

    }

    //Using baseColumns to create final properties
    public static abstract class DataEntryFavorites implements BaseColumns {
        public static final String TABLE_NAME = "Favorites";
        public static final String COL_TITLE = "TITLE";
        public static final String COL_PREVIEW = "PREVIEW";
        public static final String COL_ITEM_ID = "ITEM_ID";
        public static final String COL_ID = "_id";
        public static final String COL_PUBDATE = "PUBDATE";
        public static final String COL_BODY = "BODY";
        public static final String COL_AUTHOR = "AUTHOR";
        public static final String COL_DEFAULT_IMAGE = "DEFAULT_IMAGE";

    }
    //Create Favorites table
    private static final String SQL_CREATE_ENTRIES_FAVORITES = "CREATE TABLE " +
            DataEntryFavorites.TABLE_NAME + " (" +
            DataEntryFavorites.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            DataEntryFavorites.COL_TITLE + " TEXT," +
            DataEntryFavorites.COL_PREVIEW + " TEXT," +
            DataEntryFavorites.COL_PUBDATE + " TEXT," +
            DataEntryFavorites.COL_BODY + " TEXT," +
            DataEntryFavorites.COL_AUTHOR + " TEXT," +
            DataEntryFavorites.COL_ITEM_ID + " INTEGER," +
            DataEntryFavorites.COL_DEFAULT_IMAGE + " INTEGER)";

    private static final String SQL_DELETE_ENTRIES_ARTICLES = "DROP TABLE IF EXISTS " +
            DataEntryFavorites.TABLE_NAME;

    //creating favorites columns
    public static final String[] FAVORITES_COLUMNS = {DataEntryFavorites._ID,DataEntryFavorites.COL_ITEM_ID,DataEntryFavorites.COL_TITLE,DataEntryFavorites.COL_PREVIEW, DataEntryFavorites.COL_BODY,DataEntryFavorites.COL_AUTHOR,DataEntryFavorites.COL_PUBDATE,DataEntryFavorites.COL_DEFAULT_IMAGE};

    // method for inserting Articles properties into favorites table
    public void insertRowFavorities(Item item){

        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataEntryFavorites.COL_ITEM_ID, item.getId());
        values.put(DataEntryFavorites.COL_TITLE, item.getTitle());
        values.put(DataEntryFavorites.COL_AUTHOR, item.getAuthor());
        values.put(DataEntryFavorites.COL_BODY, item.getBody());
        values.put(DataEntryFavorites.COL_PREVIEW,item.getPreview());
        values.put(DataEntryFavorites.COL_PUBDATE, item.getPubDate());
        database.insertOrThrow(DataEntryFavorites.TABLE_NAME,null,values);
    }

    public Cursor getFavoritesList() {
        //returning favorites list table with cursor

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(DataEntryFavorites.TABLE_NAME, // a. table
                FAVORITES_COLUMNS, // b. column names
                null, // c. selections
                null, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
        return cursor;
    }
    //deleting article from favorites table by id
    public int deleteFavoritesItem(int id) {
        SQLiteDatabase db = getWritableDatabase();
        int deleteNum = db.delete(DataEntryFavorites.TABLE_NAME,
                DataEntryFavorites.COL_ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
        return deleteNum;
    }
    //get article by id
    public Cursor getArticleById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DataEntryFavorites.TABLE_NAME, // a. table
                FAVORITES_COLUMNS, // b. column names
                DataEntryFavorites.COL_ID + " = ?", // c. selections
                new String[]{String.valueOf(id)}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        return cursor;
    }
    //getting article by author
    public Cursor getArticleByAuthor(String author){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DataEntryFavorites.TABLE_NAME,
                FAVORITES_COLUMNS,
                DataEntryFavorites.COL_AUTHOR + " = ?",
                new String[] {author},
                null,
                null,
                null);

        return cursor;
    }




}
