package com.example.signpal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    EditText Username, Password;
    Button Login, RegisterPage;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        dbHelper = new DatabaseHelper(this);

        Username = (EditText) findViewById(R.id.nameEt1);
        Password = (EditText) findViewById(R.id.passET1);
        Login = (Button) findViewById(R.id.loginBtn);
        RegisterPage = (Button) findViewById(R.id.regPageBtn);


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = Username.getText().toString().trim();
                String pass = Password.getText().toString().trim();

                if (username.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(login.this, "enter username and pass!", Toast.LENGTH_SHORT).show();
                } else {
                    boolean valid = dbHelper.checkUser(username, pass);
                    if (valid) {
                        Toast.makeText(login.this, "Login Successful!:D ", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(login.this, MainActivity.class));
                        finish();
                    } else {
                        Toast.makeText(login.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        RegisterPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this, Register.class));
            }
        });
    }
}