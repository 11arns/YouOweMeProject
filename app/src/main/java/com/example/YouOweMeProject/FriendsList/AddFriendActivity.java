package com.example.YouOweMeProject.FriendsList;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;

import com.example.YouOweMeProject.FriendsList.AddFriend.Failed;
import com.example.YouOweMeProject.FriendsList.AddFriend.Successful;
import com.example.YouOweMeProject.Model.Expense;
import com.example.YouOweMeProject.Model.Friend;
import com.example.YouOweMeProject.Model.Friends;
import com.example.YouOweMeProject.Model.User;
import com.example.YouOweMeProject.R;
import com.example.YouOweMeProject.Welcome.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddFriendActivity extends AppCompatActivity {

    FirebaseAuth fbAuth;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friendslist_addfriend);
        getSupportActionBar().setTitle("Add Friend");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.nav_back);

        //firebase setup
        db = FirebaseFirestore.getInstance();
        fbAuth = FirebaseAuth.getInstance();

        EditText addFriendName = findViewById(R.id.newname);

        Button addfriendbtn = findViewById(R.id.btnsavenewfriend);
        addfriendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("user").whereEqualTo("username", addFriendName.getText().toString())
                        .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if(task.isSuccessful()){
                                    if(task.getResult().isEmpty()){
                                        startActivity(new Intent(AddFriendActivity.this, Failed.class));
                                    } else {
//                                        startActivity(new Intent(AddFriendActivity.this, Successful.class));

                                        for(QueryDocumentSnapshot document : task.getResult()){
                                            if(document.toObject(User.class).getUsername().equals(addFriendName.getText().toString())){
                                                Map<String, Object> friendsModel = new HashMap<>();

                                                friendsModel.put("username", document.toObject(User.class).getUsername());
                                                friendsModel.put("balance", 0);
//                                                friendsModel.put("email", document.toObject(User.class).getEmail());

                                                db.collection("friends").document(fbAuth.getUid())
                                                        .update("friends", FieldValue.arrayUnion(friendsModel));

                                                db.collection("friends").document(fbAuth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                        LoginActivity.friends.setFriends(task.getResult().toObject(Friends.class).getFriends());

                                                        for(Friend friend: task.getResult().toObject(Friends.class).getFriends()){
                                                            Log.d(TAG, "friend: " + friend.getUsername());
                                                        }

                                                        Toast.makeText(AddFriendActivity.this, "Added Friend", Toast.LENGTH_SHORT).show();
                                                    }
                                                });

                                                startActivity(new Intent(AddFriendActivity.this, Successful.class));
                                            }
//                                        }
                                        }

                                    }

                                } else{
//                                    startActivity(new Intent(AddFriendActivity.this, Failed.class));
                                    Log.d(TAG, "On Complete:" + "User Not Found");
                                }
                            }
                        });

                //set friends
//                db.collection("friends").document(fbAuth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                        LoginActivity.friends.setFriends(task.getResult().toObject(Friends.class).getFriends());
//
//                        for(Friend friend: task.getResult().toObject(Friends.class).getFriends()){
//                            Log.d(TAG, "friend: " + friend.getUsername());
//                        }
//
//                        Toast.makeText(AddFriendActivity.this, "Added Friend", Toast.LENGTH_SHORT).show();
//                    }
//                });

                //if username is correct(exist in the database), navigate to successful activity page


                //if username is incorrect(not exist in the database, navigate to failed activity page


            }
        });

    }
}