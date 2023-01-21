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
import com.example.YouOweMeProject.Model.Expense;
import com.example.YouOweMeProject.Model.Expenses;
import com.example.YouOweMeProject.Model.Friend;
import com.example.YouOweMeProject.Model.Friends;
import com.example.YouOweMeProject.Model.User;
import com.example.YouOweMeProject.R;
import com.example.YouOweMeProject.Welcome.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AddAmount extends AppCompatActivity {
    Button btnsave;
    Spinner spinner;
    String [] friend = {"-","Izzati", "Ayu", "Aida", "Ahmad"};
//    String[] friends = LoginActivity.friends.getFriends();
    ArrayList<Friend> friends;

    String[] friendList;


    //Add variable
    EditText amount;
    EditText nameOfExpense;
    String chosenName;

    FirebaseAuth fbAuth;
    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addexpenses_addamount);

        Log.d(TAG, "AddAmount.java: " + friends);

        //add friends
        friends = LoginActivity.friends.getFriends();
        friendList = new String[friends.size()];

        int i = 0;
        for(Friend friend: friends){
//            friendList.add(friend.getUsername());
            Log.d(TAG, "Friend: " + friend.getUsername().toString());
            friendList[i] = friend.getUsername();
            i++;
        }
//        Log.d(TAG, "FriendList: " + friendList.toString());

        fbAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        spinner=findViewById(R.id.spinner);
        getSupportActionBar().setTitle("Add Amount");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.nav_back);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(AddAmount.this, android.R.layout.simple_spinner_item,friendList);
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
                expenseModel.put("type", "youOwe");

//                db.collection("expenses").document(fbAuth.getUid())
//                        .update("expense", FieldValue.arrayUnion(expenseModel));
//
//                db.collection("expenses").document(fbAuth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                        LoginActivity.expenses.setExpenses(task.getResult().toObject(Expenses.class).getExpenses());
//                        startActivity(new Intent(AddAmount.this, ConfirmationPage.class));
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        startActivity(new Intent(AddAmount.this, AddExpensesActivity.class));
//                        Toast.makeText(AddAmount.this, "Adding Expense Failed Try Again", Toast.LENGTH_SHORT).show();
//                    }
//                });

                db.collection("expenses").document(fbAuth.getUid()).collection("expenses")
                        .add(expenseModel).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                startActivity(new Intent(AddAmount.this, ConfirmationPage.class));
                                Toast.makeText(AddAmount.this, "Added Expense", Toast.LENGTH_SHORT).show();
                            }
                        });

//                db.collection("expense").document(fbAuth.getUid())
//                        .update("expense", FieldValue.arrayUnion(expenseModel)).addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void unused) {
//                                startActivity(new Intent(AddAmount.this, ConfirmationPage.class));
//                            }
//                        }).addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                startActivity(new Intent(AddAmount.this, AddExpensesActivity.class));
//                                Toast.makeText(AddAmount.this, "Adding Expense Failed Try Again", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//


                //get user object and then updated in one push
//                db.collection("user").document(fbAuth.getUid()).get()
//                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                    @Override
//                    public void onSuccess(DocumentSnapshot documentSnapshot) {
//
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//
//                    }
//                });

                //Update User data
                LoginActivity.user.setCurrentYouOwe(LoginActivity.user.getCurrentYouOwe() + Float.parseFloat(amount.getText().toString()));
                LoginActivity.user.setTotalYouOwe(LoginActivity.user.getTotalYouOwe() + Float.parseFloat(amount.getText().toString()));

                db.collection("user").document(fbAuth.getUid())
                        .set(LoginActivity.user);

                //update friends balance data
//                db.collection("friends").document(fbAuth.getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                    @Override
//                    public void onSuccess(DocumentSnapshot documentSnapshot) {
//                        for(Friend friend : documentSnapshot.toObject(Friends.class).getFriends()) {
//                            if(chosenName == friend.getUsername()){
//                                friend.setBalance(friend.getBalance() - Float.parseFloat(amount.getText().toString()));
//                            }
//                        }
//                    }
//                });

                for(Friend friend: LoginActivity.friends.getFriends()){
                    if(friend.getUsername().equals(chosenName)){
                        friend.setBalance(friend.getBalance() - Float.parseFloat(amount.getText().toString()));
                    }
                }

                db.collection("friends").document(fbAuth.getUid()).set(LoginActivity.friends);

//                //update history
//                Map<String, Object> historyModel = new HashMap<>();
//                historyModel.put("Description",
//                        "You Owe RM" + amount.getText().toString() + " to " + chosenName);
//                historyModel.put("Date", new Timestamp(new Date()));
//
//                db.collection("history").document(fbAuth.getUid())
//                        .update("historyArr", FieldValue.arrayUnion(historyModel));

                //set new history
                Map<String, Object> newHistoryModel = new HashMap<>();
                newHistoryModel.put("date", new Timestamp(new Date()));
                newHistoryModel.put("description", "You owe " + chosenName + " RM" +  amount.getText().toString());

                db.collection("histories").document(fbAuth.getUid()).collection("history")
                        .add(newHistoryModel).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                Toast.makeText(AddAmount.this, "You added histories", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });


    }
}
