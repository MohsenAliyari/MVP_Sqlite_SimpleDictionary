package ir.okandroid.mvp_simple_dictionary.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import ir.okandroid.mvp_simple_dictionary.models.Items;

import java.util.ArrayList;

/**
 * Created by Aliyari on 09/11/2018.
 */

public class DatabaseAdapter extends Database {

    public DatabaseAdapter(Context context) {
        super(context);
    }

    /**
     * get list of word for AutoComplex Adapter from En 2 Fa table
     *
     * @param alphabet
     * @return
     */
    public ArrayList<Items> getByAlphabet_En2Fa(String tbName, String alphabet) {
        ArrayList<Items> arrayList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + tbName + " where " + WORD + " Like '" + alphabet + "%' order by Word", null);
        if (cursor.moveToFirst()) {
            do {
                Items items = new Items();
                items.setWord(cursor.getString(cursor.getColumnIndex(WORD)));
                items.setMeaning(cursor.getString(cursor.getColumnIndex(MEANING)));
                items.setId(cursor.getInt(cursor.getColumnIndex(ID)));
                arrayList.add(items);
            } while (cursor.moveToNext());
        }
            return arrayList;
        }


    /**
     * insert search result to history table
     * @param _word
     * @param _meaning
     * @return
     */
    public long setHistory(String _word, String _meaning) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(WORD, _word);
        cv.put(MEANING, _meaning);
        try {
            db.delete(TB_HISTORY, WORD + "='" + _word + "' and " + MEANING + "='" + _meaning + "'", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return db.insert(TB_HISTORY, null, cv);
    }

    public ArrayList<Items> getHistoriesList () {
        ArrayList<Items> arrayList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        try {
        Cursor cursor = db.rawQuery("select * from " + TB_HISTORY + " order by word", null);
        while (cursor.moveToNext()) {
            Items items = new Items();
            items.setWord(cursor.getString(cursor.getColumnIndex(WORD)));
            items.setMeaning(cursor.getString(cursor.getColumnIndex(MEANING)));
            items.setId(cursor.getInt(cursor.getColumnIndex(ID)));
            arrayList.add(items);
        }
        }catch (Exception e){
            e.printStackTrace();
        }
        return arrayList;
    }
}
