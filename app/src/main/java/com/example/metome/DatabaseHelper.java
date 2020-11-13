package com.example.metome;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import com.example.metome.Adapters.ModelArtistRecord;

import java.util.ArrayList;


public class DatabaseHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "MeToMe.db";
    private static final int DATABASE_VERSION = 4;

    private static final String TABLE_USER = "user";
    private static final String COL_USER_NAME = "full_name";
    private static final String COL_USER_USERNAME= "username";
    private static final String COL_USER_EMAIL = "email";
    private static final String COL_USER_PASSWORD = "password";
    private static final String COL_IMAGE = "image";


    private static final String TABLE_PIECE = "piece";
    private static final String COL_PIECE_NAME = "piece_name";
    private static final String COL_PIECE_ID = "piece_id";
    private static final String COL_PIECE_IMAGE = "piece_image";




    public DatabaseHelper(@Nullable Context context){
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String queryUser = "CREATE TABLE " + TABLE_USER + " ("+
                COL_USER_USERNAME + " TEXT PRIMARY KEY, " +
                COL_IMAGE + " TEXT, " +
                COL_USER_NAME + " TEXT, " +
                COL_USER_EMAIL + " TEXT, " +
                COL_USER_PASSWORD + " TEXT);";

        final String queryPiece = "CREATE TABLE "+ TABLE_PIECE + " (" +
                COL_PIECE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COL_PIECE_NAME + " TEXT, " +
                COL_PIECE_IMAGE + " TEXT);";

        db.execSQL(queryUser);
        db.execSQL(queryPiece);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PIECE);
        onCreate(db);

    }



    // insert column image
    public long addUser(String name, String username, String email, String password){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv =  new ContentValues();

        cv.put(COL_USER_NAME,name);
        cv.put(COL_USER_USERNAME, username);
        cv.put(COL_USER_EMAIL, email);
        cv.put(COL_USER_PASSWORD,password);

        long res = db.insert(TABLE_USER,null,cv);
        return res;
    }

    public long addPhoto(String image)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv =  new ContentValues();

        cv.put(COL_IMAGE,image);
        long res = db.insert(TABLE_USER,null,cv);

        return res;
    }

    public boolean checkUser(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        String [] columns = { COL_USER_USERNAME};

        String selection = COL_USER_USERNAME + "=?" + " and " + COL_USER_PASSWORD + "=?";
        String [] selectionArgs = {username , password};
        Cursor cursor = db.query(TABLE_USER, columns, selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if(count>0)
            return true;
        else
            return false;

    }


    public long addPiece(String image, String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COL_PIECE_IMAGE,image);
        cv.put(COL_PIECE_NAME,name);

        long res = db.insert(TABLE_PIECE,null,cv);

        return res;
    }




    public ArrayList<ModelArtistRecord> getAllPiece(String orderBy)
    {
        ArrayList<ModelArtistRecord> recordsList = new ArrayList<>();

        String selectQuery = "SELECT * FROM "+ TABLE_PIECE + " ORDER BY " + orderBy ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst())
        {
            do {
                ModelArtistRecord modelArtistRecord = new ModelArtistRecord(
                        ""+cursor.getString(cursor.getColumnIndex(COL_PIECE_IMAGE)),
                        ""+cursor.getString(cursor.getColumnIndex(COL_PIECE_NAME)));

                recordsList.add(modelArtistRecord);
            }while (cursor.moveToNext());

            db.close();

        }
        return recordsList;
    }



}
