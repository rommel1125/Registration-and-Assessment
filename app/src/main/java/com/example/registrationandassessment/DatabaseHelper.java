package com.example.registrationandassessment;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String db_name = "student_db";
    public static final String table_name = "student_tbl";
    public static final int version = 1;


    public DatabaseHelper(@Nullable Context context) {
        super(context, db_name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + table_name + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, LNAME TEXT, FNAME TEXT, MNAME TEXT, AGE TEXT, DOB TEXT, BIRTHPLACE TEXT, ADDRESS TEXT, CONTACT TEXT, EMAIL TEXT, FATHERNAME TEXT, FOCCUPATION TEXT, MOTHERNAME TEXT, MOCCUPATION TEXT, ELEMNAME TEXT, ELEMADD TEXT, HIGHNAME TEXT, HIGHADD TEXT, COURSE TEXT, SUBJECTS TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
        onCreate(db);
    }

    public boolean insertDate(String lName, String fName, String mName, String age, String dob, String birthP, String address, String contact, String email, String fatherName, String fOccupation, String motherName, String mOccupation, String elemName, String elemAdd, String highName, String highAdd, String course, String subject) {
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
        long result = db.insert(table_name, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
}
