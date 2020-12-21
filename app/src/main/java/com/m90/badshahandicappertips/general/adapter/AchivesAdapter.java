package com.m90.badshahandicappertips.general.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.m90.badshahandicappertips.R;
import com.m90.badshahandicappertips.constants.Utility;
import com.m90.badshahandicappertips.general.ArchiveWebviewActivity;
import com.m90.badshahandicappertips.general.GeneralWebviewActivity;
import com.m90.badshahandicappertips.general.NewSubsribeActivity;
import com.m90.badshahandicappertips.general.RaceCardActivity;
import com.m90.badshahandicappertips.general.RatingActivity;
import com.m90.badshahandicappertips.general.ResultActivity;
import com.m90.badshahandicappertips.general.model.ArchivesModel;

import java.util.ArrayList;

public class AchivesAdapter extends RecyclerView.Adapter<AchivesAdapter.MyViewHolder> {

    private static final String TAG = "CartAdapter";

    private Activity mContext;
    ArrayList<ArchivesModel> list;
    private  ItemClickListener itemClickListener;

    public AchivesAdapter(Activity context, ArrayList<ArchivesModel> list) {
        this.list = list;
        mContext = context;
    }

    public AchivesAdapter(Activity context, ArrayList<ArchivesModel> list, ItemClickListener itemClickListener){
        mContext = context;
        this.list = list;
        this.itemClickListener=itemClickListener;
    }

    public interface ItemClickListener {
        void onClick(View view, int position);
    }

    @Override

    public  MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_achives, null);

        //  prefManager=new PrefManager(mContext);
        return new  MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, final int i) {
       // final ArchivesModel item = list.get(i);

        //Log.e("vlist", list.toString());
        Log.e("vlist", list.get(i).venue);
         viewHolder.txt_cardname.setText(list.get(i).venue);
         viewHolder.txt_date.setText(list.get(i).date);

         viewHolder.txt_Results.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                // Toast.makeText(mContext, "bbb", Toast.LENGTH_SHORT).show();
                /* Bundle bundle2=new Bundle();
                 bundle2.putString("rvenue", list.get(i).venue);
                 bundle2.putString("rdate", list.get(i).date);
                 bundle2.putString("active1", "0");
                 Utility.launchActivity(mContext, ResultActivity.class,
                         false, bundle2);*/
                 Bundle bundle2=new Bundle();
                 bundle2.putString("rvenue", list.get(i).venue);
                 bundle2.putString("rdate", list.get(i).date );
                 bundle2.putString("active1", "0");
                 Utility.launchActivity(mContext, ResultActivity.class,
                         false, bundle2);

             }
         });


         viewHolder.txt_SpeedRating.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 Bundle bsp=new Bundle();
                 bsp.putString("rvenue", list.get(i).venue);
                 bsp.putString("rdate", list.get(i).date);
                 bsp.putString("url", mContext.getResources().getString(R.string.base_url)+"SpeedRating?active1=0&");

                 Utility.launchActivity(mContext, RatingActivity.class,
                         false, bsp);
                /* Bundle bundle=new Bundle();
                 bundle.putString("url", mContext.getResources().getString(R.string.base_url)+"SpeedRating?active1=0&venue="+list.get(i).venue+"&date="+list.get(i).date);
                // bundle.putString("url", mContext.getResources().getString(R.string.base_url)+"SpeedRating?active1=0&venue="+list.get(i).venue+"&date="+list.get(i).date);
                 Utility.launchActivity(mContext, RatingActivity.class,
                         false, bundle);*/
             }
         });

         viewHolder.txt_bpr.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
               //  Bundle bundle=new Bundle();
               /* // bundle.putString("url", mContext.getResources().getString(R.string.base_url)+"BendPositionRating?active1=0&venue="+list.get(i).venue+"&date="+list.get(i).date);
                 bundle.putString("url", mContext.getResources().getString(R.string.base_url)+"BendPositionRating?active1=0&venue="+list.get(i).venue+"&date="+list.get(i).date);
                 Utility.launchActivity(mContext, RatingActivity.class,
                         false, bundle);*/
                 Bundle bbp=new Bundle();
                 bbp.putString("rvenue", list.get(i).venue);
                 bbp.putString("rdate", list.get(i).date);
                 bbp.putString("url", mContext.getResources().getString(R.string.base_url)+"BendPositionRating?active1=0&");

                 Utility.launchActivity(mContext, RatingActivity.class,
                         false, bbp);
             }
         });

         viewHolder.txt_BestTips.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                /* Bundle bundle=new Bundle();
                 bundle.putString("url", mContext.getResources().getString(R.string.base_url)+"BestTips?active1=0&venue="+list.get(i).venue+"&date="+list.get(i).date);
                 Utility.launchActivity(mContext, ArchiveWebviewActivity.class,
                         false, bundle);*/

                 Bundle bb=new Bundle();
                 bb.putString("url", mContext.getResources().getString(R.string.base_url)+"BestTips?active1=0&venue="+list.get(i).venue+"&date="+list.get(i).date);
                 Utility.launchActivity(mContext, GeneralWebviewActivity.class,
                         false, bb);

             }
         });

         viewHolder.txt_eatingbets.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                /* Bundle bundle=new Bundle();
                 bundle.putString("url", mContext.getResources().getString(R.string.base_url)+"BestTips?active1=0&venue="+list.get(i).venue+"&date="+list.get(i).date);
                 Utility.launchActivity(mContext, ArchiveWebviewActivity.class,
                         false, bundle);*/

                 Bundle bb=new Bundle();
                 bb.putString("url", mContext.getResources().getString(R.string.base_url)+"BestTips?active1=0&venue="+list.get(i).venue+"&date="+list.get(i).date);
                 Utility.launchActivity(mContext, GeneralWebviewActivity.class,
                         false, bb);

             }
         });

         viewHolder.txt_BADSHAHANDICAPPERCHOICES.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                /* Bundle bundle=new Bundle();
                 bundle.putString("url", mContext.getResources().getString(R.string.base_url)+"BADSHAHANDICAPPERCHOICES?active1=0&venue="+list.get(i).venue+"&date="+list.get(i).date);
                 Utility.launchActivity(mContext, ArchiveWebviewActivity.class,
                         false, bundle);*/

                 Bundle bc=new Bundle();
                 bc.putString("rvenue", list.get(i).venue);
                 bc.putString("rdate", list.get(i).date);
                 bc.putString("url", mContext.getResources().getString(R.string.base_url)+"BADSHAHANDICAPPERCHOICES?active1=0&");
                 Utility.launchActivity(mContext, RatingActivity.class,
                         false, bc);
             }
         });

         viewHolder.txt_RaceCard.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 Bundle bundle=new Bundle();
                 bundle.putString("rvenue", list.get(i).venue);
                 bundle.putString("rdate", list.get(i).date);
                 bundle.putString("active1", "0");
                 Utility.launchActivity(mContext, RaceCardActivity.class,false, bundle);
                /* Bundle bundle=new Bundle();
                 bundle.putString("url", mContext.getResources().getString(R.string.base_url)+"Racecard?active1=0&venue="+list.get(i).venue+"&date"+list.get(i).date);
                 Utility.launchActivity(mContext, ArchiveWebviewActivity.class,
                         false, bundle);*/
             }
         });
         viewHolder.txt_ScaleRating.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 Bundle bs=new Bundle();
                 bs.putString("rvenue", list.get(i).venue);
                 bs.putString("rdate", list.get(i).date);
                 bs.putString("url", mContext.getResources().getString(R.string.base_url)+"ScaleRating?active1=0&");
                 Utility.launchActivity(mContext, RatingActivity.class,
                         false, bs);
                /* Bundle bundle=new Bundle();
                 bundle.putString("url", mContext.getResources().getString(R.string.base_url)+"ScaleRating?active1=0&venue="+list.get(i).venue+"&date="+list.get(i).date);
//                 bundle.putString("url", mContext.getResources().getString(R.string.base_url)+"ScaleRating?active1=0&venue="+list.get(i).venue+"&date="+list.get(i).date);
                 Utility.launchActivity(mContext, RatingActivity.class,
                         false, bundle);*/
             }
         });
         viewHolder.txt_video.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                /* Bundle bundle=new Bundle();
                 bundle.putString("url", mContext.getResources().getString(R.string.base_url)+"Videos?active1=0&venue="+list.get(i).venue+"&date="+list.get(i).date);
                 Utility.launchActivity(mContext, ArchiveWebviewActivity.class,
                         false, bundle);*/
             }
         });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView iv_cart;
        RelativeLayout rl_delete;
        int count = 0;
        TextView txt_eatingbets,txt_cardname, txt_date,txt_video, txt_Results,txt_RaceCard, txt_SpeedRating, txt_bpr, txt_BestTips,txt_ScaleRating,txt_BADSHAHANDICAPPERCHOICES;
        ImageView img;
        public MyViewHolder(View itemView) {
            super(itemView);
            txt_cardname = itemView.findViewById(R.id.txt_cardname);
            txt_date = itemView.findViewById(R.id.txt_date);
            txt_Results = itemView.findViewById(R.id.txt_Results);
            txt_RaceCard = itemView.findViewById(R.id.txt_RaceCard);
            txt_SpeedRating = itemView.findViewById(R.id.txt_SpeedRating);
            txt_bpr = itemView.findViewById(R.id.txt_bpr);
            txt_BestTips = itemView.findViewById(R.id.txt_BestTips);
            txt_BADSHAHANDICAPPERCHOICES = itemView.findViewById(R.id.txt_BADSHAHANDICAPPERCHOICES);
            txt_ScaleRating = itemView.findViewById(R.id.txt_ScaleRating);
            txt_video = itemView.findViewById(R.id.txt_video);
            txt_eatingbets = itemView.findViewById(R.id.txt_eatingbets);

            itemView.setOnClickListener(this); // bind the listener
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null) itemClickListener.onClick(view, getAdapterPosition());
        }
    }

    //region Search Filter (setFilter Code)
    public void setFilter(ArrayList<ArchivesModel> newList) {
        list = new ArrayList<>();
        list.addAll(newList);
        notifyDataSetChanged();
    }
}

