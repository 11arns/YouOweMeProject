package com.example.YouOweMeProject.FriendsList;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.example.YouOweMeProject.R;
import android.view.View;
import android.widget.Button;

public class AddFriendActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friendslist_addfriend);
        getSupportActionBar().setTitle("Add Friend");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.nav_back);

        Button addfriendbtn = findViewById(R.id.btnsavenewfriend);
        addfriendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if username is correct(exist in the database), navigate to successfull activity page
                //if username is incorrect(not exist in the database, navigate to failed activity page


            }
        });

    }
}