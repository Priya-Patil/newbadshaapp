package com.m90.badshahandicappertips.general.adapter;


import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import com.m90.badshahandicappertips.R;

import java.util.ArrayList;
import java.util.HashMap;

public class SubjectContentListAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private ArrayList<HashMap<String,String>> hostelItems;

    public SubjectContentListAdapter(Activity activity, ArrayList<HashMap<String,String>> hostelItems) {
        this.activity = activity;
        this.hostelItems = hostelItems;
    }

    @Override
    public int getCount() {
        return hostelItems.size();
    }

    @Override
    public Object getItem(int location) {
        return hostelItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.item_track, null);


        TextView chapter = (TextView) convertView.findViewById(R.id.txt_cardname);

        chapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ListView)parent).performItemClick(view, position, 0);
            }
        });

        // getting movie data for the row
        HashMap<String,String> m = hostelItems.get(position);

        Log.e( "getView: ", m.toString());

        // name
        chapter.setText(m.get("chapter_name"));


          return convertView;
    }
}