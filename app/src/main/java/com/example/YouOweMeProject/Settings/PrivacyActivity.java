package com.example.YouOweMeProject.Settings;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.YouOweMeProject.R;


public class PrivacyActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_privacy);

        getSupportActionBar().setTitle("Privacy Policy");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.nav_back);
    }
}
