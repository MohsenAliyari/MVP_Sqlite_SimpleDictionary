package ir.okandroid.mvp_simple_dictionary.describeActivity.mvp;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.widget.Toast;

import ir.okandroid.mvp_simple_dictionary.utils.BasePresenter;
import ir.okandroid.mvp_simple_dictionary.utils.MyApplication;

import java.util.Locale;

/**
 * Created by Aliyari on 13/11/2018.
 */

public class DescribePresenter extends BasePresenter {
    private DescribeModel model;
    private DescribeView view;
    private Context context;
    TextToSpeech toSpeechUs, toSpeechUk;

    public DescribePresenter(DescribeModel model, DescribeView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = MyApplication.getAppContext();
        view.setItemsData(model.title, model.description, model.id);
        view.textToSpeechBtnOnclick(view.getTextToSpeach());
        setToHistory(model.title, model.description);
        seDataClicked();
        initToSpeech1(view.getTextToSpeach());
    }

    public void seDataClicked() {

        view.btnPlayUk(new DescribeView.btnOnclick() {
            @Override
            public void onClick() {
                if (toSpeechUk != null) {
                    toSpeechUk.stop();
                    toSpeechUk.speak(view.getTextToSpeach(), TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });
        view.btnPlayUs(new DescribeView.btnOnclick() {
            @Override
            public void onClick() {
                if (toSpeechUs != null) {
                    toSpeechUs.stop();
                    toSpeechUs.speak(view.getTextToSpeach(), TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });
    }


    private void setToHistory(String word, String desc) {
        long res = MyApplication.dbAdapter.setHistory(word, desc);
        if (res > 0) {
            Toast.makeText(context, "insert in history", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "dont insert in history", Toast.LENGTH_SHORT).show();

        }
    }

    public void initToSpeech1(final String textToSpeach) {
        try {
            toSpeechUs = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status != TextToSpeech.ERROR) {
                        toSpeechUs.setLanguage(Locale.US);
                    } else {
                        Toast.makeText(context, "Something Wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            toSpeechUs.setSpeechRate(1f);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ;
        try {
            toSpeechUk = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status != TextToSpeech.ERROR) {
                        toSpeechUk.setLanguage(Locale.UK);
                    } else {
                        Toast.makeText(context, "Something Wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            toSpeechUk.setSpeechRate(1f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPause() {
        if (toSpeechUk != null) {
            toSpeechUk.stop();
            toSpeechUk.shutdown();
        }
        if (toSpeechUs != null) {
            toSpeechUs.stop();
            toSpeechUs.shutdown();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onRestart() {
        super.onRestart();
    }

    @Override
    public void onBack() {
        super.onBack();
    }
}
