package ir.okandroid.mvp_simple_dictionary.baseActivity.adapter;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ir.okandroid.mvp_simple_dictionary.R;
import ir.okandroid.mvp_simple_dictionary.interfaces.RecyclerItemClickListener;
import ir.okandroid.mvp_simple_dictionary.models.Items;

import java.util.ArrayList;

/**
 * Created by Hishki on 08/11/2018.
 */

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder>{

    private ArrayList<Items>arrayList;
    RecyclerItemClickListener itemClickListener;

    public ItemsAdapter(ArrayList<Items> arrayList, RecyclerItemClickListener itemClickListener) {
        this.arrayList = arrayList;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.row_items,parent,false);

        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Items items=arrayList.get(position);
        holder.title.setText(items.getWord());
        holder.id.setText(items.getId()+"");
        holder.description.setText(items.getMeaning());

        holder.items_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(items);
            }
        });
    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,id,description;
        ConstraintLayout items_layout;
        public ViewHolder(View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.itemTitle);
            description=itemView.findViewById(R.id.itemDescrip);
            id=itemView.findViewById(R.id.itemid);
            items_layout=itemView.findViewById(R.id.items_layout);
        }
    }
}
