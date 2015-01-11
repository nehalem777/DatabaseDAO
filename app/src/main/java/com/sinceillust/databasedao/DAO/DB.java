package com.sinceillust.databasedao.DAO;

import android.content.Context;

/**
 * Created by Since on 10/1/2558.
 */
public class DB {
    Context mContext;

    public DB(Context context) {
        mContext = context;
    }

    //Add word
    public void CreateAllData(){

        DBHelper db = new DBHelper(mContext);


        //A
        db.addWord(new Word("A","เอ",null,1));

        //C
        db.addWord(new Word("Computer","คอมพิวเตอร์",null,1));
        db.addWord(new Word("Camera",null,null,1));
        db.addWord(new Word("Camel",null,null,1));


        db.addWord(new Word("watermelon",null,null,1));
        db.addWord(new Word("turnip",null,null,1));
        db.addWord(new Word("tomato",null,null,1));
        db.addWord(new Word("strawberries",null,null,1));
        db.addWord(new Word("spinach",null,null,1));
        db.addWord(new Word("raspberry",null,null,1));
        db.addWord(new Word("radish",null,null,1));
        db.addWord(new Word("pumpkin",null,null,1));
        db.addWord(new Word("pineapple",null,null,1));
        db.addWord(new Word("pepper",null,null,1));
        db.addWord(new Word("onion",null,null,1));
        db.addWord(new Word("nectarine",null,null,1));
        db.addWord(new Word("mushroom",null,null,1));
        db.addWord(new Word("mango",null,null,1));
        db.addWord(new Word("macadamia",null,null,1));
        db.addWord(new Word("lychee",null,null,1));
        db.addWord(new Word("lettuce",null,null,1));
        db.addWord(new Word("kale",null,null,1));
        db.addWord(new Word("jojoba",null,null,1));
        db.addWord(new Word("grape",null,null,1));
        db.addWord(new Word("garlic",null,null,1));
        db.addWord(new Word("fig",null,null,1));
        db.addWord(new Word("eggplant",null,null,1));
        db.addWord(new Word("durian",null,null,1));
        db.addWord(new Word("corn",null,null,1));
        db.addWord(new Word("carrot",null,null,1));
        db.addWord(new Word("cabbage",null,null,1));
        db.addWord(new Word("blackberry",null,null,1));
        db.addWord(new Word("bamboo",null,null,1));
        db.addWord(new Word("banana",null,null,1));
        db.addWord(new Word("avocado",null,null,1));
        db.addWord(new Word("apple",null,null,1));

    }
}


