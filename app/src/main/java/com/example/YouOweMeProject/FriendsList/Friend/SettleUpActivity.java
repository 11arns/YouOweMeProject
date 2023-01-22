package com.example.YouOweMeProject.FriendsList.Friend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.YouOweMeProject.FriendsList.Friend.SettleUp.Confirmation;
import com.example.YouOweMeProject.FriendsList.FriendActivity;
import com.example.YouOweMeProject.R;
import com.example.YouOweMeProject.Settings.ProfileActivity;
import com.example.YouOweMeProject.SettingsActivity;

import org.w3c.dom.Text;

public class SettleUpActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
       
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friendslist_friend_settleup);
        getSupportActionBar().setTitle("Settle Up");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView name = findViewById(R.id.name);
        name.setText(getIntent().getStringExtra("chosenName"));

        EditText amount = findViewById(R.id.editTextTextPersonName2);
        amount.setText(getIntent().getStringExtra("amount"));

        EditText nameOfExpense = findViewById(R.id.editTextTextPersonName3);
        nameOfExpense.setText(getIntent().getStringExtra("nameOfExpense"));

        Button Settleup = findViewById(R.id.btnsettleup);
        Settleup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettleUpActivity.this, Confirmation.class);
                startActivity(intent);
            }
        });


    }
}
