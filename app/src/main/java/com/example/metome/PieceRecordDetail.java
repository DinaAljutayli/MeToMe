package com.example.metome;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.SyncStateContract;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.metome.Adapters.ModelArtistRecord;

public class PieceRecordDetail extends AppCompatActivity {

   private ImageView ivClickProfile;
     private TextView tvcategoryName;

     private String tvPiece_name;
     private String ivPiece_image;

     DatabaseHelper db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Intent intent= getIntent();
        tvPiece_name = intent.getStringExtra("tvPiece_name");
        ivPiece_image = intent.getStringExtra("ivPiece_image");

        db = new DatabaseHelper(this);

        ivClickProfile.findViewById(R.id.ivClickProfile);
        tvcategoryName.findViewById(R.id.tvcategoryName);

        showPieceRecordDetail();




    }

    private void showPieceRecordDetail() {


       //String selectQuert  = String.format("SELECT * FROM %s = %s.%s\", TABLE_PIECE ,) "SELECT * FROM " + db.onCreate(openOrCreateDatabase( + " WHERE " + Constans.COL_PIECE_IMAGE +" =\"" + recordImage+"\"";



       SQLiteDatabase database = db.getWritableDatabase();
        Cursor cursor  = database.rawQuery("select * from piece where COL_PIECE_IMAGE = piece_image ",null );

        if(cursor.getCount()==0) {

        }


    }
}
