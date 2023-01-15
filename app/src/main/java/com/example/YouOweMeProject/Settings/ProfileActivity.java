package com.example.YouOweMeProject.Settings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.YouOweMeProject.R;
import com.example.YouOweMeProject.Settings.Profile.EditProfileActivity;
import com.example.YouOweMeProject.Welcome.LoginActivity;

public class ProfileActivity extends AppCompatActivity{
    Button editProfileBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_profile);

        getSupportActionBar().setTitle("My Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.nav_back);

        //set data
        TextView profileActivityUsernameDisplay = findViewById(R.id.Username);
        TextView profileActivityUsernameBoxDisplay = findViewById(R.id.username);
        TextView profileActivityPhoneBoxDisplay = findViewById(R.id.phone);
        TextView profileActivityEmailBoxDisplay = findViewById(R.id.email);

        profileActivityUsernameDisplay.setText(LoginActivity.user.getUsername());
        profileActivityUsernameBoxDisplay.setText(LoginActivity.user.getUsername());
        profileActivityEmailBoxDisplay.setText(LoginActivity.user.getEmail());
        profileActivityPhoneBoxDisplay.setText(LoginActivity.user.getPhone());


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
