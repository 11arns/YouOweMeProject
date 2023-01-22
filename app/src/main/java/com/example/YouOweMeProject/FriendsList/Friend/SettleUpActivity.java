package com.example.YouOweMeProject.FriendsList.Friend;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.YouOweMeProject.FriendsList.Friend.SettleUp.Confirmation;
import com.example.YouOweMeProject.FriendsList.FriendActivity;
import com.example.YouOweMeProject.FriendsListActivity;
import com.example.YouOweMeProject.Model.Expense;
import com.example.YouOweMeProject.Model.Friend;
import com.example.YouOweMeProject.R;
import com.example.YouOweMeProject.Settings.ProfileActivity;
import com.example.YouOweMeProject.SettingsActivity;
import com.example.YouOweMeProject.Welcome.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

public class SettleUpActivity extends AppCompatActivity {

    //Firebase
    FirebaseFirestore db;
    FirebaseAuth fbAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friendslist_friend_settleup);
        getSupportActionBar().setTitle("Settle Up");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Firebase
        fbAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        TextView type = findViewById(R.id.paidto);
        if(getIntent().getStringExtra("type").equals("youOwe")){
            type.setText("Paid to");
        } else{
            type.setText("Pays you");
        }


        TextView name = findViewById(R.id.name);
        name.setText(getIntent().getStringExtra("chosenName"));

        EditText amount = findViewById(R.id.editTextTextPersonName2);
        amount.setText(getIntent().getStringExtra("amount"));

        TextView nameOfExpense = findViewById(R.id.editTextTextPersonName3);
        nameOfExpense.setText(getIntent().getStringExtra("nameOfExpense"));


        Button Settleup = findViewById(R.id.btnsettleup);
        Settleup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("expenses").document(fbAuth.getUid()).collection("expenses")
                        .whereEqualTo("nameOfExpense", nameOfExpense.getText().toString())
                        .whereEqualTo("chosenName", name.getText().toString())
                        .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                    Log.d(TAG, "onSuccess: " + documentSnapshot.toString());

                                    Expense expense = documentSnapshot.toObject(Expense.class);

                                    Float expenseAmount = expense.getAmount() - Float.parseFloat(amount.getText().toString());

                                    if (expenseAmount >= 0) {
                                        if (expenseAmount > 0) {
                                            db.collection("expenses").document(fbAuth.getUid())
                                                    .collection("expenses").document(documentSnapshot.getId())
                                                    .update("amount", expenseAmount);
                                        } else {
                                            db.collection("expenses").document(fbAuth.getUid())
                                                    .collection("expenses").document(documentSnapshot.getId())
                                                    .delete();
                                        }

                                        //friend
                                        for(Friend friend: LoginActivity.friends.getFriends()){
                                            if(friend.getUsername().equals(name.getText().toString())){
                                                if(getIntent().getStringExtra("type").equals("youOwe")){
                                                    friend.setBalance(friend.getBalance() + Float.parseFloat(amount.getText().toString()));

                                                    //Update User data
                                                    LoginActivity.user.setCurrentYouOwe(LoginActivity.user.getCurrentYouOwe() - Float.parseFloat(amount.getText().toString()));
                                                    db.collection("user").document(fbAuth.getUid())
                                                            .set(LoginActivity.user);
                                                } else{
                                                    friend.setBalance(friend.getBalance() - Float.parseFloat(amount.getText().toString()));

                                                    //Update User data
                                                    LoginActivity.user.setCurrentOweYou(LoginActivity.user.getCurrentOweYou() - Float.parseFloat(amount.getText().toString()));
                                                    db.collection("user").document(fbAuth.getUid())
                                                            .set(LoginActivity.user);

                                                }

                                            }
                                        }

                                        db.collection("friends").document(fbAuth.getUid()).set(LoginActivity.friends);


                                        Intent intent = new Intent(SettleUpActivity.this, Confirmation.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(SettleUpActivity.this, "Settle Up Rejected. Paid to much.", Toast.LENGTH_SHORT).show();

                                        Intent intent = new Intent(SettleUpActivity.this, FriendsListActivity.class);
                                        startActivity(intent);
                                        break;
                                    }
                                }
                            }
                        });


                Log.d(TAG, "onClick: " + amount.getText() + " | "
                        + name.getText() + " | " + nameOfExpense.getText());

//                Intent intent = new Intent(SettleUpActivity.this, Confirmation.class);
//                startActivity(intent);
            }
        });

    }
}
