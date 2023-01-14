package com.example.YouOweMeProject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.YouOweMeProject.AddExpenses.AddAmount;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AddExpensesActivity extends AppCompatActivity {

    Button btnyo;
    Button btnoy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addexpenses);

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
                Intent intent = new Intent (AddExpensesActivity.this, AddAmount.class);
                startActivity(intent);
            }
        });

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