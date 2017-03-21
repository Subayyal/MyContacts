package com.example.subi.mycontacts;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Blob;

//CREATE TABLE contacts ( _id INTEGER PRIMARY KEY AUTOINCREMENT, fname TEXT, lname TEXT, phnum TEXT, email TEXT, address TEXT, favorite BOOLEAN)

/**
 * Created by subi on 3/19/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    //database constants
    private static final String DATABASE_NAME = "contacts.db";
    private static final int DATABASE_VERSION = 1;
    //database table column names
    public static final String TABLE_NAME = "contacts";
    public static final String CONTACT_ID = "_id";
    public static final String CONTACT_FIRST_NAME = "fname";
    public static final String CONTACT_LAST_NAME = "lname";
    public static final String CONTACT_PHONE_NUMBER = "phnum";
    public static final String CONTACT_EMAIL = "email";
    public static final String CONTACT_ADDRESS = "address";
    public static final String CONTACT_FAVORITE = "favorite";
    public static final String CONTACT_IMAGE = "image";

    public static final String[] CALL_COLUMNS = {CONTACT_ID, CONTACT_FIRST_NAME, CONTACT_LAST_NAME,
                                CONTACT_PHONE_NUMBER, CONTACT_EMAIL, CONTACT_ADDRESS, CONTACT_FAVORITE, CONTACT_IMAGE};

    //create SQL table
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
            CONTACT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            CONTACT_FIRST_NAME + " TEXT, " +
            CONTACT_LAST_NAME + " TEXT, " +
            CONTACT_PHONE_NUMBER + " TEXT, " +
            CONTACT_EMAIL + " TEXT, " +
            CONTACT_ADDRESS + " TEXT, " +
            CONTACT_FAVORITE + " TEXT, " +
            CONTACT_IMAGE + " BLOB" +
            ")";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
