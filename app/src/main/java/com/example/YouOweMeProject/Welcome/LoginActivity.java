package com.example.YouOweMeProject.Welcome;

import static android.content.ContentValues.TAG;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.YouOweMeProject.MainActivity;
import com.example.YouOweMeProject.Model.User;
import com.example.YouOweMeProject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {
    EditText email, password;
    Button btnLogin;
    ProgressDialog progressDialog;

    FirebaseAuth fbAuth;
    FirebaseUser myUser;
    FirebaseFirestore db;

    public static User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_login);

        //to go to login page
        TextView clickableSignUpTxt = findViewById(R.id.clickableSignUpTxt);
        clickableSignUpTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
            }
        });

        //Login set up
        email = findViewById(R.id.ETEmailLogin);
        password = findViewById(R.id.ETPasswordLogin);
        btnLogin = findViewById(R.id.btnLoginLogin);

        //loading pop up
        progressDialog = new ProgressDialog(this);

        //FB registration logic
        db = FirebaseFirestore.getInstance();
        fbAuth = FirebaseAuth.getInstance();
        myUser = fbAuth.getCurrentUser();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PerformAuth();
            }
        });
    }

    private void PerformAuth() {
        progressDialog.setMessage("Please wait while login...");
        progressDialog.setTitle("Login");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        fbAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {


            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    //get data from user and set it on one big object
                    //one for profile, one for friends list, one for history
                    getData();

                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

                    //send user to homeActivity
//                    startActivity(new Intent(LoginActivity.this, MainActivity.class));

                } else{
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "" + task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getData(){
        db.collection("user").document(fbAuth.getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                user = documentSnapshot.toObject(User.class);
                Log.d(TAG, "onSuccess: " + user.getEmail());

                Toast.makeText(LoginActivity.this, "Success Fetching Data", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(LoginActivity.this, "Failed Fetching Data", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, LoginActivity.class));
            }
        });
    }

}
