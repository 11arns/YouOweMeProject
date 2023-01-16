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
import android.widget.TextView;
import android.widget.Toast;

import com.example.YouOweMeProject.Adapter.MyFriendsListAdapter;
import com.example.YouOweMeProject.Adapter.MyHisotryAdapter;
import com.example.YouOweMeProject.Model.Friend;
import com.example.YouOweMeProject.Model.Friends;
import com.example.YouOweMeProject.Model.Histories;
import com.example.YouOweMeProject.Model.History;
import com.example.YouOweMeProject.Welcome.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    ArrayList<History> listOfHistory;
    RecyclerView recyclerView;
    MyHisotryAdapter myAdapter;
    TextView emptyView;

    //Firebase
    FirebaseFirestore db;
    FirebaseAuth fbAuth;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data...");
        progressDialog.show();

        recyclerView = findViewById(R.id.recyclerviewHistory);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        emptyView = findViewById(R.id.empty_view4);

        fbAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        listOfHistory = new ArrayList<History>();
        myAdapter = new MyHisotryAdapter(HistoryActivity.this, listOfHistory);

        recyclerView.setAdapter(myAdapter);

        recyclerView.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);


        //important for getting data
        EventChangeListener();

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.history);

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
                        startActivity(new Intent(getApplicationContext(), FriendsListActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.add_expenses:
                        startActivity(new Intent(getApplicationContext(),AddExpensesActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.history:
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

    private void EventChangeListener(){
        db.collection("history").document(fbAuth.getUid())
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.getResult().toObject(Histories.class).getHistories() != null){
                            recyclerView.setVisibility(View.VISIBLE);
                            emptyView.setVisibility(View.GONE);

                            for(History history :task.getResult().toObject(Histories.class).getHistories()){
                                Log.d(TAG, "The issues is here: " + history.getDescription());

                                listOfHistory.add(history);

                                myAdapter.notifyDataSetChanged();
                                progressDialog.dismiss();
                            }
                        } else{
                            Toast.makeText(HistoryActivity.this, "No history", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }
                });
    }
}
