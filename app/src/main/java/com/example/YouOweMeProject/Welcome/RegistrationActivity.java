package com.example.YouOweMeProject.Welcome;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.YouOweMeProject.MainActivity;
import com.example.YouOweMeProject.Model.Expense;
import com.example.YouOweMeProject.Model.Friend;
import com.example.YouOweMeProject.Model.History;
import com.example.YouOweMeProject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {
    EditText username, email, phone, password, passwordConfirmation;
    Button btnRegistration;
    String emailPattern = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    String phonePattern= "^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$";
    ProgressDialog progressDialog;

    FirebaseAuth fbAuth;
    FirebaseUser myUser;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_registration);


        //to go to login page
        TextView clickableLoginTxt = findViewById(R.id.clickableLoginTxt);
        clickableLoginTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
            }
        });

        //this is important to setup FB
        db = FirebaseFirestore.getInstance();

        //Registration set up
        username = findViewById(R.id.ETUsername);
        email = findViewById(R.id.ETEmail);
        phone = findViewById(R.id.ETPhone);
        password = findViewById(R.id.ETPassword);
        passwordConfirmation = findViewById(R.id.ETPasswordConfirmation);
        btnRegistration = findViewById(R.id.btnRegistration);

        //loading pop up
        progressDialog = new ProgressDialog(this);

        //FB registration logic
        fbAuth = FirebaseAuth.getInstance();
        myUser = fbAuth.getCurrentUser();

        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PerformAuth();

//                Map<String, Object> user = new HashMap<>();
//                user.put("username", username.getText().toString());
//                user.put("email", email.getText().toString());
//                user.put("phone", phone.getText().toString());
//
//                db.collection("user").document(fbAuth.getUid()).set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        Toast.makeText(RegisterActivity.this, "Adding user successful", Toast.LENGTH_SHORT).show();
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(RegisterActivity.this, "Adding user failed", Toast.LENGTH_SHORT).show();
//                    }
//                });

                //send data to Firestore here
//                db.collection("user").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Toast.makeText(RegisterActivity.this, "Adding user successful", Toast.LENGTH_SHORT).show();
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(RegisterActivity.this, "Adding user failed", Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
        });
    }

    private void PerformAuth(){
        if(!email.getText().toString().matches(emailPattern)){
            email.setError("Enter correct Email");
        } else if(!phone.getText().toString().matches(phonePattern)){
          phone.setError("Phone format is wrong");
        } else if(password.getText().toString().isEmpty() || password.getText().toString().length() < 6){
            password.setError("Enter Proper Password");
        } else if (!password.getText().toString().equals(passwordConfirmation.getText().toString())){
            passwordConfirmation.setError("Password doesn't match");
        } else{
            progressDialog.setMessage("Please wait while registration...");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            fbAuth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressDialog.dismiss();
                        Toast.makeText(RegistrationActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();

                        //I need to create data for firestore
                        Map<String, Object> user = new HashMap<>();
                        user.put("username", username.getText().toString());
                        user.put("email", email.getText().toString());
                        user.put("phone", phone.getText().toString());
                        user.put("totalYouOwe", 0.0);
                        user.put("totalOweYou", 0.0);
                        user.put("currentYouOwe", 0.0);
                        user.put("currentOweYou", 0.0);

                        //send data to firestore
                        db.collection("user").document(fbAuth.getUid()).set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(RegistrationActivity.this, "Adding user successful", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(RegistrationActivity.this, "Adding user failed", Toast.LENGTH_SHORT).show();
                            }
                        });

                        //set up shell data base
                        Map<String, ArrayList<Expense>> expenseModel = new HashMap<>();
                        expenseModel.put("expenses", null);

                        db.collection("expenses").document(fbAuth.getUid()).set(expenseModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(RegistrationActivity.this, "Adding expenseModel successful", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(RegistrationActivity.this, "Adding expenseModel Failed", Toast.LENGTH_SHORT).show();
                            }
                        });

                        Map<String, ArrayList<Friend>> friendsModel = new HashMap<>();
                        friendsModel.put("friends", null);

                        db.collection("friends").document(fbAuth.getUid()).set(friendsModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(RegistrationActivity.this, "Adding friendsModel successful", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(RegistrationActivity.this, "Adding friendsModel Failed", Toast.LENGTH_SHORT).show();
                            }
                        });

                        //set up history
                        Map<String, ArrayList<History>> historyModel = new HashMap<>();
                        historyModel.put("historyArr", null);

                        db.collection("history").document(fbAuth.getUid()).set(historyModel);

                        //send user to login
                        startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));


                    } else{
                        progressDialog.dismiss();
                        Toast.makeText(RegistrationActivity.this, "" + task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

}
