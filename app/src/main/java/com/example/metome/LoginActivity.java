package com.example.metome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    DatabaseHelper db;
    Button btnlSignup, btnlLogin, btnLogin;
    EditText etUsername, etPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DatabaseHelper(this);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);

        btnlSignup = findViewById(R.id.btnlSignup);
        btnlLogin = findViewById(R.id.btnlLogin);
        btnLogin = findViewById(R.id.btnLogin);

        btnlSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signupIntent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(signupIntent);

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent loginIntent = new Intent(LoginActivity.this, LoginActivity.class);
                startActivity(loginIntent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                boolean res = db.checkUser(username, password);

                if (res) {
                    Intent categoryIntent = new Intent(LoginActivity.this, CategoriesActivity.class);
                    startActivity(categoryIntent);
                } else {
                    Toast.makeText(LoginActivity.this, "Not register, Please Sign up", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}