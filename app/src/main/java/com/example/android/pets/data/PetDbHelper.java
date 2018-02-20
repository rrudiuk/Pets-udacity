package com.example.android.pets.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.pets.data.PetsContract.PetEntry;

/**
 * Created by ruslan on 19.02.18.
 */

public class PetDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    // database name
    public static final String DATABASE_NAME = "shelter.db";

    private static final String COMMA_SEP = ",";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + PetEntry.TABLE_NAME;

    public PetDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_PETS_TABLE =
                "CREATE TABLE " + PetEntry.TABLE_NAME + " (" +
                        PetEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
                        PetEntry.COLUMN_PET_NAME + " TEXT NOT NULL" + COMMA_SEP +
                        PetEntry.COLUMN_PET_BREED + " TEXT" + COMMA_SEP +
                        PetEntry.COLUMN_PET_GENDER + " INTEGER NOT NULL" + COMMA_SEP +
                        PetEntry.COLUMN_PET_WEIGHT + " INTEGER NOT NULL DEFAULT 0" +
                        " )";

        db.execSQL(SQL_CREATE_PETS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
