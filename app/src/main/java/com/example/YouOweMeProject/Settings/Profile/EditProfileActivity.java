package com.example.YouOweMeProject.Settings.Profile;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.YouOweMeProject.R;
import com.example.YouOweMeProject.Settings.ProfileActivity;

public class EditProfileActivity extends AppCompatActivity {
    Button saveBtn;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_profile_editprofile);
        getSupportActionBar().setTitle("Edit Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.nav_back);

        saveBtn = (Button)findViewById(R.id.saveButton);
        builder = new AlertDialog.Builder(this);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (EditProfileActivity.this, ProfileActivity.class);
                startActivity(intent);

                Toast.makeText(EditProfileActivity.this, "Successfully Updated", Toast.LENGTH_SHORT).show();
                finish();

            }
        });

    }
}
