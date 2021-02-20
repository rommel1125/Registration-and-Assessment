package com.example.registrationandassessment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
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

//OLD STUDENT VARIABLES
    private int oldID;
    private String oldLNAME,oldFNAME,oldMNAME,oldCOURSE,oldMODE,oldKIND,oldYEAR,oldBAL,oldSEM;

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
                            Intent intent = new Intent(ValidationOldStudentActivity.this,OldStudentAssessmentActivity.class);
                            Cursor cursor = databaseHelper.getData(StudentID);
                 //GETTING DATA OF OLD STUDENT
                            while(cursor.moveToNext()){
                                oldID = cursor.getInt(0);
                                oldLNAME = cursor.getString(1);
                                oldFNAME = cursor.getString(2);
                                oldMNAME = cursor.getString(3);
                                oldCOURSE = cursor.getString(18);
                                oldYEAR = cursor.getString(21);
                                oldSEM = cursor.getString(24);
                            }
                            intent.putExtra("oldID",oldID);
                            intent.putExtra("oldLNAME",oldLNAME);
                            intent.putExtra("oldFNAME",oldFNAME);
                            intent.putExtra("oldMNAME",oldMNAME);
                            intent.putExtra("oldCOURSE",oldCOURSE);
                            intent.putExtra("oldYEAR",oldYEAR);
                            intent.putExtra("oldSEM",oldSEM);
                            startActivity(intent);
                        }
                        else {
                            studPASSTXT.setText("");
                            studIDTXT.setError("Student does not exists");
                            studIDTXT.requestFocus();
                        }
                    }
                }
            }
        });
    }
}