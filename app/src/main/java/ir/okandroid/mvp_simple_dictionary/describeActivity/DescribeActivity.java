package ir.okandroid.mvp_simple_dictionary.describeActivity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ir.okandroid.mvp_simple_dictionary.describeActivity.mvp.DescribeModel;
import ir.okandroid.mvp_simple_dictionary.describeActivity.mvp.DescribePresenter;
import ir.okandroid.mvp_simple_dictionary.describeActivity.mvp.DescribeView;

import ir.okandroid.mvp_simple_dictionary.utils.AppConfig;

public class DescribeActivity extends AppCompatActivity {
    public static void startActivity(Context context, String word, String desc, int Id) {
        Intent intent = new Intent(context, DescribeActivity.class);
        intent.putExtra(AppConfig.WORD, word);
        intent.putExtra(AppConfig.DESCRIPTION, desc);
        intent.putExtra(AppConfig.ID, Id);
        context.startActivity(intent);
    }

    private DescribePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DescribeModel model = new DescribeModel(this);
        DescribeView view = new DescribeView(this);
        setContentView(view);

        presenter = new DescribePresenter(model, view);
        presenter.onCreate();

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        presenter.onRestart();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        presenter.onBack();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
