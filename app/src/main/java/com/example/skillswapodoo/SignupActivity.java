package com.example.skillswapodoo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class SignupActivity extends AppCompatActivity {

    TextInputEditText editTextName, editTextEmail, editTextPassword;
    MaterialButton btnSignup;
    TextView tvLoginRedirect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        btnSignup = findViewById(R.id.btnSignup);
        tvLoginRedirect = findViewById(R.id.tv_login_redirect);

        btnSignup.setOnClickListener(view -> {
            String name = editTextName.getText().toString().trim();
            String email = editTextEmail.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                // TODO: Handle signup logic (store, auth, etc.)
                Toast.makeText(this, "Account created successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SignupActivity.this, LoginPageActivity.class));
                finish();
            }
        });

        tvLoginRedirect.setOnClickListener(view -> {
            startActivity(new Intent(SignupActivity.this, LoginPageActivity.class));
            finish();
        });
    }
}