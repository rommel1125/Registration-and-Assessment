package com.example.registrationandassessment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
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

    private EditText lnametxt,fnametxt,mnametxt,agetxt,birthplacetxt,dobtxt,addresstxt, contact, email, fatherName, fOccupation, motherName, mOccupation, elemName, elemAdd, highName, highAdd, subject;
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    private Button regBtn;
    TextView tryLang, dobTxt;
    private ImageView imageView;
    private ImagePopup imagePopup;
    private Toolbar toolbar;
    private Spinner spinner;
    public String lName, fName, mName, age, birthP, dob, address, ContactNum, EmailAdd, FatherName, FatherOccupation, MotherName, MotherOccupation, ElemName, ElemAdd, HighName, HighAdd, Subject;
    public String course = "Choose course";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
        elemName = findViewById(R.id.ElemTxt);
        elemAdd = findViewById(R.id.ElemAddTxt);
        highName = findViewById(R.id.HighTxt);
        highAdd = findViewById(R.id.HighAddTxt);

        spinner = findViewById(R.id.spinner);
        regBtn = findViewById(R.id.regBtn);
        imageView = findViewById(R.id.imageView);

        List<String> categories = new ArrayList<>();
        categories.add(0,"Choose course");
        categories.add("BSIT - Bachelor of Science in Information Technology");
        categories.add("BSA - Bachelor of Science in Accountancy");
        categories.add("BSBA - Bachelor of Science in Business Administration");
        categories.add("BEEd - Bachelor of Elementary Education");
        categories.add("BSEd - Bachelor of Secondary Education");


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
                    else if (course.equals("BSEd - Bachelor of Secondary Education")){
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
                lName = lnametxt.getText().toString();
                fName = fnametxt.getText().toString();
                mName = mnametxt.getText().toString();
                age = agetxt.getText().toString();
                dob = dobTxt.getText().toString();
                birthP = birthplacetxt.getText().toString();
                address = addresstxt.getText().toString();
                ContactNum = contact.getText().toString();
                EmailAdd = email.getText().toString();
                FatherName = fatherName.getText().toString();
                FatherOccupation = fOccupation.getText().toString();
                MotherName = motherName.getText().toString();
                MotherOccupation = mOccupation.getText().toString();
                ElemName = elemName.getText().toString();
                ElemAdd = elemAdd.getText().toString();
                HighName = highName.getText().toString();
                HighAdd = highAdd.getText().toString();

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
                                dobTxt.requestFocus();
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
                                                if (TextUtils.isEmpty(ElemName)) {
                                                    elemName.setError("Required");
                                                    elemName.requestFocus();
                                                }
                                                else{
                                                    if (TextUtils.isEmpty(ElemAdd)) {
                                                        elemAdd.setError("Required");
                                                        elemAdd.requestFocus();
                                                    }
                                                    else{
                                                        if (TextUtils.isEmpty(HighName)) {
                                                            highName.setError("Required");
                                                            highName.requestFocus();
                                                        }
                                                        else{
                                                            if (TextUtils.isEmpty(HighAdd)) {
                                                                highAdd.setError("Required");
                                                                highAdd.requestFocus();
                                                            }
                                                            else{
                                                                if (course == "Choose course") {
                                                                    Toast.makeText(RegistrationActivity.this,course,Toast.LENGTH_SHORT).show();
                                                                    TextView errorText = (TextView)spinner.getSelectedView();
                                                                    errorText.setError("");
                                                                    errorText.setTextColor(Color.RED);//just to highlight that this is an error
                                                                    errorText.setText("Required");
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