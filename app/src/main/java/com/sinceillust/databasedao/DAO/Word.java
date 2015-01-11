package com.sinceillust.databasedao.DAO;

/**
 * Created by Since on 26/12/2557.
 */
public class Word {

    //private variables
    int _id;
    String _word;
    String _trans;
    String _termino;
    int _cat_id;

    //Empty constructor
    public Word() {
    }

    //Constructor
    public Word(int id, String word, String termino, String trans, int cat_id) {
        this._id = id;
        this._word = word;
        this._trans = trans;
        this._termino = termino;
        this._cat_id = cat_id;
    }

    //Constructor
    public Word(String word, String termino, String trans, int cat_id) {
        this._word = word;
        this._trans = trans;
        this._termino = termino;
        this._cat_id = cat_id;
    }

    //Constructor
    public Word(String word){}

    //GettingID
    public int getID(){
        return this._id;
    }

    //SettingID
    public void setID(int id) {
        this._id = id;
    }

    //GettingWord
    public String getWord() {
        return _word;
    }

    //SettingWord
    public void setWord(String word) {
        this._word = word;
    }

    //GettingTrans
    public String getTrans() {
        return _trans;
    }

    //SettingTrans
    public void setTrans(String trans) {
        this._trans = trans;
    }

    //GettingTerminology
    public String getTermino() {
        return _termino;
    }

    //SettingTerminology
    public void setTermino(String termino) {
        this._termino = termino;
    }

    //GettingCategoryID
    public int getCat_id() {
        return _cat_id;
    }

    //SettingCategoryID
    public void setCat_id(int cat_id) {
        this._cat_id = cat_id;
    }
}
