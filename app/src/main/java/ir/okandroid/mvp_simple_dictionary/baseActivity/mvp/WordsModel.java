package ir.okandroid.mvp_simple_dictionary.baseActivity.mvp;

import android.app.Activity;

import ir.okandroid.mvp_simple_dictionary.models.Items;
import ir.okandroid.mvp_simple_dictionary.utils.MyApplication;

import java.util.ArrayList;

/**
 * Created by Aliyari on 02/11/2018.
 */

public class WordsModel {

    private Activity activity;
    MyApplication myApplication = new MyApplication();

    public WordsModel(Activity activity) {
        this.activity = activity;


    }


    /**
     * Get Array List of Items
     *
     * @return
     */
    public ArrayList<Items> arrayList_CustomAdapter(String tbName,final String alphabet) {
        ArrayList<Items> itemsArrayList = new ArrayList<>();
        itemsArrayList = myApplication.dbAdapter.getByAlphabet_En2Fa(tbName,alphabet);
        return itemsArrayList;
    }


}
