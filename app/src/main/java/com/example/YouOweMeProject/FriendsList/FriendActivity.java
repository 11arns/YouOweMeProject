package com.example.YouOweMeProject.FriendsList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.YouOweMeProject.FriendsList.Friend.SettleUpActivity;
import com.example.YouOweMeProject.R;

import java.util.ArrayList;

public class FriendActivity extends AppCompatActivity {
    ArrayList<friendmodel> friendmodels = new ArrayList<>();

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



        getSupportActionBar().setTitle("Friend Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.nav_back);

        Button SettleAllButton = findViewById(R.id.SettleAllButton);
        SettleAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FriendActivity.this, SettleUpActivity.class);
                startActivity(intent);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.friend_recyclerview);

        setUpFriendModel();
        friend_recyclerviewadapter adapter = new friend_recyclerviewadapter(this, friendmodels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



    }

    private void setUpFriendModel(){
        String[]  expensenameTV= getResources().getStringArray(R.array.expensename);
        String[]  expenseamountTV= getResources().getStringArray(R.array.expenseamount);
        String[]  expensestatusTV= getResources().getStringArray(R.array.expensestatus);

        for (int i=0; i<expensenameTV.length; i++){
            friendmodels.add(new friendmodel(expensenameTV[i], expensestatusTV[i], expenseamountTV[i]));
        }
    }
}
