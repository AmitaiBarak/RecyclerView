package com.example.recyclerview;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SighnInActivity extends AppCompatActivity {
    private Button LIB;
    private Button MoveToSighnUpButton;
    private EditText usernameET;
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
        usernameET = findViewById(R.id.usernameET);
        passwordET = findViewById(R.id.passwordET);
        rememberMeSwitch = findViewById(R.id.rememberMeSwitch);


        LIB = findViewById(R.id.LogInButton);
        LIB.setOnClickListener(view -> {
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("username", usernameET.getText().toString());
            editor.apply();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        MoveToSighnUpButton = findViewById(R.id.MoveToSighnUpButton);
        MoveToSighnUpButton.setOnClickListener(view -> {
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.sighn_up_custome_dialog);
            dialog.show();
            Button SUB = dialog.findViewById(R.id.SighnUpButton);
            SUB.setOnClickListener(v -> {
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("username", usernameET.getText().toString());
                editor.apply();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            });
        });
    }
}