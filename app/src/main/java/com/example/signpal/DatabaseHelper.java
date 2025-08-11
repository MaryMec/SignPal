package com.example.signpal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "SignPal.db";
    private static final int DATABASE_VERSION = 1;


    private static final String TABLE_USERS = "users";
    private static final String COL_ID = "id";
    private static final String COL_USERNAME = "username";
    private static final String COL_EMAIL = "email";
    private static final String COL_PASSWORD = "password";
public DatabaseHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
}

@Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL("CREATE TABLE" + TABLE_USERS + " (" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COL_USERNAME + " TEXT UNIQUE, " +
            COL_EMAIL + " TEXT, " +
            COL_PASSWORD + " TEXT)");
}

@Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
    onCreate(db);
}


public boolean insertUser(String username, String email, String password) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put(COL_USERNAME, username);
    values.put(COL_EMAIL, email);
    values.put(COL_PASSWORD, password);

    long result = db.insert(TABLE_USERS, null, values);
    db.close();
    return result != -1;
}

public boolean checkUser(String username, String password) {
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS +
            " WHERE " + COL_USERNAME + "=? AND " + COL_PASSWORD + "=?",
            new String[]{username, password});
    boolean exists = cursor.getCount() > 0;
    cursor.close();
    db.close();
    return exists;
}
}