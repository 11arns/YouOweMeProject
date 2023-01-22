package com.example.YouOweMeProject;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.YouOweMeProject.Adapter.MyFriendsListAdapter;
import com.example.YouOweMeProject.Adapter.SelectListener;
import com.example.YouOweMeProject.FriendsList.AddFriendActivity;
import com.example.YouOweMeProject.FriendsList.FriendActivity;
import com.example.YouOweMeProject.Model.Expense;
import com.example.YouOweMeProject.Model.Friend;
import com.example.YouOweMeProject.Model.Friends;
import com.example.YouOweMeProject.Welcome.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class FriendsListActivity extends AppCompatActivity implements SelectListener {
//    ArrayList<FriendsListModel> FriendListModels = new ArrayList<>();
//    int[] friendimage = {R.drawable.aida, R.drawable.ayu, R.drawable.ain};

    ArrayList<Friend> list;
    RecyclerView recyclerView;
    MyFriendsListAdapter myAdapter;
    TextView emptyView;

    //Firebase
    FirebaseFirestore db;
    FirebaseAuth fbAuth;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friendslist);

        //friends list recycler view
//        RecyclerView recyclerView = findViewById(R.id.friendslist_recyclerview);
//        setupfriendslistmodel();
//        Friendslist_RecyclerViewAdapter adapter = new Friendslist_RecyclerViewAdapter(this
//                ,FriendListModels ,this);
//
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data...");
        progressDialog.show();

        recyclerView = findViewById(R.id.friendslist_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        emptyView = findViewById(R.id.empty_view);

        fbAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        list = new ArrayList<Friend>();
        myAdapter = new MyFriendsListAdapter(FriendsListActivity.this, list, this);

        recyclerView.setAdapter(myAdapter);

        recyclerView.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);

        //important for getting data
        EventChangeListener();


        //test
        tryToGetDataFromExpense();


        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.friend);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.friend:
                        return true;
                    case R.id.add_expenses:
                        startActivity(new Intent(getApplicationContext(),AddExpensesActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.history:
                        startActivity(new Intent(getApplicationContext(),HistoryActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.settings:
                        startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        Button AddFriendButton = findViewById(R.id.AddFriendButton);
        AddFriendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FriendsListActivity.this, AddFriendActivity.class);
                startActivity(intent);
            }
        });
    }

    private void EventChangeListener(){
        DocumentReference docRef = db.collection("friends").document(fbAuth.getUid());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.getResult().toObject(Friends.class).getFriends() != null){

                    recyclerView.setVisibility(View.VISIBLE);
                    emptyView.setVisibility(View.GONE);

                    for(Friend friend :task.getResult().toObject(Friends.class).getFriends()){
                        Log.d(TAG, "The issues is here: " + friend.getUsername());

                        list.add(friend);

                        progressDialog.dismiss();

                        myAdapter.notifyDataSetChanged();

                        Log.d(TAG, "On Success: " + friend.getUsername());
                    }
                } else{
                    progressDialog.dismiss();
                }
            }
        });
    }

    private void tryToGetDataFromExpense(){
        Log.d(TAG, "FriendsListActivity: " + LoginActivity.histories.getHistories());
    }


    @Override
    public void onItemClicked(Friend myFriendsListAdapter) {
//        Toast.makeText(this, myFriendsListAdapter.getUsername(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(FriendsListActivity.this, FriendActivity.class);
        intent.putExtra("chosenName", myFriendsListAdapter.getUsername());
        startActivity(intent);
    }
}