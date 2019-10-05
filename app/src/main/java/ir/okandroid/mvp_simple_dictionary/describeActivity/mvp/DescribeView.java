package ir.okandroid.mvp_simple_dictionary.describeActivity.mvp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.okandroid.mvp_simple_dictionary.R;

/**
 * Created by Aliyari on 13/11/2018.
 */

public class DescribeView extends FrameLayout {


    @BindView(R.id.desc_TvTitle)
    TextView descTvTitle;
    @BindView(R.id.desc_TvDescription)
    TextView descTvDescription;


    @BindView(R.id.desc_ImgPlayUsBtn)
    ImageView descImgPlayUs;
    @BindView(R.id.desc_ImgPlayUkBtn)
    ImageView descImgPlayUkBtn;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.textViewUk)
    TextView textViewUk;
    private Context context;
    View view = null;


    public DescribeView(@NonNull Context context) {
        super(context);
        this.context = context;
        view = inflate(context, R.layout.activity_describe, this);
        ButterKnife.bind(this, view);
    }


    public void setItemsData(String word, String desc, int id) {
        descTvTitle.setText(word);
        descTvDescription.setText(desc);

    }

    public String getTextToSpeach() {
        return descTvTitle.getText().toString();
    }


    public interface btnOnclick {
        void onClick();
    }


    public void btnPlayUs(final btnOnclick clicked) {
        descImgPlayUs.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked.onClick();
            }
        });
    }
    public void btnPlayUk(final btnOnclick clicked) {
        descImgPlayUkBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked.onClick();
            }
        });

    }

    public void textToSpeechBtnOnclick(final String textToSpeach) {
/*
        descImgPlayUs.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, textToSpeach, Toast.LENGTH_SHORT).show();
                toSpeechUs.speak(textToSpeach, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
        descImgPlayUkBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                toSpeechUk.speak(textToSpeach, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
*/


    }


}
