package com.example.YouOweMeProject.AddExpenses.Amount;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.YouOweMeProject.R;

public class ConfirmationPage extends AppCompatActivity {
    Button btnsave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_confirmation);
        getSupportActionBar().setTitle("Confirmation");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.nav_back);
    }
}
