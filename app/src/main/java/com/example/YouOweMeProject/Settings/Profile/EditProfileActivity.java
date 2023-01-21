package com.example.YouOweMeProject.Settings.Profile;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.YouOweMeProject.R;
import com.example.YouOweMeProject.Settings.ProfileActivity;
import com.example.YouOweMeProject.Welcome.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class EditProfileActivity extends AppCompatActivity {
    Button saveBtn;
    AlertDialog.Builder builder;

    EditText username, phone, email;
    FirebaseAuth fbAuth;
    FirebaseUser myUser;
    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_profile_editprofile);
        getSupportActionBar().setTitle("Edit Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.nav_back);

        saveBtn = (Button)findViewById(R.id.saveButton);
        builder = new AlertDialog.Builder(this);

        //select the editText
        username = findViewById(R.id.edit_username);
        phone = findViewById(R.id.editPhone);
        email = findViewById(R.id.editEmail);

        //FB setup
        fbAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();




        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performUpdate();

            }
        });
    }

    private void performUpdate(){
        LoginActivity.user.setPhone(phone.getText().toString());
        LoginActivity.user.setUsername(username.getText().toString());

        db.collection("user").document(fbAuth.getUid()).set(LoginActivity.user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent (EditProfileActivity.this, ProfileActivity.class);
                    startActivity(intent);

                    Toast.makeText(EditProfileActivity.this, "Successfully Updated", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });


    }

}
