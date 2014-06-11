package com.kimtajo.guideMatching.guide;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import com.kimtajo.guideMatching.R;
import com.kimtajo.guideMatching.dataClass.Custom;
import com.kimtajo.guideMatching.dataClass.Schedule;
import com.kimtajo.guideMatching.listAdapterClass.ScheduleListAdapter;
import com.kimtajo.guideMatching.listClass.ScheduleList;
import com.kimtajo.guideMatching.tourist.friendScheduleListActivity;
import com.kimtajo.guideMatching.tourist.scheduleDetailActivity;

import java.util.ArrayList;

/**
 * Created by Hellomath on 2014. 5. 2..
 */
public class guideScdListActivity extends ListActivity {
    final static String server = "http://hellomath.iptime.org/guideMatching"; // server url
    private Custom custInfo;
    private ScheduleList scdList;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide_list_view);

        Intent intent = getIntent();
        custInfo = (Custom)intent.getSerializableExtra("custInfo"); // 회원 정보를 받아옴
        scdList = (ScheduleList)intent.getSerializableExtra("scdList");

        if(scdList.getList().size() > 0){
            ArrayList<Schedule> mlist = new ArrayList<Schedule>();
            mlist.addAll(scdList.getList());
            final ScheduleListAdapter myAdapter = new ScheduleListAdapter(this, R.layout.guide_item, mlist, custInfo.getCustNo());
            setListAdapter(myAdapter);

            getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                    Intent intent = new Intent(guideScdListActivity.this, scheduleDetailActivity.class);
                    int index = (int) myAdapter.getItemId(position);
                    String url = server+"/schedule/chkScdJoin.php";
                    int chk = myAdapter.getItem(index).chkScdJoin(custInfo.getCustNo(), url);

                    intent.putExtra("scdDetail", myAdapter.getItem(index));
                    intent.putExtra("custInfo", custInfo);
                    intent.putExtra("joinChk", chk);
                    startActivity(intent); // 변경

                }
            });
        }
        else{
            ArrayList<String> noList = new ArrayList<String>();

            noList.add(0, "No Schedule");
            ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, noList);
            setListAdapter(arrayAdapter);
        }
    }
}