package com.example.YouOweMeProject.AddExpenses;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.YouOweMeProject.AddExpenses.Amount.ConfirmationPage;
import com.example.YouOweMeProject.AddExpensesActivity;
import com.example.YouOweMeProject.Model.Friend;
import com.example.YouOweMeProject.R;
import com.example.YouOweMeProject.Welcome.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class OweYou extends AppCompatActivity {
    Button btnsave;
    Spinner spinner;
    String [] friend = {"-","Izzati", "Ayu", "Aida", "Ahmad"};

    //Add variable
    EditText amount;
    EditText nameOfExpense;
    String chosenName;

    ArrayList<Friend> friends;

    String[] friendList;

    FirebaseAuth fbAuth;
    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addexpenses_oweyou);


        fbAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        friends = LoginActivity.friends.getFriends();
        friendList = new String[friends.size()];

        int i = 0;
        for(Friend friend: friends){
//            friendList.add(friend.getUsername());
            Log.d(TAG, "Friend: " + friend.getUsername().toString());
            friendList[i] = friend.getUsername();
            i++;
        }


        spinner=findViewById(R.id.spinner);
        getSupportActionBar().setTitle("Add Amount");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.nav_back);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(OweYou.this, android.R.layout.simple_spinner_item,friendList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                chosenName=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //set data
        amount = findViewById(R.id.amount);
        nameOfExpense = findViewById(R.id.expenses);


        btnsave = (Button)findViewById(R.id.btnsave);

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: " + chosenName);
                Log.d(TAG, "onClick: " + amount.getText().toString());
                Log.d(TAG, "onClick: " + nameOfExpense.getText().toString());

                Map<String, Object> expenseModel = new HashMap<>();
                expenseModel.put("chosenName", chosenName);
                expenseModel.put("amount", Float.parseFloat(amount.getText().toString()));
                expenseModel.put("nameOfExpense", nameOfExpense.getText().toString());
                expenseModel.put("type", "oweYou");

                db.collection("expenses").document(fbAuth.getUid()).collection("expenses")
                        .add(expenseModel).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                startActivity(new Intent(OweYou.this, ConfirmationPage.class));
                                Toast.makeText(OweYou.this, "Added Expense", Toast.LENGTH_SHORT).show();
                            }
                        });

                //Update Owe You
                LoginActivity.user.setCurrentOweYou(LoginActivity.user.getCurrentOweYou() + Float.parseFloat(amount.getText().toString()));
                LoginActivity.user.setTotalOweYou(LoginActivity.user.getTotalOweYou() + Float.parseFloat(amount.getText().toString()));


                db.collection("user").document(fbAuth.getUid())
                        .set(LoginActivity.user);


                //friend
                for(Friend friend: LoginActivity.friends.getFriends()){
                    if(friend.getUsername().equals(chosenName)){
                        friend.setBalance(friend.getBalance() + Float.parseFloat(amount.getText().toString()));
                    }
                }

                db.collection("friends").document(fbAuth.getUid()).set(LoginActivity.friends);

                //set new history
                Map<String, Object> newHistoryModel = new HashMap<>();
                newHistoryModel.put("date", new Timestamp(new Date()));
                newHistoryModel.put("description", chosenName + " owes you " +" RM" +  amount.getText().toString());

                db.collection("histories").document(fbAuth.getUid()).collection("history")
                        .add(newHistoryModel).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                Toast.makeText(OweYou.this, "You added histories", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });


    }

}
