package com.m90.badshahandicappertips.general.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.m90.badshahandicappertips.R;
import com.m90.badshahandicappertips.general.model.TrackModel;
import com.m90.badshahandicappertips.general.model.TrackModel;

import java.util.ArrayList;

public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.MyViewHolder> {

    private static final String TAG = "CartAdapter";

    private Activity mContext;
    ArrayList<TrackModel> list;
    private  ItemClickListener itemClickListener;
    String hname="";

    public TrackAdapter(Activity context, ArrayList<TrackModel> list) {
        this.list = list;
        mContext = context;
    }

    public TrackAdapter(Activity context, ArrayList<TrackModel> list, ItemClickListener itemClickListener){
        mContext = context;
        this.list = list;
        this.itemClickListener=itemClickListener;
    }

    public interface ItemClickListener {
        void onClick(View view, int position);
    }

    @Override

    public  MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_track, null);

        //  prefManager=new PrefManager(mContext);
        return new  MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, final int i) {

//        Log.e("distance", list.get(i).distance);
        //Log.e("remark", list.get(i).remark);
        String date1="",venue1="",track="", progress="",distance = "",remark="";

        if(list.get(i).date1==null)
        {
            date1="";
        }
        else {
            date1=list.get(i).date1;
        }

         if(list.get(i).venue1==null)
         {
             venue1="";
         }
          else {
             venue1=list.get(i).venue1;
         }

          if(list.get(i).track==null)
         {
             track="";
         }
          else {
              track=list.get(i).track;
         }
          if(list.get(i).progress==null)
         {
             progress="";
         }
          else {
              progress=list.get(i).progress;
         }
        if(list.get(i).distance==null)
        {
            distance="";
        }
        else {
            distance=list.get(i).distance;
        }
        if(list.get(i).remark==null)
        {
            remark="";
        }
        else {
            remark=list.get(i).remark;
        }
        viewHolder.txt_date.setText(date1);
        viewHolder.txt_venue.setText(venue1);
        String  newString = track.replaceAll("\n", "");
      //String  newString1 = newString.replaceAll("_x000D_", "");
        Log.e( "chkstring: ",newString);
        viewHolder.txt_track.setText(newString.replace("_x000D_", " ")+"   "+progress+"   "+distance+"   "+remark);
        viewHolder.txt_progress.setText(progress);
        viewHolder.txt_distance.setText(distance);
        viewHolder.txt_remark.setText(remark);
     // viewHolder.txt_hname.setText(list.get(i).horse);
        viewHolder.txt_hname.setText(list.get(i).horse);

     /*if (list.contains(list.get(i-(i-1)).horse)) {

            viewHolder.txt_hname.setText(list.get(i).horse);

        }
        else {
            viewHolder.txt_hname.setText("");
        }*/
       // for(int j =0; j < list.size(); j++) {
           /* if (list.lastIndexOf(list.get(i)) != i)  {
                viewHolder.txt_hname.setText("");
                //System.out.println(list.get(i)+" is duplicated");
            }
            else
            {
                viewHolder.txt_hname.setText(list.get(i).horse);

            }*/
        //}

       /* if(hname.equals(list.get(i).horse))
        {
            viewHolder.txt_hname.setText("");
        }
        else {
            hname=list.get(i).horse;
            viewHolder.txt_hname.setText(list.get(i).horse);
        }*/
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txt_hname, txt_date,txt_venue, txt_track, txt_progress, txt_distance, txt_remark;

        public MyViewHolder(View itemView) {
            super(itemView);
            txt_hname = itemView.findViewById(R.id.txt_cardname);
            txt_date = itemView.findViewById(R.id.txt_date);
            txt_venue = itemView.findViewById(R.id.txt_venue);
            txt_track = itemView.findViewById(R.id.txt_track);
            txt_progress = itemView.findViewById(R.id.txt_progress);
            txt_distance = itemView.findViewById(R.id.txt_distance);
            txt_remark = itemView.findViewById(R.id.txt_remark);

            itemView.setOnClickListener(this); // bind the listener
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null) itemClickListener.onClick(view, getAdapterPosition());
        }
    }


}

