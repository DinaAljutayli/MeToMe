package com.example.metome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.metome.Adapters.AdapterArtistRecord;

import java.util.ArrayList;

import retrofit2.Retrofit;

public class ArtistActivity extends AppCompatActivity {

    private RecyclerView rvPieceArtist;
    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);

        rvPieceArtist = findViewById(R.id.rvPieceArtist);
        db = new DatabaseHelper(this);


        loadRecords();


    }

    private void loadRecords()
    {
        AdapterArtistRecord adapterArtistRecord = new AdapterArtistRecord(this,db.getAllPiece(" DESC"));

        rvPieceArtist.setAdapter(adapterArtistRecord);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadRecords();
    }

    public void btnAdd_piece(View view)
    {
        Intent intent = new Intent(this,AddUpdatePhoto.class);
        startActivity(intent);
    }

}