package com.example.metome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    Button btnlSignUp;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnlSignUp = findViewById(R.id.btnlSignup);
        btnLogin = findViewById(R.id.btnLogin);

        btnlSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signupIntent = new Intent(LoginActivity.this , SignUpActivity.class);
                startActivity(signupIntent);

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent login= new Intent(LoginActivity.this,CategoriesActivity.class);
                startActivity(login);
            }
        });



    }
}