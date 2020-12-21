package com.m90.badshahandicappertips.general.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.m90.badshahandicappertips.R;
import com.m90.badshahandicappertips.home.model.HomeModel;

import java.util.ArrayList;

public class SubscribeAdapter extends RecyclerView.Adapter<SubscribeAdapter.MyViewHolder> {

    private static final String TAG = "CartAdapter";

    private Activity mContext;
    ArrayList<HomeModel> list;
    private  ItemClickListener itemClickListener;

    public SubscribeAdapter(Activity context, ArrayList<HomeModel> list) {
        this.list = list;
        mContext = context;
    }

    public SubscribeAdapter(Activity context, ArrayList<HomeModel> list, ItemClickListener itemClickListener){
        mContext = context;
        this.list = list;
        this.itemClickListener=itemClickListener;
    }

    public interface ItemClickListener {
        void onClick(View view, int position);
    }

    @Override

    public  MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new, null);

        //  prefManager=new PrefManager(mContext);
        return new  MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, final int i) {
        final HomeModel item = list.get(i);

        Log.e("vlist", list.toString());
        viewHolder.tv_title.setText(item.getName());
        viewHolder.img.setBackground(mContext.getResources().getDrawable(item.getImage_drawable()));


        if(item.getName().equals("BADSHA HANDICAPPER\n         CHOICES"))
        {
            viewHolder.tv_title.setTextColor(mContext.getResources().getColor(R.color.orange));
        }

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView iv_cart;
        RelativeLayout rl_delete;
        int count = 0;
        TextView tv_title, tv_freepaid;
        ImageView img;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            img = itemView.findViewById(R.id.img);

            itemView.setOnClickListener(this); // bind the listener
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null) itemClickListener.onClick(view, getAdapterPosition());
        }
    }

    //region Search Filter (setFilter Code)
    public void setFilter(ArrayList<HomeModel> newList) {
        list = new ArrayList<>();
        list.addAll(newList);
        notifyDataSetChanged();
    }
}

