package com.example.YouOweMeProject.AddExpenses;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.YouOweMeProject.AddExpenses.Amount.ConfirmationPage;
import com.example.YouOweMeProject.R;

public class AddAmount extends AppCompatActivity {

    Button btnsave;
    Spinner spinner;
    String [] friend = {"-","Izzati", "Ayu", "Aida", "Ahmad"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addexpenses_addamount);
        spinner=findViewById(R.id.spinner);
        getSupportActionBar().setTitle("Add Amount");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.nav_back);

        btnsave = (Button)findViewById(R.id.btnsave);

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (AddAmount.this, ConfirmationPage.class);
                startActivity(intent);
            }
        });

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(AddAmount.this, android.R.layout.simple_spinner_item,friend);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value=parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
