package com.example.signpal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText nameInput, emailInput, passwordInput;
    Button RegisterButton, ResetButton;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dbHelper = new DatabaseHelper(this);

        nameInput = findViewById(R.id.nameET);
        emailInput = findViewById(R.id.emailET);
        passwordInput = findViewById(R.id.passET);

        RegisterButton = findViewById(R.id.registerBT);
        ResetButton = findViewById(R.id.resetBT);

        RegisterButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String username = nameInput.getText().toString().trim();
                String email = emailInput.getText().toString().trim();
                String password = passwordInput.getText().toString().trim();

                if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Register.this, "Missing field! Please fill all",
                            Toast.LENGTH_SHORT).show();
                } else {
                    boolean inserted = dbHelper.insertUser(username, email, password);
                    if (inserted) {
                        Toast.makeText(Register.this, "Registration complete!",
                                Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Register.this, login.class));
                        finish();
                    } else {
                        Toast.makeText(Register.this, "Username already exists!!",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //not to forget reset button
    }
}