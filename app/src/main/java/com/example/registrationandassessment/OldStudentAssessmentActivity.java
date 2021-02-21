package com.example.registrationandassessment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ceylonlabs.imageviewpopup.ImagePopup;

import java.util.ArrayList;
import java.util.List;

public class OldStudentAssessmentActivity extends AppCompatActivity {



//OLD STUDENT VARIABLES
    private int oldID;
    private String oldLNAME,oldFNAME,oldMNAME,oldCOURSE,oldYEAR,oldSEM;

//
    private String MODEofPAYMENT,KINDofINSTALLMENT,balance,sem;
    private double tuitionFee = 10000, semestral = 0, quarterly = 0, monthly = 0;

//XML's
    private TextView oldNAMETXT, oldIDTXT, oldCOURSETXT, oldYEARTXT, oldSEMTXT,newYEARTXT,newSEMTXT,SemQuaMon, totalTXT,mopTXT;
    private ImageView imageView;
    private RadioGroup mopGROUP;
    private Spinner spinner;
    private ImagePopup imagePopup;
    private Button continueBTN;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_student_assessment);

        databaseHelper = new DatabaseHelper(this);
//FIND VIEW BY ID
        oldNAMETXT = findViewById(R.id.oldNAME);
        oldIDTXT = findViewById(R.id.oldID);
        oldCOURSETXT = findViewById(R.id.oldCOURSE);
        oldYEARTXT = findViewById(R.id.oldYEAR);
        oldSEMTXT = findViewById(R.id.oldSEM);
        newYEARTXT = findViewById(R.id.newYEAR);
        newSEMTXT = findViewById(R.id.newSEM);
        imageView = findViewById(R.id.newSUBJECT);
        mopGROUP = findViewById(R.id.mopGroup);
        spinner = findViewById(R.id.spinnerInstallment);
        SemQuaMon = findViewById(R.id.SemQuaMon);
        totalTXT = findViewById(R.id.totalTXT);
        mopTXT = findViewById(R.id.mopTXT);
        continueBTN = findViewById(R.id.continueBTN);
        spinner.setEnabled(false);

//INTENT RECEIVE
        Intent intent = getIntent();
        oldID = intent.getIntExtra("oldID",0);
        oldLNAME = intent.getStringExtra("oldLNAME");
        oldFNAME = intent.getStringExtra("oldFNAME");
        oldMNAME = intent.getStringExtra("oldMNAME");
        oldCOURSE = intent.getStringExtra("oldCOURSE");
        oldYEAR = intent.getStringExtra("oldYEAR");
        oldSEM = intent.getStringExtra("oldSEM");

//POSTING DATA FROM DB TO TEXT VIEWS
        oldIDTXT.setText(String.valueOf(oldID));
        oldNAMETXT.setText(oldLNAME+", "+oldFNAME+" "+oldMNAME);
        oldCOURSETXT.setText(oldCOURSE);
        oldYEARTXT.setText(oldYEAR);
        oldSEMTXT.setText(oldSEM);


//CALLING NEW YEAR/SEM METHOD
        newYS();
//CALLING SUBJECT() METHOD
        subject();

//CLICK LISTENER FOR IMAGE POP UP
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagePopup.viewPopup();
            }
        });

//RADIO GROUP
        mopGROUP.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbCASH:
                        SemQuaMon.setText("CASH");
                        spinner.setEnabled(false);
                        MODEofPAYMENT = "CASH";
                        KINDofINSTALLMENT = "";
                        totalTXT.setText(String.valueOf("₱ "+tuitionFee));
                        mopTXT.setError(null);
                        break;
                    case R.id.rbINSTALLMENT:
                        spinner.setSelection(0);
                        mopTXT.setError(null);
                        SemQuaMon.setText("");
                        MODEofPAYMENT = "INSTALLMENT";
                        KINDofINSTALLMENT = "";
                        totalTXT.setText("");
                        spinner.setEnabled(true);

                }
            }
        });

//LIST OF ITEM FOR SPINNER
        List<String> items = new ArrayList<>();
        items.add(0,"Choose Mode");
        items.add("Semestral");
        items.add("Quarterly");
        items.add("Monthly");

        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,items);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Choose Mode")){
                    KINDofINSTALLMENT = "";
                    SemQuaMon.setText("");
                    totalTXT.setText("");
                }
                else{
                    if (parent.getItemAtPosition(position).equals("Semestral")){
                        KINDofINSTALLMENT = "Semestral";
                        mopTXT.setError(null);
                        SemQuaMon.setText("Semestral payment: ");
                        semestral = tuitionFee/2;
                        totalTXT.setText(String.format("₱ %.2f per semester",semestral));
                    }
                    else if (parent.getItemAtPosition(position).equals("Quarterly")){
                        KINDofINSTALLMENT = "Quarterly";
                        mopTXT.setError(null);
                        SemQuaMon.setText("Quarterly payment: ");
                        quarterly = tuitionFee/3;
                        totalTXT.setText(String.format("₱ %.2f",quarterly));
                    }
                    else if (parent.getItemAtPosition(position).equals("Monthly")){
                        KINDofINSTALLMENT = "Monthly";
                        mopTXT.setError(null);
                        SemQuaMon.setText("Monthly payment: ");
                        monthly = tuitionFee/10;
                        totalTXT.setText(String.format("₱ %.2f per month",monthly));
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        continueBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });
    }

//UPDATE METHOD
    private void update(){
        if (TextUtils.isEmpty(SemQuaMon.getText().toString())){
            mopTXT.setError("Please choose mode of payment");
            mopTXT.requestFocus();
        }
        else {
            balance = totalTXT.getText().toString();
            sem = newSEMTXT.getText().toString();
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Confirm Registration");
            alert.setMessage("Please review your payment !\nTo continue click \"YES\"");
            alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    Boolean checkupdate = databaseHelper.updateNewStudent(oldIDTXT.getText().toString(),MODEofPAYMENT,KINDofINSTALLMENT,newYEARTXT.getText().toString(),totalTXT.getText().toString(),newSEMTXT.getText().toString());
                    if (checkupdate == true){
                        Toast.makeText(OldStudentAssessmentActivity.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(OldStudentAssessmentActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(OldStudentAssessmentActivity.this,"Something went wrong",Toast.LENGTH_SHORT).show();
                    }
                }
            });
            alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).create().show();
        }
    }

//NEW YEAR/SEM
    public void newYS(){
        if (oldCOURSETXT.getText().toString().equals("BSIT - Bachelor of Science in Information Technology")){

            if (oldYEARTXT.getText().toString().equals("1ST YEAR")){
                switch (oldSEMTXT.getText().toString()){
                    case "1ST SEM":
                        newYEARTXT.setText("1ST YEAR");
                        newSEMTXT.setText("2ND SEM");
                        break;
                    case "2ND SEM":
                        newYEARTXT.setText("2ND YEAR");
                        newSEMTXT.setText("1ST SEM");
                        break;
                }

            }
            else if (oldYEARTXT.getText().toString().equals("2ND YEAR")){
                switch (oldSEMTXT.getText().toString()){
                    case "1ST SEM":
                        newYEARTXT.setText("2ND YEAR");
                        newSEMTXT.setText("2ND SEM");
                        break;
                    case "2ND SEM":
                        newYEARTXT.setText("3RD YEAR");
                        newSEMTXT.setText("1ST SEM");
                        break;
                }
            }
            else if (oldYEARTXT.getText().toString().equals("3RD YEAR")){
                switch (oldSEMTXT.getText().toString()){
                    case "1ST SEM":
                        newYEARTXT.setText("3RD YEAR");
                        newSEMTXT.setText("2ND SEM");
                        break;
                    case "2ND SEM":
                        newYEARTXT.setText("4TH YEAR");
                        newSEMTXT.setText("1ST SEM");
                        break;
                }
            }
            else if (oldYEARTXT.getText().toString().equals("4TH YEAR")){
                switch (oldSEMTXT.getText().toString()){
                    case "1ST SEM":
                        newYEARTXT.setText("4TH YEAR");
                        newSEMTXT.setText("2ND SEM");
                        break;
                    case "2ND SEM":
                        newYEARTXT.setText("GRADUATE");
                        newSEMTXT.setText("");
                        break;
                }
            }
            else {
                imageView.setImageResource(R.drawable.empty);
                mopGROUP.setEnabled(false);
            }

        }
        else if (oldCOURSETXT.getText().toString().equals("BSA - Bachelor of Science in Accountancy")){

            if (oldYEARTXT.getText().toString().equals("1ST YEAR")){
                switch (oldSEMTXT.getText().toString()){
                    case "1ST SEM":
                        newYEARTXT.setText("1ST YEAR");
                        newSEMTXT.setText("2ND SEM");
                        break;
                    case "2ND SEM":
                        newYEARTXT.setText("2ND YEAR");
                        newSEMTXT.setText("1ST SEM");
                        break;
                }

            }
            else if (oldYEARTXT.getText().toString().equals("2ND YEAR")){
                switch (oldSEMTXT.getText().toString()){
                    case "1ST SEM":
                        newYEARTXT.setText("2ND YEAR");
                        newSEMTXT.setText("2ND SEM");
                        break;
                    case "2ND SEM":
                        newYEARTXT.setText("3RD YEAR");
                        newSEMTXT.setText("1ST SEM");
                        break;
                }
            }
            else if (oldYEARTXT.getText().toString().equals("3RD YEAR")){
                switch (oldSEMTXT.getText().toString()){
                    case "1ST SEM":
                        newYEARTXT.setText("3RD YEAR");
                        newSEMTXT.setText("2ND SEM");
                        break;
                    case "2ND SEM":
                        newYEARTXT.setText("4TH YEAR");
                        newSEMTXT.setText("1ST SEM");
                        break;
                }
            }
            else if (oldYEARTXT.getText().toString().equals("4TH YEAR")){
                switch (oldSEMTXT.getText().toString()){
                    case "1ST SEM":
                        newYEARTXT.setText("4TH YEAR");
                        newSEMTXT.setText("2ND SEM");
                        break;
                    case "2ND SEM":
                        newYEARTXT.setText("GRADUATE");
                        newSEMTXT.setText("");
                        break;
                }
            }

        }
        else if (oldCOURSETXT.getText().toString().equals("BSBA - Bachelor of Science in Business Administration")){

            if (oldYEARTXT.getText().toString().equals("1ST YEAR")){
                switch (oldSEMTXT.getText().toString()){
                    case "1ST SEM":
                        newYEARTXT.setText("1ST YEAR");
                        newSEMTXT.setText("2ND SEM");
                        break;
                    case "2ND SEM":
                        newYEARTXT.setText("2ND YEAR");
                        newSEMTXT.setText("1ST SEM");
                        break;
                }

            }
            else if (oldYEARTXT.getText().toString().equals("2ND YEAR")){
                switch (oldSEMTXT.getText().toString()){
                    case "1ST SEM":
                        newYEARTXT.setText("2ND YEAR");
                        newSEMTXT.setText("2ND SEM");
                        break;
                    case "2ND SEM":
                        newYEARTXT.setText("3RD YEAR");
                        newSEMTXT.setText("1ST SEM");
                        break;
                }
            }
            else if (oldYEARTXT.getText().toString().equals("3RD YEAR")){
                switch (oldSEMTXT.getText().toString()){
                    case "1ST SEM":
                        newYEARTXT.setText("3RD YEAR");
                        newSEMTXT.setText("2ND SEM");
                        break;
                    case "2ND SEM":
                        newYEARTXT.setText("4TH YEAR");
                        newSEMTXT.setText("1ST SEM");
                        break;
                }
            }
            else if (oldYEARTXT.getText().toString().equals("4TH YEAR")){
                switch (oldSEMTXT.getText().toString()){
                    case "1ST SEM":
                        newYEARTXT.setText("4TH YEAR");
                        newSEMTXT.setText("2ND SEM");
                        break;
                    case "2ND SEM":
                        newYEARTXT.setText("GRADUATE");
                        newSEMTXT.setText("");
                        break;
                }
            }

        }
        else if (oldCOURSETXT.getText().toString().equals("BEEd - Bachelor of Elementary Education")){

            if (oldYEARTXT.getText().toString().equals("1ST YEAR")){
                switch (oldSEMTXT.getText().toString()){
                    case "1ST SEM":
                        newYEARTXT.setText("1ST YEAR");
                        newSEMTXT.setText("2ND SEM");
                        break;
                    case "2ND SEM":
                        newYEARTXT.setText("2ND YEAR");
                        newSEMTXT.setText("1ST SEM");
                        break;
                }

            }
            else if (oldYEARTXT.getText().toString().equals("2ND YEAR")){
                switch (oldSEMTXT.getText().toString()){
                    case "1ST SEM":
                        newYEARTXT.setText("2ND YEAR");
                        newSEMTXT.setText("2ND SEM");
                        break;
                    case "2ND SEM":
                        newYEARTXT.setText("3RD YEAR");
                        newSEMTXT.setText("1ST SEM");
                        break;
                }
            }
            else if (oldYEARTXT.getText().toString().equals("3RD YEAR")){
                switch (oldSEMTXT.getText().toString()){
                    case "1ST SEM":
                        newYEARTXT.setText("3RD YEAR");
                        newSEMTXT.setText("2ND SEM");
                        break;
                    case "2ND SEM":
                        newYEARTXT.setText("4TH YEAR");
                        newSEMTXT.setText("1ST SEM");
                        break;
                }
            }
            else if (oldYEARTXT.getText().toString().equals("4TH YEAR")){
                switch (oldSEMTXT.getText().toString()){
                    case "1ST SEM":
                        newYEARTXT.setText("4TH YEAR");
                        newSEMTXT.setText("2ND SEM");
                        break;
                    case "2ND SEM":
                        newYEARTXT.setText("GRADUATE");
                        newSEMTXT.setText("");
                        break;
                }
            }

        }
        else if (oldCOURSETXT.getText().toString().equals("BSEd - Bachelor of Secondary Education major in English")){

            if (oldYEARTXT.getText().toString().equals("1ST YEAR")){
                switch (oldSEMTXT.getText().toString()){
                    case "1ST SEM":
                        newYEARTXT.setText("1ST YEAR");
                        newSEMTXT.setText("2ND SEM");
                        break;
                    case "2ND SEM":
                        newYEARTXT.setText("2ND YEAR");
                        newSEMTXT.setText("1ST SEM");
                        break;
                }

            }
            else if (oldYEARTXT.getText().toString().equals("2ND YEAR")){
                switch (oldSEMTXT.getText().toString()){
                    case "1ST SEM":
                        newYEARTXT.setText("2ND YEAR");
                        newSEMTXT.setText("2ND SEM");
                        break;
                    case "2ND SEM":
                        newYEARTXT.setText("3RD YEAR");
                        newSEMTXT.setText("1ST SEM");
                        break;
                }
            }
            else if (oldYEARTXT.getText().toString().equals("3RD YEAR")){
                switch (oldSEMTXT.getText().toString()){
                    case "1ST SEM":
                        newYEARTXT.setText("3RD YEAR");
                        newSEMTXT.setText("2ND SEM");
                        break;
                    case "2ND SEM":
                        newYEARTXT.setText("4TH YEAR");
                        newSEMTXT.setText("1ST SEM");
                        break;
                }
            }
            else if (oldYEARTXT.getText().toString().equals("4TH YEAR")){
                switch (oldSEMTXT.getText().toString()){
                    case "1ST SEM":
                        newYEARTXT.setText("4TH YEAR");
                        newSEMTXT.setText("2ND SEM");
                        break;
                    case "2ND SEM":
                        newYEARTXT.setText("GRADUATE");
                        newSEMTXT.setText("");
                        break;
                }
            }
        }
    }



//SUBJECT IMAGE VIEW
    public void subject(){
        imagePopup = new ImagePopup(OldStudentAssessmentActivity.this);
        imagePopup.setWindowHeight(500);
        imagePopup.setWindowWidth(1000);
//BSIT
        if (oldCOURSETXT.getText().toString().equals("BSIT - Bachelor of Science in Information Technology")){

            if (newYEARTXT.getText().toString().equals("1ST YEAR")){
                switch (newSEMTXT.getText().toString()){
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
            else if (newYEARTXT.getText().toString().equals("2ND YEAR")){
                switch (newSEMTXT.getText().toString()){
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
            else if (newYEARTXT.getText().toString().equals("3RD YEAR")){
                switch (newSEMTXT.getText().toString()){
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
            else if (newYEARTXT.getText().toString().equals("4TH YEAR")){
                switch (newSEMTXT.getText().toString()){
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
            }

        }

//BSA
        else  if (oldCOURSETXT.getText().toString().equals("BSA - Bachelor of Science in Accountancy")){
            if (newYEARTXT.getText().toString().equals("1ST YEAR")){
                switch (newSEMTXT.getText().toString()){
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
            else if (newYEARTXT.getText().toString().equals("2ND YEAR")){
                switch (newSEMTXT.getText().toString()){
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
            else if (newYEARTXT.getText().toString().equals("3RD YEAR")){
                switch (newSEMTXT.getText().toString()){
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
            else if (newYEARTXT.getText().toString().equals("4TH YEAR")){
                switch (newSEMTXT.getText().toString()){
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
                //GRADUATE
            }
        }

//BSBA
        else if (oldCOURSETXT.getText().toString().equals("BSBA - Bachelor of Science in Business Administration")){
            if (newYEARTXT.getText().toString().equals("1ST YEAR")){
                switch (newSEMTXT.getText().toString()){
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
            else if (newYEARTXT.getText().toString().equals("2ND YEAR")){
                switch (newSEMTXT.getText().toString()){
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
            else if (newYEARTXT.getText().toString().equals("3RD YEAR")){
                switch (newSEMTXT.getText().toString()){
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
            else if (newYEARTXT.getText().toString().equals("4TH YEAR")){
                switch (newSEMTXT.getText().toString()){
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
            }
        }

//BEEd
        else if (oldCOURSETXT.getText().toString().equals("BEEd - Bachelor of Elementary Education")){
            if (newYEARTXT.getText().toString().equals("1ST YEAR")){
                switch (newSEMTXT.getText().toString()){
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
            else if (newYEARTXT.getText().toString().equals("2ND YEAR")){
                switch (newSEMTXT.getText().toString()){
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
            else if (newYEARTXT.getText().toString().equals("3RD YEAR")){
                switch (newSEMTXT.getText().toString()){
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
            else if (newYEARTXT.getText().toString().equals("4TH YEAR")){
                switch (newSEMTXT.getText().toString()){
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
            }
        }

//BSEd
        else if (oldCOURSETXT.getText().toString().equals("BSEd - Bachelor of Secondary Education major in English")){
            if (newYEARTXT.getText().toString().equals("1ST YEAR")){
                switch (newSEMTXT.getText().toString()){
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
            else if (newYEARTXT.getText().toString().equals("2ND YEAR")){
                switch (newSEMTXT.getText().toString()){
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
            else if (newYEARTXT.getText().toString().equals("3RD YEAR")){
                switch (newSEMTXT.getText().toString()){
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
            else if (newYEARTXT.getText().toString().equals("4TH YEAR")){
                switch (newSEMTXT.getText().toString()){
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
            }
        }
    }
}