package com.example.metome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    DatabaseHelper db;

    EditText etFullNameS, etUserNameSU, etEmail,etPasswordSU;
    Button  btnsLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        db = new DatabaseHelper(this);

        etFullNameS = findViewById(R.id.etFullNameS);
        etUserNameSU = findViewById(R.id.etUserNameSU);
        etEmail = findViewById(R.id.etEmail);
        etPasswordSU = findViewById(R.id.etPasswordSU);

        btnsLogin = findViewById(R.id.btnsLogin);



        btnsLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(loginIntent);
            }
        });


    }

    public void btnSignUp(View view)
    {

        String name = etFullNameS.getText().toString();
        String username = etUserNameSU.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPasswordSU.getText().toString();

        long val = db.addUser(name, username, email, password);

        if(val>0){

            Toast.makeText(SignUpActivity.this, "Sign Up successfully!!" , Toast.LENGTH_SHORT).show();
            Intent signupIntent = new Intent(SignUpActivity.this, CategoriesActivity.class);
            startActivity(signupIntent);
        }
        else{
            Toast.makeText(SignUpActivity.this, "Error in registration ", Toast.LENGTH_SHORT).show();
        }
    }


}