package com.sinceillust.databasedao.Activity;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.sinceillust.databasedao.Adapter.CustomAdapter;
import com.sinceillust.databasedao.DAO.DB;
import com.sinceillust.databasedao.DAO.DBHelper;
import com.sinceillust.databasedao.DAO.Word;
import com.sinceillust.databasedao.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    // List view
    private ListView listView;

    // Listview Adapter
    ArrayAdapter<String> adapter;
    CustomAdapter customAdapter;

    // Search EditText
    EditText inputSearch;

    //Database
    SQLiteDatabase mdb;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBHelper(this);

        //Renew Database
        mdb = db.getReadableDatabase();
        db.onUpgrade(mdb,1,1);
        mdb.close();

        //create Data
        DB Database = new DB(this);
        Database.CreateAllData();
//        db.addWord(new Word("A","เอ",null,1));
//        db.addWord(new Word("Computer","คอมพิวเตอร์",null,1));
//        db.addWord(new Word("Camera",null,null,1));
//        db.addWord(new Word("Camel",null,null,1));
//        db.addWord(new Word("Camel",null,null,1));
//        db.addWord(new Word("Camel",null,null,1));
//        db.addWord(new Word("Camel",null,null,1));
//        db.addWord(new Word("Camel",null,null,1));
//        db.addWord(new Word("Camel",null,null,1));
//        db.addWord(new Word("Camel",null,null,1));
//        db.addWord(new Word("Camel",null,null,1));
//        db.addWord(new Word("Camel",null,null,1));
//        db.addWord(new Word("Camel",null,null,1));
//        db.addWord(new Word("Camel",null,null,1));
//        db.addWord(new Word("Camel",null,null,1));
//        db.addWord(new Word("Camel",null,null,1));
//        db.addWord(new Word("Camel",null,null,1));





        //view matching
        listView = (ListView) findViewById(R.id.listView);
        inputSearch = (EditText) findViewById(R.id.inputSearch);

        //Select all data
        final List<Word> getWord_word = db.getAllWords();
        List<String> getWord_string = db.getAllWords2();
/*
        List<String> getWord_word = new ArrayList<String>();
        getWord.add("aaa");
        getWord.add("aab");
        getWord.add("aac");
*/
        //Sort list
        //Collections.sort(getWord_word);

        // Adding items to listview with default adapter
        adapter = new ArrayAdapter<String>(this, R.layout.list, R.id.word, getWord_string);
        //listView.setAdapter(adapter);

        //Adding item to Listview with custom adapter
        //customAdapter = new CustomAdapter(getApplicationContext(), getWord_word);


        //Querry Data Log
        for(Word word : getWord_word){
            String log = "No : " + word.getID() + ", " +
                         "Word : " + word.getWord() + ", " +
                         "Tran : " + word.getTrans() + ", " +
                         "Ter : " + word.getTermino() + ", " +
                         "Cat : " + word.getCat_id();
            Log.d("Word", log);
        }

        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //MainActivity.this.adapter.getFilter().filter(s);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                List<Word> src_word = new ArrayList<Word>();
                int textlength = inputSearch.getText().length();

                if(textlength==0){
                    listView.setAdapter(null);
                } else {
                    for (int i = 0; i < getWord_word.size(); i++) {
                        try {
                            if (inputSearch.getText().toString()
                                    .equalsIgnoreCase(getWord_word.get(i).getWord()
                                            .subSequence(0, textlength)
                                            .toString())) {
                                src_word.add(getWord_word.get(i));
                            }
                        } catch (Exception e) {
                        }
                    }
                    Toast.makeText(MainActivity.this, "afterTextChanged", Toast.LENGTH_SHORT).show();
                    listView.setAdapter(new CustomAdapter(getApplicationContext(), src_word));
                    //listView.setAdapter(adapter);
                }
            }
        });
        //listView.setAdapter(adapter);
    }
}
