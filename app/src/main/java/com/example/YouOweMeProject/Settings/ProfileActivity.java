package com.example.YouOweMeProject.Settings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.example.YouOweMeProject.R;
import com.example.YouOweMeProject.Settings.Profile.EditProfileActivity;

public class ProfileActivity extends AppCompatActivity{


    Button editProfileBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_profile);

        getSupportActionBar().setTitle("My Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.nav_back);

        editProfileBtn = (Button)findViewById(R.id.editButton);

        editProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (ProfileActivity.this, EditProfileActivity.class);
                startActivity(intent);
            }
        });
    }
}
