package com.example.registrationandassessment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText studID,studPass;
    Button loginBtn,newStudBtn,oldStudBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studID = findViewById(R.id.studID);
        studPass = findViewById(R.id.studPass);
        loginBtn = findViewById(R.id.loginBtn);
        newStudBtn = findViewById(R.id.newStudBtn);
        oldStudBtn = findViewById(R.id.oldStudBtn);
        newStudBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RegistrationActivity.class);
                startActivity(intent);
            }
        });

        oldStudBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ValidationOldStudentActivity.class);
                startActivity(intent);
            }
        });
    }
}