package com.example.registrationandassessment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RegistrationActivity extends AppCompatActivity {

    private EditText lnametxt,fnametxt,mnametxt,agetxt,birthplacetxt,dobtxt,coursetxt;
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    private Toolbar toolbar;
    private Spinner spinner;

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
        birthplacetxt = findViewById(R.id.birthplacetxt);
        dobtxt = findViewById(R.id.dobtxt);
        spinner = findViewById(R.id.spinner);
        coursetxt = findViewById(R.id.courseTxt);

        List<String> categories = new ArrayList<>();
        categories.add(0,"Choose course");
        categories.add("BSIT - Bachelor of Science in Information Technology");
        categories.add("FOOD TECH - Food and technology");
        categories.add("BSBM - Bachelor of Science IN business management");
        categories.add("BSCS- Bachelor of Science in Computer Science");
        categories.add("BSECE - Bachelor of Science in Electronics and Communication Engineer");

        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Choose course")){
                    coursetxt.setText("");
                }
                else{
                    String item = parent.getItemAtPosition(position).toString();
                    coursetxt.setText(item);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        dobtxt.setOnClickListener(new View.OnClickListener() {
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
                dobtxt.setText(date);
            }
        };
    }
}