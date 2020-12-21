package com.m90.badshahandicappertips.general.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.m90.badshahandicappertips.R;
import com.m90.badshahandicappertips.general.model.RoundModel;

import java.util.ArrayList;

public class RoundAdapter extends RecyclerView.Adapter<RoundAdapter.MyViewHolder> {

    private static final String TAG = "CartAdapter";

    private Activity mContext;
    ArrayList<RoundModel> list;
    private  ItemClickListener itemClickListener;

    public RoundAdapter(Activity context, ArrayList<RoundModel> list) {
        this.list = list;
        mContext = context;
    }

    public RoundAdapter(Activity context, ArrayList<RoundModel> list, ItemClickListener itemClickListener){
        mContext = context;
        this.list = list;
        this.itemClickListener=itemClickListener;
    }

    public interface ItemClickListener {
        void onClick(View view, int position);
    }

    @Override

    public  MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_circle, null);

        //  prefManager=new PrefManager(mContext);
        return new  MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, final int i) {
        final RoundModel item = list.get(i);

        Log.e("vlist", item.toString());
        viewHolder.tv_title.setText(item.getName());
       // viewHolder.img.setBackground(mContext.getResources().getDrawable(item.getImage_drawable()));



    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_title;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);

            itemView.setOnClickListener(this); // bind the listener
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null) itemClickListener.onClick(view, getAdapterPosition());
        }
    }

    //region Search Filter (setFilter Code)
    public void setFilter(ArrayList<RoundModel> newList) {
        list = new ArrayList<>();
        list.addAll(newList);
        notifyDataSetChanged();
    }
}

