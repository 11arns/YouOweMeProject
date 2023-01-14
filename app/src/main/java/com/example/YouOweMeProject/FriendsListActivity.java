package com.example.YouOweMeProject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.YouOweMeProject.FriendsList.FriendActivity;
import com.example.YouOweMeProject.FriendsList.FriendsListInterface;
import com.example.YouOweMeProject.FriendsList.FriendsListModel;
import com.example.YouOweMeProject.FriendsList.Friendslist_RecyclerViewAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class FriendsListActivity extends AppCompatActivity implements FriendsListInterface {


    ArrayList<FriendsListModel> FriendListModels = new ArrayList<>();
    int[] friendimage = {R.drawable.aida, R.drawable.ayu, R.drawable.ain};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friendslist);

        //friends list recycler view
        RecyclerView recyclerView = findViewById(R.id.friendslist_recyclerview);
        setupfriendslistmodel();
        Friendslist_RecyclerViewAdapter adapter = new Friendslist_RecyclerViewAdapter(this
                , FriendListModels, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


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




    }


    private void setupfriendslistmodel(){
        String[] name = getResources().getStringArray(R.array.friendname);
        String[] debtstatus = getResources().getStringArray(R.array.debtstatus);
        String[] debtamount = getResources().getStringArray(R.array.debtamount);

        for(int i=0; i< name.length; i++ ){
            FriendListModels.add(new FriendsListModel(name[i],debtstatus[i],debtamount[i], friendimage[i] ));
        }
    }

    @Override
    public void onitemclick(int position) {
        Intent intent = new Intent( FriendsListActivity.this, FriendActivity.class);
        intent.putExtra("Friend Name", FriendListModels.get(position).getFriendname());
        intent.putExtra("Debt Status", FriendListModels.get(position).getDebtstatus());
        intent.putExtra("Debt Amount", FriendListModels.get(position).getDebtamount());
        intent.putExtra("Image", FriendListModels.get(position).getImage());

        startActivity(intent);

    }
}