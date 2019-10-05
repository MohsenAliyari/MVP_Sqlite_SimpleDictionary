package ir.okandroid.mvp_simple_dictionary.baseActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ir.okandroid.mvp_simple_dictionary.baseActivity.mvp.WordsModel;
import ir.okandroid.mvp_simple_dictionary.baseActivity.mvp.WordPresenter;
import ir.okandroid.mvp_simple_dictionary.baseActivity.mvp.WordView;

public class BaseActivity extends AppCompatActivity {

    private WordPresenter wordPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WordView wordView = new WordView(this);
        setContentView(wordView);

        WordsModel wordsModel = new WordsModel(this);
        wordPresenter = new WordPresenter(wordsModel, wordView);
        wordPresenter.onCreate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        wordPresenter.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        wordPresenter.onPause();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        wordPresenter.onBack();
    }
    @Override
    protected void onResume() {
        super.onResume();
        wordPresenter.onResume();
    }
}
