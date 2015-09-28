package com.example.user.mommemories;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static com.example.user.mommemories.Constants.*;
/**
 * Created by User on 27/9/2558.
 */
public class NotesHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "notes.db";
    private static final int DATABASE_VERSION = 1;


    public NotesHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUME_TIME_NOTE + " INTEGER, " + COLUME_DATE_NOTE + " TEXT, " + COLUME_DETAIL_NOTE + " TEXT, " +
                COLUME_LOCATION_NOTE + " TEXT, " + COLUME_SUBJECT + " TEXT );");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
