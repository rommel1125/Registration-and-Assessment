package com.example.registrationandassessment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ValidationOldStudentActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText studIDTXT,studPASSTXT;
    private Button validateBTN;
    private DatabaseHelper databaseHelper;
    private String password;
    private int StudentID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validation_old_student);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//INSTANTIATION OF DB
        databaseHelper = new DatabaseHelper(this);

//FIND VIEW B ID's
        studIDTXT = findViewById(R.id.studIDTXT);
        studPASSTXT = findViewById(R.id.studPASSTXT);
        validateBTN = findViewById(R.id.validateBTN);

//VALIDATE BUTTON
        validateBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(studIDTXT.getText().toString())){
                    studIDTXT.setError("Cannot be empty");
                    studIDTXT.requestFocus();
                }
                else{
                    StudentID = Integer.parseInt(studIDTXT.getText().toString().trim());
                    if (TextUtils.isEmpty(studPASSTXT.getText().toString())){
                        studPASSTXT.setError("Cannot be empty");
                        studPASSTXT.requestFocus();
                    }
                    else{
                        password = studPASSTXT.getText().toString().trim();
                        if (databaseHelper.login(StudentID,password)){
                            //
                        }
                    }
                }
            }
        });
    }
}