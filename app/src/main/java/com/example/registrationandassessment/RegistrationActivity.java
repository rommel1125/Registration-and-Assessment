package com.example.registrationandassessment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ceylonlabs.imageviewpopup.ImagePopup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RegistrationActivity extends AppCompatActivity {

    private EditText lnametxt,fnametxt,mnametxt,agetxt,birthplacetxt,addresstxt, contact, email, fatherName, fOccupation, motherName, mOccupation, juniorName, juniorAdd, seniorName, seniorAdd, subject;
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    private Button regBtn;
    private TextView tryLang, dobTxt;
    private ImageView imageView;
    private ImagePopup imagePopup;
    private Toolbar toolbar;
    private Spinner spinner;
    public String lName, fName, mName, age, birthP, dob, address, ContactNum, EmailAdd, FatherName, FatherOccupation, MotherName, MotherOccupation, JuniorName, JuniorAdd, SeniorName, SeniorAdd, Subject;
    public String course = "Choose course";
    public int studID;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        databaseHelper =new DatabaseHelper(this);

        lnametxt = findViewById(R.id.lnametxt);
        fnametxt = findViewById(R.id.fnametxt);
        mnametxt = findViewById(R.id.mnametxt);
        agetxt = findViewById(R.id.agetxt);
        dobTxt = findViewById(R.id.dobtxt);
        birthplacetxt = findViewById(R.id.birthplacetxt);
        addresstxt = findViewById(R.id.addresstxt);
        contact = findViewById(R.id.contactNum);
        email = findViewById(R.id.emailAdd);
        fatherName = findViewById(R.id.fathersNameTxt);
        fOccupation = findViewById(R.id.fOccupationTxt);
        motherName = findViewById(R.id.mothersNameTxt);
        mOccupation = findViewById(R.id.mOccupationTxt);
        juniorName = findViewById(R.id.JuniorTxt);
        juniorAdd = findViewById(R.id.JuniorAddTxt);
        seniorName = findViewById(R.id.SeniorTxt);
        seniorAdd = findViewById(R.id.SeniorAddTxt);

        spinner = findViewById(R.id.spinner);
        regBtn = findViewById(R.id.regBtn);
        imageView = findViewById(R.id.imageView);

        List<String> categories = new ArrayList<>();
        categories.add(0,"Choose course");
        categories.add("BSIT - Bachelor of Science in Information Technology");
        categories.add("BSA - Bachelor of Science in Accountancy");
        categories.add("BSBA - Bachelor of Science in Business Administration");
        categories.add("BEEd - Bachelor of Elementary Education");
        categories.add("BSEd - Bachelor of Secondary Education major in English");


        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                imagePopup = new ImagePopup(RegistrationActivity.this);
                imagePopup.setWindowHeight(500);
                imagePopup.setWindowWidth(1000);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        imagePopup.viewPopup();
                    }
                });
                if (parent.getItemAtPosition(position).equals("Choose course")){
                    course = parent.getItemAtPosition(position).toString();
                    imageView.setImageResource(R.drawable.empty);
                    imagePopup.initiatePopup(imageView.getDrawable());
//                    TextView errorText = (TextView)spinner.getSelectedView();
//                    errorText.setError("");
//                    errorText.setTextColor(Color.RED);
//                    errorText.setText("Choose course");
                }
                else{
                    course = parent.getItemAtPosition(position).toString();
                    Toast.makeText(RegistrationActivity.this,course,Toast.LENGTH_SHORT).show();
                    if (course.equals("BSIT - Bachelor of Science in Information Technology")){
                        imageView.setImageResource(R.drawable.bsit);
                        imagePopup.initiatePopup(imageView.getDrawable());
                    }
                    else if (course.equals("BSA - Bachelor of Science in Accountancy")){
                        imageView.setImageResource(R.drawable.bsa);
                        imagePopup.initiatePopup(imageView.getDrawable());
                    }
                    else if (course.equals("BSBA - Bachelor of Science in Business Administration")){
                        imageView.setImageResource(R.drawable.bsba);
                        imagePopup.initiatePopup(imageView.getDrawable());
                    }
                    else if (course.equals("BEEd - Bachelor of Elementary Education")){
                        imageView.setImageResource(R.drawable.beed);
                        imagePopup.initiatePopup(imageView.getDrawable());
                    }
                    else if (course.equals("BSEd - Bachelor of Secondary Education major in English")){
                        imageView.setImageResource(R.drawable.bsed);
                        imagePopup.initiatePopup(imageView.getDrawable());
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
        dobTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(RegistrationActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        onDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month +1;
                String date = month + "/" +dayOfMonth+ "/" +year;
                dobTxt.setText(date);
                dobTxt.setError(null);
            }
        };
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lName = lnametxt.getText().toString().trim();
                fName = fnametxt.getText().toString().trim();
                mName = mnametxt.getText().toString().trim();
                age = agetxt.getText().toString().trim();
                dob = dobTxt.getText().toString().trim();
                birthP = birthplacetxt.getText().toString().trim();
                address = addresstxt.getText().toString().trim();
                ContactNum = contact.getText().toString().trim();
                EmailAdd = email.getText().toString().trim();
                FatherName = fatherName.getText().toString().trim();
                FatherOccupation = fOccupation.getText().toString().trim();
                MotherName = motherName.getText().toString().trim();
                MotherOccupation = mOccupation.getText().toString().trim();
                JuniorName = juniorName.getText().toString().trim();
                JuniorAdd = juniorAdd.getText().toString().trim();
                SeniorName = seniorName.getText().toString().trim();
                SeniorAdd = seniorAdd.getText().toString().trim();

                if (TextUtils.isEmpty(lName)){
                    lnametxt.setError("Required");
                    lnametxt.requestFocus();
                }
                else{
                    if (TextUtils.isEmpty(fName)) {
                        fnametxt.setError("Required");
                        fnametxt.requestFocus();
                    }
                    else{
                        if (TextUtils.isEmpty(age)){
                            agetxt.setError("Required");
                            agetxt.requestFocus();
                        }
                        else{
                            if (TextUtils.isEmpty(dob)) {
                                dobTxt.setError("Required");
                                Toast.makeText(RegistrationActivity.this,"Date of Birth is empty",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                if (TextUtils.isEmpty(birthP)) {
                                    birthplacetxt.setError("Required");
                                    birthplacetxt.requestFocus();
                                }
                                else{
                                    if (TextUtils.isEmpty(address)) {
                                        addresstxt.setError("Required");
                                        addresstxt.requestFocus();
                                    }
                                    else{
                                        if (TextUtils.isEmpty(ContactNum)) {
                                            contact.setError("Required");
                                            contact.requestFocus();
                                        }
                                        else{
                                            if (TextUtils.isEmpty(EmailAdd)) {
                                                email.setError("Required");
                                                email.requestFocus();
                                            }
                                            else{
                                                if (TextUtils.isEmpty(FatherName)) {
                                                    fatherName.setError("Required");
                                                    fatherName.requestFocus();
                                                }
                                                else{
                                                    if (TextUtils.isEmpty(FatherOccupation)) {
                                                        fOccupation.setError("Required");
                                                        fOccupation.requestFocus();
                                                    }
                                                    else{
                                                        if (TextUtils.isEmpty(MotherName)) {
                                                            motherName.setError("Required");
                                                            motherName.requestFocus();
                                                        }
                                                        else{
                                                            if (TextUtils.isEmpty(MotherOccupation)) {
                                                                mOccupation.setError("Required");
                                                                mOccupation.requestFocus();
                                                            }
                                                            else{
                                                                if (TextUtils.isEmpty(JuniorName)) {
                                                                    juniorName.setError("Required");
                                                                    juniorName.requestFocus();
                                                                }
                                                                else{
                                                                    if (TextUtils.isEmpty(JuniorAdd)) {
                                                                        juniorAdd.setError("Required");
                                                                        juniorAdd.requestFocus();
                                                                    }
                                                                    else{
                                                                        if (TextUtils.isEmpty(SeniorName)) {
                                                                            seniorName.setError("Required");
                                                                            seniorName.requestFocus();
                                                                        }
                                                                        else{
                                                                            if (TextUtils.isEmpty(SeniorAdd)) {
                                                                                seniorAdd.setError("Required");
                                                                                seniorAdd.requestFocus();
                                                                            }
                                                                            else{
                                                                                if (course == "Choose course") {
                                                                                    Toast.makeText(RegistrationActivity.this,course,Toast.LENGTH_SHORT).show();
                                                                                    TextView errorText = (TextView)spinner.getSelectedView();
                                                                                    errorText.setError("");
                                                                                    errorText.setTextColor(Color.RED);//just to highlight that this is an error
                                                                                    errorText.setText("Required");
                                                                                }
                                                                                else {
                                                                                    Intent myIntent = new Intent(RegistrationActivity.this, AssessmentActivity.class);
                                                                                    myIntent.putExtra("lName", lName);
                                                                                    myIntent.putExtra("fName", fName);
                                                                                    myIntent.putExtra("mName",mName);
                                                                                    myIntent.putExtra("age",age);
                                                                                    myIntent.putExtra("dob",dob);
                                                                                    myIntent.putExtra("birthP",birthP);
                                                                                    myIntent.putExtra("address",address);
                                                                                    myIntent.putExtra("ContactNum",ContactNum);
                                                                                    myIntent.putExtra("EmailAdd",EmailAdd);
                                                                                    myIntent.putExtra("FatherName",FatherName);
                                                                                    myIntent.putExtra("FatherOccupation",FatherOccupation);
                                                                                    myIntent.putExtra("MotherName",MotherName);
                                                                                    myIntent.putExtra("MotherOccupation",MotherOccupation);
                                                                                    myIntent.putExtra("JuniorName",JuniorName);
                                                                                    myIntent.putExtra("JuniorAdd",JuniorAdd);
                                                                                    myIntent.putExtra("SeniorName",SeniorName);
                                                                                    myIntent.putExtra("SeniorAdd",SeniorAdd);
                                                                                    myIntent.putExtra("course",course);

                                                                                    Cursor cursor = databaseHelper.getID();
                                                                                    while (cursor.moveToNext()) {
                                                                                        studID = cursor.getInt(0);
                                                                                    }
                                                                                    myIntent.putExtra("studID",studID);

                                                                                    startActivity(myIntent);
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
    }
}