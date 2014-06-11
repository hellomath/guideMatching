package com.kimtajo.guideMatching.listAdapterClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.kimtajo.guideMatching.lib.ImageDownloader;
import com.kimtajo.guideMatching.R;
import com.kimtajo.guideMatching.dataClass.Info;

import java.util.ArrayList;

/**
 * Created by Hellomath on 2014. 4. 7..
 */
public class InfoListAdapter extends ArrayAdapter<Info> {
    private ArrayList<Info> items;
    Context mContext;
    private final ImageDownloader imageDownloader = new ImageDownloader();
    public InfoListAdapter(Context context, int textViewResourceId, ArrayList<Info> items){
        super(context, textViewResourceId, items);
        this.items = items;
        mContext = context;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View v = convertView;
        if(v == null){
            LayoutInflater vi = LayoutInflater.from(mContext.getApplicationContext());
            v = vi.inflate(R.layout.info_item, null);

        }
        Info item = items.get(position);
        if(item != null){
            TextView name = (TextView)v.findViewById(R.id.itemName);
            TextView tel = (TextView)v.findViewById(R.id.itemTel);
            TextView eval_cnt = (TextView)v.findViewById(R.id.itemEvalCount);
            ImageView thumbnail = (ImageView)v.findViewById(R.id.itemThumb);
            RatingBar eval_point = (RatingBar)v.findViewById(R.id.itemEvalPoint);



            if(name != null) name.setText(item.getName());
            if(tel != null) tel.setText(item.getTelNo());
            if(eval_cnt != null) eval_cnt.setText("("+item.getEvalCount()+")");
            if(eval_point != null) eval_point.setRating((float)item.getEvalPoint());
            if(thumbnail != null){
                //imageDownloader.setMode(ImageDownloader.Mode.NO_DOWNLOADED_DRAWABLE);
                //imageDownloader.download(item.getThumbUrl(), thumbnail);
            }
        }
        return v;
    }
}