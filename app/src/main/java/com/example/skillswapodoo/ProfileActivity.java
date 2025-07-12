package com.example.skillswapodoo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private EditText editName, editEmail, editSkills;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        editName = findViewById(R.id.edit_name);
        editEmail = findViewById(R.id.edit_email);
        editSkills = findViewById(R.id.edit_skills);
        btnSave = findViewById(R.id.btn_save);

        btnSave.setOnClickListener(v -> {
            String name = editName.getText().toString().trim();
            String email = editEmail.getText().toString().trim();
            String skills = editSkills.getText().toString().trim();

            // For now, just show a toast (you can save to SharedPreferences or DB)
            Toast.makeText(this, "Saved: " + name, Toast.LENGTH_SHORT).show();
        });
    }
}
