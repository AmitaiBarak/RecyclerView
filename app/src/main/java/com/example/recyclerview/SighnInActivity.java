package com.example.recyclerview;

import static com.example.recyclerview.FirebaseRef.refAuth;
import static com.example.recyclerview.FirebaseRef.refUserItems;
import static com.example.recyclerview.MainActivity.*;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SighnInActivity extends AppCompatActivity {
    private Button LIB;
    private Button MoveToSighnUpButton;
    private EditText emailET;
    private EditText passwordET;
    private Switch rememberMeSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sighn_in);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        SharedPreferences sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        boolean rememberMe = sharedPref.getBoolean("RememberMe", false);

        if (!rememberMe) {
            refAuth.signOut();
        }
        else if (refAuth.getCurrentUser() != null) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

        emailET = findViewById(R.id.emailET);
        passwordET = findViewById(R.id.passwordET);

        rememberMeSwitch = findViewById(R.id.rememberMeSwitch);


        LIB = findViewById(R.id.LogInButton);
        LIB.setOnClickListener(view -> {
            String email = emailET.getText().toString();
            String password = passwordET.getText().toString();
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(SighnInActivity.this, "Please fill in email and password", Toast.LENGTH_SHORT).show();
                return;
            }
            else if(password.length()<6){
                Toast.makeText(SighnInActivity.this, "Password must me at least 6 characters", Toast.LENGTH_SHORT).show();
            }
            else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(SighnInActivity.this, "Please enter a valid email", Toast.LENGTH_LONG).show();
            }
            else{
                refAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user = refAuth.getCurrentUser();
                            Toast.makeText(SighnInActivity.this, user.getUid(), Toast.LENGTH_LONG).show();
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putBoolean("RememberMe", rememberMeSwitch.isChecked());
                            editor.apply();
                            Intent intent = new Intent(SighnInActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(SighnInActivity.this, "Error. Please Try Again Later.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        MoveToSighnUpButton = findViewById(R.id.MoveToSighnUpButton);
        MoveToSighnUpButton.setOnClickListener(view -> {
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.sighn_up_custome_dialog);
            dialog.show();
            Button SUB = dialog.findViewById(R.id.SighnUpButton);
            EditText dialogEmailET = dialog.findViewById(R.id.emailET);
            EditText dialogPasswordET = dialog.findViewById(R.id.passwordET);
            Switch dialogRememberMeSwitch = dialog.findViewById(R.id.rememberMeSwitch);


            SUB.setOnClickListener(v -> {
                String email = dialogEmailET.getText().toString();
                String password = dialogPasswordET.getText().toString();
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(SighnInActivity.this, "Please fill in email and password", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(password.length()<6){
                    Toast.makeText(SighnInActivity.this, "Password must me at least 6 characters", Toast.LENGTH_SHORT).show();
                }
                else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(SighnInActivity.this, "Please enter a valid email", Toast.LENGTH_LONG).show();
                }
                else{
                    refAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                FirebaseUser user = refAuth.getCurrentUser();
                                Toast.makeText(SighnInActivity.this, user.getUid(), Toast.LENGTH_LONG).show();
                                SharedPreferences.Editor editor = sharedPref.edit();
                                editor.putBoolean("RememberMe", rememberMeSwitch.isChecked());
                                editor.apply();
                                Intent intent = new Intent(SighnInActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(SighnInActivity.this, "Error. Please Try Again Later.", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            });
        });
    }
}