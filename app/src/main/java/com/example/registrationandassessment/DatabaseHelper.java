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
        db.execSQL("CREATE TABLE " + student_table_name + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, LNAME TEXT, FNAME TEXT, MNAME TEXT, AGE TEXT, DOB TEXT, BIRTHPLACE TEXT, ADDRESS TEXT, CONTACT TEXT, EMAIL TEXT, FATHERNAME TEXT, FOCCUPATION TEXT, MOTHERNAME TEXT, MOCCUPATION TEXT, ELEMNAME TEXT, ELEMADD TEXT, HIGHNAME TEXT, HIGHADD TEXT, COURSE TEXT, SUBJECTS TEXT, MOP TEXT, INSTALLMENT TEXT, YEAR TEXT)");
        db.execSQL("INSERT INTO "+student_table_name+" (ID) VALUES (2021000)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + student_table_name);
    }

    public boolean insertNewStudent(String lName, String fName, String mName, String age, String dob, String birthP, String address, String contact, String email, String fatherName, String fOccupation, String motherName, String mOccupation, String elemName, String elemAdd, String highName, String highAdd, String course, String subject, String mop, String installment, String year) {
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
        contentValues.put("ELEMNAME", elemName);
        contentValues.put("ELEMADD", elemAdd);
        contentValues.put("HIGHNAME", highName);
        contentValues.put("HIGHADD", highAdd);
        contentValues.put("COURSE", course);
        contentValues.put("SUBJECT", subject);
        contentValues.put("MOP",mop);
        contentValues.put("INSTALLMENT",installment);
        contentValues.put("YEAR",year);
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
