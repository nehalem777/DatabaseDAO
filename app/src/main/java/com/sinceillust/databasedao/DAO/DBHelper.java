package com.sinceillust.databasedao.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Since on 26/12/2557.
 */
public class DBHelper extends SQLiteOpenHelper{

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    //Database Name
    private static final String DATABASE_NAME = "wordsDB";

    //Table Words
    public static final String TABLE_WORDS = "Words";
    public static final String COLUMN_WORD_ID = "_id";                                              //primary key ต้องไว้คอลัมแรก
    public static final String COLUMN_WORD_WORD = "Word";
    public static final String COLUMN_WORD_TRNSITER = "trans";                                      // คำทับศัพท์
    public static final String COLUMN_WORD_TERMINOLOGY = "ter";
    public static final String COLUMN_WORD_CATEGORY_ID = "cate_id";

    public static final String TABLE_CREATE_WORDS = "CREATE TABLE  "
            + TABLE_WORDS + "("
            + COLUMN_WORD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_WORD_WORD + " TEXT , "
            + COLUMN_WORD_TRNSITER + " TEXT , "
            + COLUMN_WORD_TERMINOLOGY + " TEXT , "
            + COLUMN_WORD_CATEGORY_ID + " INTEGER  "
            +");";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE_WORDS);
        Log.e("since", "Create words table");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORDS);
        Log.e("since", "Table Upgrade from" + oldVersion + "to" + newVersion);
        onCreate(db);
    }

    //All CRUD Operations (Create, Read, Update and Delete)
    //Adding new word
    public void addWord(Word word){
        SQLiteDatabase db = this.getWritableDatabase();                                             //ขอสิทธิการเขียนลงฐานข้อมูล

        //Insert Content Value
        ContentValues values = new ContentValues();
        values.put(COLUMN_WORD_WORD, word.getWord());
        values.put(COLUMN_WORD_TRNSITER, word.getTrans());
        values.put(COLUMN_WORD_TERMINOLOGY, word.getTermino());
        values.put(COLUMN_WORD_CATEGORY_ID, word.getCat_id());

        //Inserting Row
        db.insert(TABLE_WORDS, null, values);
        db.close();                                                                                 //close Database Connection

        Log.e("since", "Insert \"" + word.getWord() + "\" Successful");
    }

    //Getting Single Word
    public Word getWord(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_WORDS, null, COLUMN_WORD_ID + " = ?",                        //SELECT * FROM TABLE_WORDS WHERE COLMUN_WORD_ID = id
                new String[] {String.valueOf(id)},null,null,null,null);

        //String selectQuery = "SELECT * FROM " + TABLE_WORDS +                                     //SELECT * FROM TABLE_WORDS WHERE COLMUN_WORD_ID = id
        //        " WHERE " + COLUMN_WORD_ID + " = " + id;
        //Cursor cursor = db.rawQuery(selectQuery,null);

        if (cursor != null)
            cursor.moveToFirst();                                                                   //เลื่อเคอเซอไปตำแหน่งแรก

        Word word = new Word();
        word.setID(cursor.getInt(cursor.getColumnIndex(COLUMN_WORD_ID)));
        word.setWord(cursor.getString(cursor.getColumnIndex(COLUMN_WORD_WORD)));
        word.setTrans(cursor.getString(cursor.getColumnIndex(COLUMN_WORD_TRNSITER)));
        word.setTermino(cursor.getString(cursor.getColumnIndex(COLUMN_WORD_TERMINOLOGY)));
        word.setCat_id(cursor.getInt(cursor.getColumnIndex(COLUMN_WORD_CATEGORY_ID)));

        //return word
        db.close();
        return word;

    }

    //Getting All Words
    public List<Word> getAllWords(){
        List<Word> wordList = new ArrayList<Word>();
        //Select All Query
        String allQuery = "SELECT * FROM " + TABLE_WORDS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(allQuery,null);

        //looping through all rows and adding to list
        if(cursor.moveToFirst()){
            do{
                Word word = new Word();
                word.setID(cursor.getInt(cursor.getColumnIndex(COLUMN_WORD_ID)));
                word.setWord(cursor.getString(cursor.getColumnIndex(COLUMN_WORD_WORD)));
                word.setTrans(cursor.getString(cursor.getColumnIndex(COLUMN_WORD_TRNSITER)));
                word.setTermino(cursor.getString(cursor.getColumnIndex(COLUMN_WORD_TERMINOLOGY)));
                word.setCat_id(cursor.getInt(cursor.getColumnIndex(COLUMN_WORD_CATEGORY_ID)));

                //Adding contac to list
                wordList.add(word);
            }while (cursor.moveToNext());                                                           //ทำจนกว่าจะเหลือแถวสุดท้าย
        }

        //return word list
        db.close();
        return wordList;
    }

    public List<String > getAllWords2(){
        List<String > wordList = new ArrayList<String >();
        //Select All Query
        String allQuery = "SELECT * FROM " + TABLE_WORDS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(allQuery,null);

        //looping through all rows and adding to list
        if(cursor.moveToFirst()){
            do{
                wordList.add(cursor.getString(cursor.getColumnIndex(COLUMN_WORD_WORD)));
            }while (cursor.moveToNext());
        }

        //return word list
        db.close();
        return wordList;
    }

    //Getting Words Count
    public int getWordsCount(){
        //Select All Query
        String countQuery = "SELECT * FROM " + TABLE_WORDS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery,null);
        cursor.close();

        //return count
        db.close();
        return cursor.getCount();

    }

    //Updating single word
    public int updateWord(Word word){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_WORD_WORD, word.getWord());
        values.put(COLUMN_WORD_TRNSITER, word.getTrans());
        values.put(COLUMN_WORD_TERMINOLOGY, word.getTermino());
        values.put(COLUMN_WORD_CATEGORY_ID, word.getCat_id());

        //updating row
        //Returns : the number of rows affected
        String wordUpdate = "";
        db.close();
        return db.update(TABLE_WORDS, values, COLUMN_WORD_ID + "= ?",
                new String[] {String.valueOf(word.getID())});
    }

    //Deleting single word
    public void deleteWord(Word word){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_WORDS, COLUMN_WORD_ID + " = ?",
                new String[] {String.valueOf(word.getID())});
        db.close();
    }

}
