package com.example.YouOweMeProject.AddExpenses.Amount;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.YouOweMeProject.AddExpenses.AddAmount;
import com.example.YouOweMeProject.FriendsListActivity;
import com.example.YouOweMeProject.MainActivity;
import com.example.YouOweMeProject.R;

public class ConfirmationPage extends AppCompatActivity {
    Button btndone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addexpenses_add_amount_confirmation);
        getSupportActionBar().hide();

        btndone = (Button)findViewById(R.id.btndone);

        btndone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (ConfirmationPage.this, FriendsListActivity.class);
                startActivity(intent);
            }
        });
    }
}
