package com.example.YouOweMeProject.FriendsList.Friend.SettleUp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.YouOweMeProject.FriendsList.Friend.SettleUpActivity;
import com.example.YouOweMeProject.FriendsList.FriendActivity;
import com.example.YouOweMeProject.FriendsListActivity;
import com.example.YouOweMeProject.MainActivity;
import com.example.YouOweMeProject.R;

public class Confirmation extends AppCompatActivity {

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.friendslist_friend_settleup_confirmation);
            getSupportActionBar().hide();
            //sounds
            MediaPlayer mp = MediaPlayer.create(this, R.raw.sound);
            mp.start();

            Button Successfull = findViewById(R.id.btnsuccessful);
            Successfull.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Confirmation.this, FriendsListActivity.class);
                    startActivity(intent);
                }
            });


        }

}
