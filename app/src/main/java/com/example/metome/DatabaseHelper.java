package com.example.metome;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;


public class DatabaseHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "MeToMe.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_USER = "user";
    private static final String COL_USER_NAME = "full_name";
    private static final String COL_USER_USERNAME= "username";
    private static final String COL_USER_EMAIL = "email";
    private static final String COL_USER_PASSWORD = "password";

    SQLiteDatabase db;



    public DatabaseHelper(@Nullable Context context){
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String queryUser = "CREATE TABLE " + TABLE_USER + " ("+
                COL_USER_NAME + " TEXT, " +
                COL_USER_USERNAME + "String PRIMARY KEY, " +
                COL_USER_EMAIL + " TEXT, " +
                COL_USER_PASSWORD + " TEXT);";

        db.execSQL(queryUser);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);

    }

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



}
