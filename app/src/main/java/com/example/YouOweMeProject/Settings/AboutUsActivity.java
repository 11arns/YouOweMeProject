package com.example.YouOweMeProject.Settings;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.YouOweMeProject.R;


public class AboutUsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_about_us);

        getSupportActionBar().setTitle("About Us");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.nav_back);
    }
}

