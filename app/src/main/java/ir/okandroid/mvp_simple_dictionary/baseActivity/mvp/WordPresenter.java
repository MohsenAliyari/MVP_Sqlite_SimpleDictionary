package ir.okandroid.mvp_simple_dictionary.baseActivity.mvp;


import android.text.Editable;
import android.text.TextWatcher;
import ir.okandroid.mvp_simple_dictionary.R;
import ir.okandroid.mvp_simple_dictionary.models.Items;
import ir.okandroid.mvp_simple_dictionary.utils.BasePresenter;
import ir.okandroid.mvp_simple_dictionary.utils.MyApplication;

import java.util.ArrayList;

import static ir.okandroid.mvp_simple_dictionary.database.Database.TB_EN2FA;
import static ir.okandroid.mvp_simple_dictionary.database.Database.TB_FA2EN;

/**
 * Created by Aliyari on 02/11/2018.
 */

public class WordPresenter extends BasePresenter {

    private WordsModel model;
    private WordView view;
    MyApplication myApplication = new MyApplication();


    public WordPresenter(WordsModel wordsModel, WordView wordView) {
        this.model = wordsModel;
        this.view = wordView;
    }

    public void setDataClick() {

        view.sendSearchTextInterface(new WordView.setOnclickListener() {
            @Override
            public void onclick() {

                String alphabet = view.getSearchText();
                view.setRecyclerData(itemsArrayList(alphabet));
            }
        });

    }

    @Override
    public void onCreate() {
        super.onCreate();

        view.input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String alphabet = s.toString();
                if (!alphabet.isEmpty()) {
                    view.setRecyclerData(itemsArrayList(alphabet));
                } else {
                    getHistoriesItems();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        setDataClick();
        getHistoriesItems();
    }

    private void getHistoriesItems() {
        ArrayList<Items> itemsArrayList = new ArrayList<>();
        itemsArrayList = (myApplication.dbAdapter.getHistoriesList());
        view.setRecyclerData(itemsArrayList);
    }

    public ArrayList<Items> itemsArrayList(String alphabet) {
        ArrayList<Items> itemsList = new ArrayList<>();
       if (isPersianAlphabet(alphabet)){
           itemsList=model.arrayList_CustomAdapter(TB_FA2EN,alphabet);
       }else {
           itemsList=model.arrayList_CustomAdapter(TB_EN2FA,alphabet);
       }
        return itemsList;
    }

    private boolean isPersianAlphabet(String alphabet) {
        String charAt0= String.valueOf(alphabet.charAt(0));
        String persianAlphbet[] = view.getContext().getResources().getStringArray(R.array.persian_alphabet);
        for (String string : persianAlphbet) {
            if (string.equals(charAt0)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBack() {
        super.onBack();
    }
}
