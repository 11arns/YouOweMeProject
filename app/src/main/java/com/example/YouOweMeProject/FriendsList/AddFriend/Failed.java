package com.example.YouOweMeProject.FriendsList.AddFriend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.YouOweMeProject.FriendsList.AddFriendActivity;
import com.example.YouOweMeProject.FriendsList.FriendActivity;
import com.example.YouOweMeProject.FriendsListActivity;
import com.example.YouOweMeProject.R;

public class Failed extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friendslist_addfriend_failed);

        Button tryagainbtn = findViewById(R.id.btntryagain);
        tryagainbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Failed.this, AddFriendActivity.class);
                startActivity(intent);
            }
        });

        Button cancelbtn = findViewById(R.id.btncancel);
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Failed.this, FriendsListActivity.class);
                startActivity(intent);
            }
        });
    }
}
