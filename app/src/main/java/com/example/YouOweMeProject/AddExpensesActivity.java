package com.example.YouOweMeProject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.YouOweMeProject.AddExpenses.AddAmount;
import com.example.YouOweMeProject.AddExpenses.OweYou;
import com.example.YouOweMeProject.Welcome.LoginActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AddExpensesActivity extends AppCompatActivity {
    Button btnyo;
    Button btnoy;
    LinearLayout linearLayout;
    TextView hiddenTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addexpenses);
        getSupportActionBar().setTitle("Add Expenses");

        btnyo = (Button)findViewById(R.id.btnyo);
        btnoy = (Button)findViewById(R.id.btnoy);

        btnyo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (AddExpensesActivity.this, AddAmount.class);
                startActivity(intent);
            }
        });

        btnoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (AddExpensesActivity.this, OweYou.class);
                startActivity(intent);
            }
        });

        //hide if there is no parents
        linearLayout = findViewById(R.id.linearLayout1);
        hiddenTextView = findViewById(R.id.empty_view3);


        linearLayout.setVisibility(View.GONE);
        hiddenTextView.setVisibility(View.VISIBLE);

        if(LoginActivity.friends.getFriends() != null){
            linearLayout.setVisibility(View.VISIBLE);
            hiddenTextView.setVisibility(View.GONE);
        }




        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.add_expenses);

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
}