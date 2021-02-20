package com.example.registrationandassessment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText studID,studPass;
    private Button loginBtn,newStudBtn,oldStudBtn;
    private DatabaseHelper databaseHelper;
    private String password, studLNAME,studFNAME,studMNAME,studCOURSE,studYEAR,studMODE,studKIND,studBAL,studSEM;
    private int StudentID,ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studID = findViewById(R.id.studID);
        studPass = findViewById(R.id.studPass);
        loginBtn = findViewById(R.id.loginBtn);
        newStudBtn = findViewById(R.id.newStudBtn);
        oldStudBtn = findViewById(R.id.oldStudBtn);
        databaseHelper = new DatabaseHelper(this);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(studID.getText().toString())){
                    studID.setError("Cannot be empty");
                    studID.requestFocus();
                }
                else{
                    StudentID = Integer.parseInt(studID.getText().toString().trim());
                    if (TextUtils.isEmpty(studPass.getText().toString())){
                        studPass.setError("Cannot be empty");
                        studPass.requestFocus();
                    }
                    else{
                        password = studPass.getText().toString().trim();
                        if(databaseHelper.login(StudentID,password)){
                            Intent intent = new Intent(MainActivity.this,StudentActivity.class);
                            Cursor cursor = databaseHelper.getData(StudentID);
                            while (cursor.moveToNext()){
                                ID = cursor.getInt(0);
                                studLNAME = cursor.getString(1);
                                studFNAME = cursor.getString(2);
                                studMNAME = cursor.getString(3);
                                studCOURSE = cursor.getString(18);
                                studMODE = cursor.getString(19);
                                studKIND = cursor.getString(20);
                                studYEAR = cursor.getString(21);
                                studBAL = cursor.getString(23);
                                studSEM = cursor.getString(24);
                            }
                            intent.putExtra("ID",ID);
                            intent.putExtra("studLNAME",studLNAME);
                            intent.putExtra("studFNAME",studFNAME);
                            intent.putExtra("studMNAME",studMNAME);
                            intent.putExtra("studCOURSE",studCOURSE);
                            intent.putExtra("studYEAR",studYEAR);
                            intent.putExtra("studMODE",studMODE);
                            intent.putExtra("studKIND",studKIND);
                            intent.putExtra("studBAL",studBAL);
                            intent.putExtra("studSEM",studSEM);
                            startActivity(intent);
                        }
                        else {
                            studPass.setText("");
                            studID.setError("Student does not exists");
                            studID.requestFocus();
                        }
                    }
                }

            }
        });

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