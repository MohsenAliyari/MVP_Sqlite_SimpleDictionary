package ir.okandroid.mvp_simple_dictionary.describeActivity.mvp;

import android.app.Activity;
import android.os.Bundle;

import ir.okandroid.mvp_simple_dictionary.utils.AppConfig;

/**
 * Created by Aliyari on 13/11/2018.
 */

public class DescribeModel {

    private Activity activity;
    public String title = "", description = "";
    public int id = 0;
    Bundle bundle = null;

    public DescribeModel(Activity activity) {
        this.activity = activity;
        getBundle();
    }

    private void getBundle() {
        bundle = this.activity.getIntent().getExtras();
        title = bundle.getString(AppConfig.WORD, "");
        description = bundle.getString(AppConfig.DESCRIPTION, "");
        id = bundle.getInt(AppConfig.ID, 0);
    }

}
