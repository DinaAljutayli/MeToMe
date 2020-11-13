package com.example.metome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ProfileActivity extends AppCompatActivity {

    Button btnLogout;
    ImageView ivProfilePhoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btnLogout = findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent LogoutIntent = new Intent(ProfileActivity.this,LoginSignUpActivity.class);
                startActivity(LogoutIntent);

            }
        });

        ivProfilePhoto = findViewById(R.id.ivProfilePhoto);


    }


    public void ivProfilePhoto(View view)
    {

        Intent intent = new Intent(ProfileActivity.this,AddUpdatePhoto.class);

        startActivity(intent);

    }




}