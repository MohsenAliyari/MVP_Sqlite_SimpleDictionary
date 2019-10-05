package ir.okandroid.mvp_simple_dictionary.baseActivity.mvp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import ir.okandroid.mvp_simple_dictionary.R;
import ir.okandroid.mvp_simple_dictionary.baseActivity.adapter.ItemsAdapter;
import ir.okandroid.mvp_simple_dictionary.describeActivity.DescribeActivity;
import ir.okandroid.mvp_simple_dictionary.interfaces.RecyclerItemClickListener;
import ir.okandroid.mvp_simple_dictionary.models.Items;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Aliyari on 02/11/2018.
 */

public class WordView extends FrameLayout {
    @BindView(R.id.baseSearch)
    ImageButton btnSearch;
    @BindView(R.id.baseInput)
    AutoCompleteTextView input;
    @BindView(R.id.baseRecycler)
    RecyclerView baseRecycler;
    private Context context;

    public WordView(@NonNull Context context) {
        super(context);
        this.context = context;
        View view = inflate(context, R.layout.activity_base, this);
        ButterKnife.bind(this, view);
    }



    public String getSearchText() {
        return input.getText().toString();
    }


    public void sendSearchTextInterface(final setOnclickListener listener) {
        btnSearch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onclick();

            }
        });
    }

    //** init onclick with interface
    public interface setOnclickListener {
        void onclick();
    }


    private RecyclerItemClickListener itemClickListener = new RecyclerItemClickListener() {
        @Override
        public void onItemClick(Items items) {
           DescribeActivity.startActivity(context,items.getWord(),items.getMeaning(),items.getId());
        }
    };

    public void setRecyclerData(ArrayList<Items> arrayList) {
        ItemsAdapter adapter = new ItemsAdapter(arrayList, itemClickListener);
        baseRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        baseRecycler.setHasFixedSize(true);
        baseRecycler.setAdapter(adapter);
    }

}
