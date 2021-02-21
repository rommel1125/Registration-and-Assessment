package com.example.registrationandassessment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ceylonlabs.imageviewpopup.ImagePopup;

public class StudentActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView studId,studName,studCourse,studYear,studSem,studMode,studBal,eduBack,TuitionTXT,pera,mopWALA;
    private String studLNAME,studFNAME,studMNAME,studCOURSE,studYEAR,studSEM,studMODE,studBAL;
    private ImageView imageView;
    private int studID;
    private ImagePopup imagePopup;
    private Button logoutBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

//VIEW BY ID's
        studId = findViewById(R.id.studID);
        studName = findViewById(R.id.studNAME);
        studCourse = findViewById(R.id.studCOURSE);
        studYear = findViewById(R.id.studYEAR);
        studSem = findViewById(R.id.studSEM);
        studMode = findViewById(R.id.studMODE);
        studBal = findViewById(R.id.studBAL);
        imageView = findViewById(R.id.studSUBJECT);
        logoutBTN = findViewById(R.id.logoutBTN);
        eduBack = findViewById(R.id.eduBack);
        TuitionTXT = findViewById(R.id.TuitionTXT);
        pera = findViewById(R.id.pera);
        mopWALA = findViewById(R.id.mopWALA);


//GET INTENT
        Intent intent = getIntent();
        studID = intent.getIntExtra("ID",0);
        studLNAME = intent.getStringExtra("studLNAME");
        studFNAME = intent.getStringExtra("studFNAME");
        studMNAME = intent.getStringExtra("studMNAME");
        studCOURSE = intent.getStringExtra("studCOURSE");
        studYEAR = intent.getStringExtra("studYEAR");
        studSEM = intent.getStringExtra("studSEM");
        studMODE = intent.getStringExtra("studMODE");
        studBAL = intent.getStringExtra("studBAL");

//DISPLAYING RECEIVE INTENT TO TEXTVIEW
        studId.setText(String.valueOf(studID));
        studName.setText(studLNAME+", "+studFNAME+" "+studMNAME);
        studCourse.setText(studCOURSE);
        studYear.setText(studYEAR);
        studSem.setText(studSEM);
        studMode.setText(studMODE);
        studBal.setText(studBAL);
//CALLING SUBJECT METHOD
        subject();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagePopup.viewPopup();
            }
        });

        logoutBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(StudentActivity.this);
                alert.setTitle("LOGOUT")
                        .setMessage("Are you sure ?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(StudentActivity.this,MainActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create()
                        .show();
            }
        });
    }

//SUBJECT IMAGE VIEW
    public void subject(){
        imagePopup = new ImagePopup(StudentActivity.this);
        imagePopup.setWindowHeight(500);
        imagePopup.setWindowWidth(1000);
//BSIT
        if (studCourse.getText().toString().equals("BSIT - Bachelor of Science in Information Technology")){

            if (studYear.getText().toString().equals("1ST YEAR")){
                switch (studSem.getText().toString()){
                    case "1ST SEM":
                        imageView.setImageResource(R.drawable.bsit);
                        imagePopup.initiatePopup(imageView.getDrawable());
                        break;
                    case "2ND SEM":
                        imageView.setImageResource(R.drawable.bsit1st2nd);
                        imagePopup.initiatePopup(imageView.getDrawable());
                        break;
                }
            }
            else if (studYear.getText().toString().equals("2ND YEAR")){
                switch (studSem.getText().toString()){
                    case "1ST SEM":
                        imageView.setImageResource(R.drawable.bsit2nd1st);
                        imagePopup.initiatePopup(imageView.getDrawable());
                        break;
                    case "2ND SEM":
                        imageView.setImageResource(R.drawable.bsit2nd2nd);
                        imagePopup.initiatePopup(imageView.getDrawable());
                        break;
                }
            }
            else if (studYear.getText().toString().equals("3RD YEAR")){
                switch (studSem.getText().toString()){
                    case "1ST SEM":
                        imageView.setImageResource(R.drawable.bsit3rd1st);
                        imagePopup.initiatePopup(imageView.getDrawable());
                        break;
                    case "2ND SEM":
                        imageView.setImageResource(R.drawable.bsit3rd2nd);
                        imagePopup.initiatePopup(imageView.getDrawable());
                        break;
                }
            }
            else if (studYear.getText().toString().equals("4TH YEAR")){
                switch (studSem.getText().toString()){
                    case "1ST SEM":
                        imageView.setImageResource(R.drawable.bsit4th1st);
                        imagePopup.initiatePopup(imageView.getDrawable());
                        break;
                    case "2ND SEM":
                        imageView.setImageResource(R.drawable.bsit4th2nd);
                        imagePopup.initiatePopup(imageView.getDrawable());
                        break;
                }
            }
            else{
                //GRADUATE
                if(studYear.getText().toString().equals("GRADUATE")){
                    eduBack.setText("");
                    TuitionTXT.setText("");
                    pera.setText("");
                    mopWALA.setText("");
                    studMode.setText("");
                    studBal.setText("");
                }
            }

        }

//BSA
        else  if (studCourse.getText().toString().equals("BSA - Bachelor of Science in Accountancy")){
            if (studYear.getText().toString().equals("1ST YEAR")){
                switch (studSem.getText().toString()){
                    case "1ST SEM":
                        imageView.setImageResource(R.drawable.bsa);
                        imagePopup.initiatePopup(imageView.getDrawable());
                        break;
                    case "2ND SEM":
                        imageView.setImageResource(R.drawable.bsa1st2nd);
                        imagePopup.initiatePopup(imageView.getDrawable());
                        break;
                }
            }
            else if (studYear.getText().toString().equals("2ND YEAR")){
                switch (studSem.getText().toString()){
                    case "1ST SEM":
                        imageView.setImageResource(R.drawable.bsa2nd1st);
                        imagePopup.initiatePopup(imageView.getDrawable());
                        break;
                    case "2ND SEM":
                        imageView.setImageResource(R.drawable.bsa2nd2nd);
                        imagePopup.initiatePopup(imageView.getDrawable());
                        break;
                }
            }
            else if (studYear.getText().toString().equals("3RD YEAR")){
                switch (studSem.getText().toString()){
                    case "1ST SEM":
                        imageView.setImageResource(R.drawable.bsa3rd1st);
                        imagePopup.initiatePopup(imageView.getDrawable());
                        break;
                    case "2ND SEM":
                        imageView.setImageResource(R.drawable.bsa3rd2nd);
                        imagePopup.initiatePopup(imageView.getDrawable());
                        break;
                }
            }
            else if (studYear.getText().toString().equals("4TH YEAR")){
                switch (studSem.getText().toString()){
                    case "1ST SEM":
                        imageView.setImageResource(R.drawable.bsa4th1st);
                        imagePopup.initiatePopup(imageView.getDrawable());
                        break;
                    case "2ND SEM":
                        imageView.setImageResource(R.drawable.bsa4th2nd);
                        imagePopup.initiatePopup(imageView.getDrawable());
                        break;
                }
            }
            else{
                if(studYear.getText().toString().equals("GRADUATE")){
                    eduBack.setText("");
                    TuitionTXT.setText("");
                    pera.setText("");
                    mopWALA.setText("");
                    studMode.setText("");
                    studBal.setText("");
                }
            }
        }

//BSBA
        else if (studCourse.getText().toString().equals("BSBA - Bachelor of Science in Business Administration")){
            if (studYear.getText().toString().equals("1ST YEAR")){
                switch (studSem.getText().toString()){
                    case "1ST SEM":
                        imageView.setImageResource(R.drawable.bsba);
                        imagePopup.initiatePopup(imageView.getDrawable());
                        break;
                    case "2ND SEM":
                        imageView.setImageResource(R.drawable.bsba1st2nd);
                        imagePopup.initiatePopup(imageView.getDrawable());
                        break;
                }
            }
            else if (studYear.getText().toString().equals("2ND YEAR")){
                switch (studSem.getText().toString()){
                    case "1ST SEM":
                        imageView.setImageResource(R.drawable.bsba2nd1st);
                        imagePopup.initiatePopup(imageView.getDrawable());
                        break;
                    case "2ND SEM":
                        imageView.setImageResource(R.drawable.bsba2nd2nd);
                        imagePopup.initiatePopup(imageView.getDrawable());
                        break;
                }
            }
            else if (studYear.getText().toString().equals("3RD YEAR")){
                switch (studSem.getText().toString()){
                    case "1ST SEM":
                        imageView.setImageResource(R.drawable.bsba3rd1st);
                        imagePopup.initiatePopup(imageView.getDrawable());
                        break;
                    case "2ND SEM":
                        imageView.setImageResource(R.drawable.bsba3rd2nd);
                        imagePopup.initiatePopup(imageView.getDrawable());
                        break;
                }
            }
            else if (studYear.getText().toString().equals("4TH YEAR")){
                switch (studSem.getText().toString()){
                    case "1ST SEM":
                        imageView.setImageResource(R.drawable.bsba4th1st);
                        imagePopup.initiatePopup(imageView.getDrawable());
                        break;
                    case "2ND SEM":
                        imageView.setImageResource(R.drawable.bsba4th2nd);
                        imagePopup.initiatePopup(imageView.getDrawable());
                        break;
                }
            }
            else{
                //GRADUATE
                if(studYear.getText().toString().equals("GRADUATE")){
                    eduBack.setText("");
                    TuitionTXT.setText("");
                    pera.setText("");
                    mopWALA.setText("");
                    studMode.setText("");
                    studBal.setText("");
                }
            }
        }

//BEEd
        else if (studCourse.getText().toString().equals("BEEd - Bachelor of Elementary Education")){
            if (studYear.getText().toString().equals("1ST YEAR")){
                switch (studSem.getText().toString()){
                    case "1ST SEM":
                        imageView.setImageResource(R.drawable.beed);
                        imagePopup.initiatePopup(imageView.getDrawable());
                        break;
                    case "2ND SEM":
                        imageView.setImageResource(R.drawable.beed1st2nd);
                        imagePopup.initiatePopup(imageView.getDrawable());
                        break;
                }
            }
            else if (studYear.getText().toString().equals("2ND YEAR")){
                switch (studSem.getText().toString()){
                    case "1ST SEM":
                        imageView.setImageResource(R.drawable.beed2nd1st);
                        imagePopup.initiatePopup(imageView.getDrawable());
                        break;
                    case "2ND SEM":
                        imageView.setImageResource(R.drawable.beed2nd2nd);
                        imagePopup.initiatePopup(imageView.getDrawable());
                        break;
                }
            }
            else if (studYear.getText().toString().equals("3RD YEAR")){
                switch (studSem.getText().toString()){
                    case "1ST SEM":
                        imageView.setImageResource(R.drawable.beed3rd1st);
                        imagePopup.initiatePopup(imageView.getDrawable());
                        break;
                    case "2ND SEM":
                        imageView.setImageResource(R.drawable.beed3rd2nd);
                        imagePopup.initiatePopup(imageView.getDrawable());
                        break;
                }
            }
            else if (studYear.getText().toString().equals("4TH YEAR")){
                switch (studSem.getText().toString()){
                    case "1ST SEM":
                        imageView.setImageResource(R.drawable.beed4th1st);
                        imagePopup.initiatePopup(imageView.getDrawable());
                        break;
                    case "2ND SEM":
                        imageView.setImageResource(R.drawable.beed4th2nd);
                        imagePopup.initiatePopup(imageView.getDrawable());
                        break;
                }
            }
            else{
                //GRADUATE
                if(studYear.getText().toString().equals("GRADUATE")){
                    eduBack.setText("");
                    TuitionTXT.setText("");
                    pera.setText("");
                    mopWALA.setText("");
                    studMode.setText("");
                    studBal.setText("");
                }
            }
        }

//BSEd
        else if (studCourse.getText().toString().equals("BSEd - Bachelor of Secondary Education major in English")){
            if (studYear.getText().toString().equals("1ST YEAR")){
                switch (studSem.getText().toString()){
                    case "1ST SEM":
                        imageView.setImageResource(R.drawable.bsed);
                        imagePopup.initiatePopup(imageView.getDrawable());
                        break;
                    case "2ND SEM":
                        imageView.setImageResource(R.drawable.bsed1st2nd);
                        imagePopup.initiatePopup(imageView.getDrawable());
                        break;
                }
            }
            else if (studYear.getText().toString().equals("2ND YEAR")){
                switch (studSem.getText().toString()){
                    case "1ST SEM":
                        imageView.setImageResource(R.drawable.bsed2nd1st);
                        imagePopup.initiatePopup(imageView.getDrawable());
                        break;
                    case "2ND SEM":
                        imageView.setImageResource(R.drawable.bsed2nd2nd);
                        imagePopup.initiatePopup(imageView.getDrawable());
                        break;
                }
            }
            else if (studYear.getText().toString().equals("3RD YEAR")){
                switch (studSem.getText().toString()){
                    case "1ST SEM":
                        imageView.setImageResource(R.drawable.bsed3rd1st);
                        imagePopup.initiatePopup(imageView.getDrawable());
                        break;
                    case "2ND SEM":
                        imageView.setImageResource(R.drawable.bsed3rd2nd);
                        imagePopup.initiatePopup(imageView.getDrawable());
                        break;
                }
            }
            else if (studYear.getText().toString().equals("4TH YEAR")){
                switch (studSem.getText().toString()){
                    case "1ST SEM":
                        imageView.setImageResource(R.drawable.bsed4th1st);
                        imagePopup.initiatePopup(imageView.getDrawable());
                        break;
                    case "2ND SEM":
                        imageView.setImageResource(R.drawable.bsed4th2nd);
                        imagePopup.initiatePopup(imageView.getDrawable());
                        break;
                }
            }
            else{
                //GRADUATE
                if(studYear.getText().toString().equals("GRADUATE")){
                    eduBack.setText("");
                    TuitionTXT.setText("");
                    pera.setText("");
                    mopWALA.setText("");
                    studMode.setText("");
                    studBal.setText("");
                }
            }
        }
    }
}