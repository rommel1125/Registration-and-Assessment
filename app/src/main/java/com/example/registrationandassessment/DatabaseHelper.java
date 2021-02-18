package com.example.registrationandassessment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String db_name = "student_db";
    public static final String student_table_name = "student_tbl";
    public static final int version = 1;


    public DatabaseHelper(@Nullable Context context) {
        super(context, db_name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + student_table_name + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, LNAME TEXT, FNAME TEXT, MNAME TEXT, AGE TEXT, DOB TEXT, BIRTHPLACE TEXT, ADDRESS TEXT, CONTACT TEXT, EMAIL TEXT, FATHERNAME TEXT, FOCCUPATION TEXT, MOTHERNAME TEXT, MOCCUPATION TEXT, JUNIORNAME TEXT, JUNIORADD TEXT, SENIORNAME TEXT, SENIORADD TEXT, COURSE TEXT, MOP TEXT, KINDOFINSTALLMENT TEXT, YEAR TEXT,PASSWORD TEXT,BALANCE TEXT)");
        db.execSQL("INSERT INTO "+student_table_name+" (ID) VALUES (2021000)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + student_table_name);
    }

    public boolean insertNewStudent(String lName, String fName, String mName, String age, String dob, String birthP, String address, String contact, String email, String fatherName, String fOccupation, String motherName, String mOccupation, String juniorName, String juniorAdd, String seniorName, String seniorAdd, String course, String mop, String kindofinstallment, String year,String password,String balance) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("LNAME", lName);
        contentValues.put("FNAME", fName);
        contentValues.put("MNAME", mName);
        contentValues.put("AGE", age);
        contentValues.put("DOB", dob);
        contentValues.put("BIRTHPLACE", birthP);
        contentValues.put("ADDRESS", address);
        contentValues.put("CONTACT", contact);
        contentValues.put("EMAIL", email);
        contentValues.put("FATHERNAME", fatherName);
        contentValues.put("FOCCUPATION", fOccupation);
        contentValues.put("MOTHERNAME", motherName);
        contentValues.put("MOCCUPATION", mOccupation);
        contentValues.put("JUNIORNAME", juniorName);
        contentValues.put("JUNIORADD", juniorAdd);
        contentValues.put("SENIORNAME", seniorName);
        contentValues.put("SENIORADD", seniorAdd);
        contentValues.put("COURSE", course);
        contentValues.put("MOP",mop);
        contentValues.put("KINDOFINSTALLMENT",kindofinstallment);
        contentValues.put("YEAR",year);
        contentValues.put("PASSWORD",password);
        contentValues.put("BALANCE",balance);
        long result = db.insert(student_table_name, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getID(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+student_table_name+" WHERE ID = (SELECT MAX(ID) FROM student_tbl)",null);
        return cursor;
    }
}
