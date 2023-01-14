package com.example.YouOweMeProject.FriendsList.AddFriend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.YouOweMeProject.FriendsList.Friend.SettleUpActivity;
import com.example.YouOweMeProject.FriendsList.FriendActivity;
import com.example.YouOweMeProject.R;

public class Successful extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friendslist_addfriend_successful);

        Button successfulbtn = findViewById(R.id.btnsuccessful);
        successfulbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Successful.this, FriendActivity.class);
                startActivity(intent);
            }
        });
    }
}
