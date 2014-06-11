package com.kimtajo.guideMatching.listAdapterClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.kimtajo.guideMatching.lib.ChangeDateFormat;
import com.kimtajo.guideMatching.lib.ImageDownloader;
import com.kimtajo.guideMatching.R;
import com.kimtajo.guideMatching.dataClass.Schedule;

import java.util.ArrayList;

/**
 * Created by Hellomath on 2014. 4. 7..
 */
public class ScheduleListAdapter extends ArrayAdapter<Schedule> {
    private final ArrayList<Schedule> items;
    Context mContext;
    private final ImageDownloader imageDownloader = new ImageDownloader();
    private final int cust_no;
    final static String server = "http://hellomath.iptime.org/guideMatching"; // server url

    public ScheduleListAdapter(Context context, int textViewResourceId, ArrayList<Schedule> items, int cust_no){
        super(context, textViewResourceId, items);
        this.items = items;
        this.cust_no = cust_no;
        mContext = context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View v = convertView;
        if(v == null){
            LayoutInflater vi = LayoutInflater.from(mContext.getApplicationContext());
            v = vi.inflate(R.layout.schdule_item, null);

        }
        final Schedule item = items.get(position);
        TextView scdName = (TextView)v.findViewById(R.id.scdName);
        TextView scdDatetime = (TextView)v.findViewById(R.id.scdDatetime);
        TextView scdInfo = (TextView)v.findViewById(R.id.scdInfo);
        TextView scdEntry = (TextView)v.findViewById(R.id.scdEntry);

        if(item != null){
            if(scdName != null) scdName.setText(item.getScdName());
            if(scdDatetime != null){
                ChangeDateFormat cdf = new ChangeDateFormat();
                String datetime = cdf.formattedDate(item.getScdDatetime(), "yyyyMMddHHmm", "yyyy-MM-dd HH:mm");

                scdDatetime.setText(datetime);

            }
            if(scdInfo != null) scdInfo.setText(item.getScdInfo());
            if(scdEntry != null){
                if(item.getScdMeetOpnYn() == 'Y'){
                    scdEntry.setText(item.getMeetList().size()+" peoples");
                }
                else if(item.getScdMeetOpnYn() == 'N'){
                    scdEntry.setText("closed");
                }
            }

        }
        return v;
    }
}