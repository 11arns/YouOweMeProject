package com.example.YouOweMeProject.AddExpenses;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.YouOweMeProject.AddExpenses.Amount.ConfirmationPage;
import com.example.YouOweMeProject.R;
import com.example.YouOweMeProject.Settings.Profile.EditProfileActivity;
import com.example.YouOweMeProject.Settings.ProfileActivity;

public class AddAmount extends AppCompatActivity {

    Button btnsave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_amount);
        getSupportActionBar().setTitle("Add Amount");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.nav_back);

        btnsave = (Button)findViewById(R.id.btnsave);

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (AddAmount.this, ConfirmationPage.class);
                startActivity(intent);
            }
        });
    }
}
