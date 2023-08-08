package com.example.trainingmobilefave.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.trainingmobilefave.model.FavoriteCharacter;

import java.util.ArrayList;
import java.util.List;

public class CharacterDB {
    private DBHelper dbHelper;

    public  CharacterDB(Context context){
        this.dbHelper = DBHelper.getInstance(context);
    }

    //create
    public int insertCharacter(FavoriteCharacter character){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.FIELD_CHARACTER_NAME, character.getName());
        cv.put(DBHelper.FIELD_CHARACTER_KANJI, character.getNameKanji());
        cv.put(DBHelper.FIELD_CHARACTER_URL, character.getUrl());
        cv.put(DBHelper.FIELD_CHARACTER_IMAGE, character.getImageUrl());
        cv.put(DBHelper.FIELD_CHARACTER_FAVORITES, character.getFavorites());
        int insertedId = (int) db.insert(DBHelper.TABLE_CHARACTER, null, cv);
        db.close();
        return insertedId;
    }

    //Read
    public List<FavoriteCharacter> getAllFavoriteCharacters(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DBHelper.TABLE_CHARACTER, null);
        List<FavoriteCharacter> favoriteCharacterList = new ArrayList<>();
        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.FIELD_CHARACTER_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.FIELD_CHARACTER_NAME));
                String nameKanji = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.FIELD_CHARACTER_KANJI));
                String url = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.FIELD_CHARACTER_URL));
                String imageUrl = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.FIELD_CHARACTER_IMAGE));
                int favorites = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.FIELD_CHARACTER_FAVORITES));

                FavoriteCharacter character = new FavoriteCharacter(id, name, nameKanji, url, imageUrl, favorites);
                favoriteCharacterList.add(character);

                cursor.moveToNext();
            }
        }
        cursor.close();
        db.close();
        return favoriteCharacterList;
    }
    //Delete
    public int deleteCharacter(int id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int deletedRows = db.delete(DBHelper.TABLE_CHARACTER, DBHelper.FIELD_CHARACTER_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
        return deletedRows;
    }
}
