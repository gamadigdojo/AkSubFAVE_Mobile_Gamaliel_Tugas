package com.example.trainingmobilefave.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "dbCharacter";
    private static final int DB_VERSION = 1;

    public static final String TABLE_CHARACTER = "characters";

    public static final String FIELD_CHARACTER_ID = "id";
    public static final String FIELD_CHARACTER_NAME = "name";
    public static final String FIELD_CHARACTER_KANJI = "nameKanji";
    public static final String FIELD_CHARACTER_IMAGE = "imageUrl";
    public static final String FIELD_CHARACTER_URL = "url";
    public static final String FIELD_CHARACTER_FAVORITES = "favorites";

    private static final String CREATE_TABLE_CHARACTER = "CREATE TABLE " + TABLE_CHARACTER
            + "(" + FIELD_CHARACTER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            FIELD_CHARACTER_NAME + " TEXT," +
            FIELD_CHARACTER_KANJI + " TEXT," +
            FIELD_CHARACTER_IMAGE + " TEXT," +
            FIELD_CHARACTER_URL + " TEXT," +
            FIELD_CHARACTER_FAVORITES + " INTEGER);";

    private static final String DROP_TABLE_CHARACTER = "DROP TABLE IF EXISTS " + TABLE_CHARACTER;

    private static DBHelper dbInstance;
    public static synchronized DBHelper getInstance(Context context){
        if(dbInstance == null){
            dbInstance = new DBHelper(context);
        }
        return dbInstance;
    }

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CHARACTER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_CHARACTER);
        onCreate(db);
    }
}

