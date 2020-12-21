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
import com.m90.badshahandicappertips.general.model.TotalPlateNumberDetailsModel;

import java.util.ArrayList;

public class NumberDetailsRoundRaceAdapter extends RecyclerView.Adapter<NumberDetailsRoundRaceAdapter.MyViewHolder> {

    private static final String TAG = "CartAdapter";

    private Activity mContext;
    ArrayList<TotalPlateNumberDetailsModel> list;
    private  ItemClickListener itemClickListener;

    public NumberDetailsRoundRaceAdapter(Activity context, ArrayList<TotalPlateNumberDetailsModel> list) {
        this.list = list;
        mContext = context;
    }

    public NumberDetailsRoundRaceAdapter(Activity context, ArrayList<TotalPlateNumberDetailsModel> list, ItemClickListener itemClickListener){
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
        final TotalPlateNumberDetailsModel item = list.get(i);

        Log.e("vlist", item.toString());
        viewHolder.tv_title.setText(list.get(i).r);
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
    public void setFilter(ArrayList<TotalPlateNumberDetailsModel> newList) {
        list = new ArrayList<>();
        list.addAll(newList);
        notifyDataSetChanged();
    }
}

