package ir.okandroid.mvp_simple_dictionary.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Aliyari on 09/11/2018.
 */

public class Database extends SQLiteOpenHelper {

    public static String DBNAME = "simple_entofatoen.db";
    public static int DBVERSION = 1;

    private String PATH = "";
    private Context context;
    public SQLiteDatabase mydb;
    public static String TB_EN2FA = "en2fa";
    public static String TB_FA2EN = "fa2en";
    public static String TB_HISTORY = "history";
    public static final String ID = "id";
    public static final String WORD = "Word";
    public static final String MEANING = "Meaning";

    public Database(Context context) {
        super(context, DBNAME, null, DBVERSION);
        this.context = context;
        PATH = "data/data/" + context.getPackageName() + "/databases/";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TB_HISTORY + "(" + ID + " integer primary key autoincrement," + WORD + " text," + MEANING + " text )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public void copydatabase() throws IOException {
        OutputStream outputStream = new FileOutputStream(PATH + DBNAME);
        byte[] buffer = new byte[1024];
        int length;
        InputStream inputStream=context.getAssets().open(DBNAME);
        while ((length=inputStream.read(buffer))>0){
            outputStream.write(buffer,0,length);
        }
        inputStream.close();
        outputStream.flush();
        outputStream.close();
    }
    public boolean checkdb() {

        SQLiteDatabase db = null;
        try {
            db = SQLiteDatabase.openDatabase(PATH + DBNAME, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLException e) {

        }
        return db != null ? true : false;

    }
    public void useable() {

        boolean checkdb = checkdb();

        if (checkdb) {

        } else {

            this.getReadableDatabase();

            try {
                copydatabase();
            } catch (IOException e) {
            }

        }

    }

    public void open() {

        mydb = SQLiteDatabase.openDatabase(PATH + DBNAME, null, SQLiteDatabase.OPEN_READWRITE);

    }

    public void close() {
        mydb.close();
    }
}
