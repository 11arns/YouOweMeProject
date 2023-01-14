package com.example.YouOweMeProject.FriendsList;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.YouOweMeProject.R;

public class FriendActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friendslist_friend);

        //passing data from friend list page
        String friend_name = getIntent().getStringExtra("Friend Name");
        String debt_status = getIntent().getStringExtra("Debt Status");
        String debt_amount = getIntent().getStringExtra("Debt Amount");
        int friend_image = getIntent().getIntExtra("Image", 0);

        TextView friendnameTV = findViewById(R.id.TVfriendnamedetails);
        TextView debtstatusTV = findViewById(R.id.TVdebtstatusdetails);
        TextView debtamountTV = findViewById(R.id.TVdebtamountdetails);
        ImageView friendIV = findViewById(R.id.IVfriend);

        friendnameTV.setText(friend_name);
        debtstatusTV.setText(debt_status);
        debtamountTV.setText(debt_amount);
        friendIV.setImageResource(friend_image);



        getSupportActionBar().setTitle("Your Friend Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.nav_back);

    }
}
