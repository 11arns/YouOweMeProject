package com.example.YouOweMeProject.AddExpenses.Amount;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.YouOweMeProject.AddExpenses.AddAmount;
import com.example.YouOweMeProject.MainActivity;
import com.example.YouOweMeProject.R;

public class ConfirmationPage extends AppCompatActivity {
    Button btndone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addexpenses_add_amount_confirmation);
        getSupportActionBar().setTitle("Confirmation");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.nav_back);

        btndone = (Button)findViewById(R.id.btndone);

        btndone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (ConfirmationPage.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
