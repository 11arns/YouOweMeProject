package com.example.YouOweMeProject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.YouOweMeProject.Welcome.LoginActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth fAuth;
    Button editImageButton;
    ImageView profilePic;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.home);

        //Set data
        TextView mainActivityUsernameDisplay = findViewById(R.id.textView17);
        mainActivityUsernameDisplay.setText(LoginActivity.user.getUsername());

        TextView totalOweYou = findViewById(R.id.txt4);
        TextView totalYouOwe = findViewById(R.id.txt5);
        TextView currentOweYou = findViewById(R.id.txt10);
        TextView currentYouOwe = findViewById(R.id.txt9);
        totalOweYou.setText(LoginActivity.user.getTotalOweYou().toString());
        totalYouOwe.setText(LoginActivity.user.getTotalYouOwe().toString());
        currentOweYou.setText(LoginActivity.user.getCurrentOweYou().toString());
        currentYouOwe.setText(LoginActivity.user.getCurrentYouOwe().toString());

        profilePic = findViewById(R.id.profileImg);
        editImageButton = findViewById(R.id.editImageButton);

        fAuth = FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        StorageReference profileRef = storageReference.child("users/"+fAuth.getCurrentUser().getUid()+"/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>(){
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(profilePic);
            }
        });



        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.home:
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