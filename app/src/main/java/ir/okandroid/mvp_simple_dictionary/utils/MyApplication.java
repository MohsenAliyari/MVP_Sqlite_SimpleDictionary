package ir.okandroid.mvp_simple_dictionary.utils;

import android.app.Application;
import android.content.Context;

import ir.okandroid.mvp_simple_dictionary.database.DatabaseAdapter;

/**
 * Created by Aliyari on 08/11/2018.
 */

public class MyApplication extends Application {

    public static Context context;
    public static DatabaseAdapter dbAdapter=null;
    public MyApplication() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MyApplication.context=getApplicationContext();
        dbAdapter=new DatabaseAdapter(MyApplication.context);
        dbAdapter.useable();
    }

    public static Context getAppContext() {
        return MyApplication.context;
    }
}
