package com.example.YouOweMeProject.FriendsList;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.YouOweMeProject.Adapter.MyFriendAdapter;
import com.example.YouOweMeProject.Adapter.SelectListener;
import com.example.YouOweMeProject.Adapter.SelectListenerExpense;
import com.example.YouOweMeProject.FriendsList.Friend.SettleUpActivity;
import com.example.YouOweMeProject.Model.Expense;
import com.example.YouOweMeProject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class FriendActivity extends AppCompatActivity implements SelectListenerExpense {
//    ArrayList<friendmodel> friendmodels = new ArrayList<>();

    ArrayList<Expense> listOfExpense;
    RecyclerView recyclerView;
    MyFriendAdapter myAdapter;

    //need to disable these button and show text when there are no item
    TextView emptyView;
    //Button settleAll;
    ConstraintLayout constraintLayout;

    //Firebase
    FirebaseFirestore db;
    FirebaseAuth fbAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friendslist_friend);

        //passing data from friend list page
//        String friend_name = getIntent().getStringExtra("Friend Name");
//        String debt_status = getIntent().getStringExtra("Debt Status");
//        String debt_amount = getIntent().getStringExtra("Debt Amount");
//        int friend_image = getIntent().getIntExtra("Image", 0);

//        TextView friendnameTV = findViewById(R.id.TVfriendnamedetails);
//        TextView debtstatusTV = findViewById(R.id.TVdebtstatusdetails);
//        TextView debtamountTV = findViewById(R.id.TVdebtamountdetails);
//        ImageView friendIV = findViewById(R.id.IVfriend);
//
//        friendnameTV.setText(friend_name);
//        debtstatusTV.setText(debt_status);
//        debtamountTV.setText(debt_amount);
//        friendIV.setImageResource(friend_image);


//        RecyclerView recyclerView = findViewById(R.id.friend_recyclerview);
//        friend_recyclerviewadapter adapter = new friend_recyclerviewadapter(this, friendmodels);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView = findViewById(R.id.friend_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fbAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        listOfExpense = new ArrayList<Expense>();
        myAdapter = new MyFriendAdapter(FriendActivity.this, listOfExpense, this);

        recyclerView.setAdapter(myAdapter);

        //hide and show what need to be display
        emptyView = findViewById(R.id.empty_view2);
        //settleAll = findViewById(R.id.SettleAllButton);
        constraintLayout = findViewById(R.id.constraintLayout);

        recyclerView.setVisibility(View.GONE);
       // settleAll.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
        constraintLayout.setVisibility(View.GONE);

        //important for getting data
        EventChangeListener();

        //back button
        getSupportActionBar().setTitle("Friend Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.nav_back);

       /* Button SettleAllButton = findViewById(R.id.SettleAllButton);
        SettleAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FriendActivity.this, SettleUpActivity.class);
                startActivity(intent);
            }
        });*/
    }

    private void EventChangeListener(){
        db.collection("expenses").document(fbAuth.getUid()).collection("expenses")
                .whereEqualTo("chosenName", getIntent().getStringExtra("chosenName"))
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            if(!task.getResult().isEmpty()){
                                for(DocumentSnapshot documentSnapshot: task.getResult().getDocuments()){
                                    Expense expense = documentSnapshot.toObject(Expense.class);
                                    listOfExpense.add(expense);

                                    Log.d(TAG, "onComplete: " + expense.getNameOfExpense());

                                    myAdapter.notifyDataSetChanged();
                                }
                                //settleAll.setVisibility(View.VISIBLE);
                                recyclerView.setVisibility(View.VISIBLE);
                                emptyView.setVisibility(View.GONE);
                            }
                        }

                    }
                });
    }

    @Override
    public void onItemClicked(Expense myFriendsListAdapter) {
//        Toast.makeText(this, myFriendsListAdapter.getChosenName(), Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, myFriendsListAdapter.getAmount().toString(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(FriendActivity.this, SettleUpActivity.class);
        intent.putExtra("chosenName", myFriendsListAdapter.getChosenName());
        intent.putExtra("amount", myFriendsListAdapter.getAmount().toString());
        intent.putExtra("nameOfExpense", myFriendsListAdapter.getNameOfExpense());
        intent.putExtra("type", myFriendsListAdapter.getType());
        startActivity(intent);
    }

}
