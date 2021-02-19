package com.example.registrationandassessment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ceylonlabs.imageviewpopup.ImagePopup;

import java.util.ArrayList;
import java.util.List;

public class AssessmentActivity extends AppCompatActivity {

    public String lName, fName, mName, age, birthP, dob, address, ContactNum, EmailAdd, FatherName, FatherOccupation, MotherName, MotherOccupation, JuniorName, JuniorAdd, SeniorName, SeniorAdd,course, StudID, year,balance, sem;
    public int student, selectedID;
    public double tuitionFee = 10000, semestral = 0, quarterly = 0, monthly = 0;
    private TextView studID,studName,studCourse,studYear, SQM, totalTxt,mop,SEM;
    private EditText password,ConfirmPassword;
    private Toolbar toolbar;
    private Button continueBtn;
    private ImageView studSub;
    private Spinner installChoice;
    private RadioGroup mopGroup;
    private RadioButton cash, ins;
    private ImagePopup imagePopup;
    private DatabaseHelper databaseHelper;

    public String ModeOfPayment,kindofinstallment;
    public String passwordInput, ConfirmPasswordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        databaseHelper = new DatabaseHelper(this);


        //FIND VIEW BY ID
        studID = findViewById(R.id.studID);
        studName = findViewById(R.id.studName);
        studCourse = findViewById(R.id.studCourse);
        studSub = findViewById(R.id.studSub);
        mopGroup = findViewById(R.id.mopGroup);
        continueBtn = findViewById(R.id.continueBtn);
        cash = findViewById(R.id.rbCash);
        ins = findViewById(R.id.rbInstallment);
        installChoice = findViewById(R.id.spinnerInstallment);
        SQM = findViewById(R.id.SQM);
        totalTxt = findViewById(R.id.totalTxt);
        password = findViewById(R.id.password);
        ConfirmPassword = findViewById(R.id.confirmPassword);
        mop = findViewById(R.id.mop);
        studYear = findViewById(R.id.studYear);
        SEM = findViewById(R.id.studSem);
        installChoice.setEnabled(false);

        //RECEIVE INTENTS
        Intent intent = getIntent();
        lName = intent.getStringExtra("lName");
        fName = intent.getStringExtra("fName");
        mName = intent.getStringExtra("mName");
        age = intent.getStringExtra("age");
        dob = intent.getStringExtra("dob");
        birthP = intent.getStringExtra("birthP");
        address = intent.getStringExtra("address");
        ContactNum = intent.getStringExtra("ContactNum");
        EmailAdd = intent.getStringExtra("EmailAdd");
        FatherName = intent.getStringExtra("FatherName");
        FatherOccupation = intent.getStringExtra("FatherOccupation");
        MotherName = intent.getStringExtra("MotherName");
        MotherOccupation = intent.getStringExtra("MotherOccupation");
        JuniorName = intent.getStringExtra("JuniorName");
        JuniorAdd = intent.getStringExtra("JuniorAdd");
        SeniorName = intent.getStringExtra("SeniorName");
        SeniorAdd = intent.getStringExtra("SeniorAdd");
        course = intent.getStringExtra("course");
        student = intent.getIntExtra("studID",0);
        year = studYear.getText().toString();

//RADIO GROUP
        mopGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbCash:
                        SQM.setText("CASH");
                        totalTxt.setText(String.valueOf("₱ "+tuitionFee));
                        mop.setError(null);
                        kindofinstallment = "";
                        ModeOfPayment = "CASH";
                        installChoice.setEnabled(false);
                        break;
                    case R.id.rbInstallment:
                        installChoice.setSelection(0);
                        mop.setError(null);
                        SQM.setText("");
                        ModeOfPayment = "INSTALLMENT";
                        kindofinstallment = "";
                        totalTxt.setText("");
                        installChoice.setEnabled(true);
                        break;
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
        installChoice.setAdapter(dataAdapter);

        installChoice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Choose Mode")){
                    kindofinstallment = "";
                    SQM.setText("");
                    totalTxt.setText("");
                }
                else{
                    if (parent.getItemAtPosition(position).equals("Semestral")){
                        kindofinstallment = "Semestral";
                        mop.setError(null);
                        SQM.setText("Semestral payment: ");
                        semestral = tuitionFee/2;
                        totalTxt.setText(String.format("₱ %.2f per semester",semestral));
                    }
                    else if (parent.getItemAtPosition(position).equals("Quarterly")){
                        kindofinstallment = "Quarterly";
                        mop.setError(null);
                        SQM.setText("Quarterly payment: ");
                        quarterly = tuitionFee/3;
                        totalTxt.setText(String.format("₱ %.2f",quarterly));
                    }
                    else if (parent.getItemAtPosition(position).equals("Monthly")){
                        kindofinstallment = "Monthly";
                        mop.setError(null);
                        SQM.setText("Monthly payment: ");
                        monthly = tuitionFee/10;
                        totalTxt.setText(String.format("₱ %.2f per month",monthly));
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

//DISPLAY THE NAMES AND STUDENT ID
        student = student + 1;
        StudID = String.valueOf(student);

        studID.setText(StudID);
        studName.setText(lName+", "+fName+" "+mName);
        studCourse.setText(course);

//IMAGE POPUP
        imagePopup = new ImagePopup(AssessmentActivity.this);
        imagePopup.setWindowHeight(500);
        imagePopup.setWindowWidth(1000);
        studSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagePopup.viewPopup();
            }
        });

        if (studCourse.getText().toString().equals("BSIT - Bachelor of Science in Information Technology")){
            studSub.setImageResource(R.drawable.bsit);
            imagePopup.initiatePopup(studSub.getDrawable());
        }
        else if(studCourse.getText().toString().equals("BSA - Bachelor of Science in Accountancy")){
            studSub.setImageResource(R.drawable.bsa);
            imagePopup.initiatePopup(studSub.getDrawable());
        }
        else if (studCourse.getText().toString().equals("BSBA - Bachelor of Science in Business Administration")){
            studSub.setImageResource(R.drawable.bsba);
            imagePopup.initiatePopup(studSub.getDrawable());
        }
        else if (studCourse.getText().toString().equals("BEEd - Bachelor of Elementary Education")){
            studSub.setImageResource(R.drawable.beed);
            imagePopup.initiatePopup(studSub.getDrawable());
        }
        else if (studCourse.getText().toString().equals("BSEd - Bachelor of Secondary Education major in English")){
            studSub.setImageResource(R.drawable.bsed);
            imagePopup.initiatePopup(studSub.getDrawable());
        }

//CONTINUE BUTTON
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertNewStudent();
            }
        });

    }
    private void insertNewStudent(){
        if (validatePassword() == false){
            validatePassword();
        }
        else{
            if (TextUtils.isEmpty(SQM.getText().toString())){
                mop.setError("Please choose mode of payment");
            }
            else{

                balance = totalTxt.getText().toString();
                sem = SEM.getText().toString();
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("Confirm Registration")
                        .setMessage("Please remember your \"Student ID\" and your \"Password\" you need them to login\n\n\nTo Continue click \"YES\"")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//INSERT DATA
                                Boolean checkinsert = databaseHelper.insertNewStudent(lName,fName,mName,age,dob,birthP,address,ContactNum,EmailAdd,FatherName,FatherOccupation,MotherName,MotherOccupation,JuniorName,JuniorAdd,SeniorName,SeniorAdd,course,ModeOfPayment,kindofinstallment,year,passwordInput,balance,sem);
                                if (checkinsert == true){
                                    Toast.makeText(AssessmentActivity.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(AssessmentActivity.this,MainActivity.class);
                                    startActivity(intent);
                                }
                                else {
                                    Toast.makeText(AssessmentActivity.this,"Something went wrong",Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create().show();
            }
        }
    }


    private boolean alertDialog(){

        return false;
    }
    private boolean validatePassword() {
        passwordInput = password.getText().toString().trim();
        ConfirmPasswordInput = ConfirmPassword.getText().toString().trim();
        if (passwordInput.isEmpty()) {
            password.requestFocus();
            password.setError("Required");
            return false;
        }  if (passwordInput.length()<5) {
            password.requestFocus();
            password.setError("Password must be at least 5 characters");
            return false;
        }
        if (!passwordInput.equals(ConfirmPasswordInput)) {
            ConfirmPassword.setText("");
            ConfirmPassword.requestFocus();
            ConfirmPassword.setError("Password Would Not be matched");
            return false;
        }else {
            return true;
        }
    }
}