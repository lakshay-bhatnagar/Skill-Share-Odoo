package com.example.skillswapodoo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class LoginPageActivity extends AppCompatActivity {

    TextInputEditText editTextEmail, editTextPassword;
    MaterialButton btnLogin;
    TextView tvSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvSignup = findViewById(R.id.tv_signup);

        btnLogin.setOnClickListener(view -> {
            String email = editTextEmail.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT).show();
            } else {
                // TODO: Add actual login logic here
                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginPageActivity.this, MainActivity.class));
                finish();
            }
        });

        tvSignup.setOnClickListener(view -> {
            startActivity(new Intent(LoginPageActivity.this, SignupActivity.class));
        });
    }
}