package com.kimtajo.guideMatching.tourist;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.kimtajo.guideMatching.lib.ChangeDateFormat;
import com.kimtajo.guideMatching.dataClass.Custom;
import com.kimtajo.guideMatching.R;
import com.kimtajo.guideMatching.dataClass.Schedule;

import java.util.ArrayList;

/**
 * Created by Hellomath on 2014. 5. 1..
 */
public class scheduleDetailActivity extends ListActivity {
    final static String server = "http://hellomath.iptime.org/guideMatching"; // server url
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_detail);
        Intent intent = getIntent();
        final Schedule item = (Schedule)intent.getSerializableExtra("scdDetail");
        final Custom custInfo = (Custom)intent.getSerializableExtra("custInfo"); // 회원 정보를 받아옴
        final int joinChk = intent.getIntExtra("joinChk", 999);
        final int[] peoples = {item.getMeetList().size()};


        TextView scdName = (TextView)findViewById(R.id.scdNameDetail);
        TextView datetime = (TextView)findViewById(R.id.scdDatetimeDetail);
        TextView info = (TextView)findViewById(R.id.scdInfoDetail);
        final TextView entry = (TextView)findViewById(R.id.scdEntryDetail);
        TextView memo = (TextView)findViewById(R.id.scdMemoDetail);
        final ImageButton joinScd = (ImageButton)findViewById(R.id.scdJoinDetail);

        scdName.setText(item.getScdName());
        ChangeDateFormat cdf = new ChangeDateFormat();
        String datetimes = cdf.formattedDate(item.getScdDatetime(), "yyyyMMddHHmm", "yyyy-MM-dd HH:mm");

        datetime.setText(datetimes);
        info.setText(item.getScdInfo());


        final ArrayList<String>[] meetList = new ArrayList[]{new ArrayList<String>()};
        if(item.getScdMeetOpnYn() == 'Y'){
            entry.setText(peoples[0] +" peoples");

            if(item.getMeetList().size() > 0)
                meetList[0] = item.getMeetList();
            else
                meetList[0].add(0, "No Participant");
        }
        else{
            entry.setText("closed");
            meetList[0].add(0, "Closed");
        }

        if(joinChk > 0){
            joinScd.setImageResource(R.drawable.schedule_cancel);
        }
        else{
            joinScd.setImageResource(R.drawable.schedule_attend);
        }
        final ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, meetList[0]);
        setListAdapter(arrayAdapter);
        memo.setText(item.getScdMemo());

        joinScd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String url = server+"/schedule/joinScdData.php";
                    String result = item.joinScdData(custInfo.getCustNo(), url);
                if(result.equals("insert_ok")){
                    joinScd.setImageResource(R.drawable.schedule_cancel);

                    if(item.getScdMeetOpnYn() == 'Y'){
                        peoples[0]++;
                        entry.setText(peoples[0] +" peoples");
                        meetList[0].add(meetList[0].size(), custInfo.getCustNick());
                        meetList[0].remove("No Participant");
                        setListAdapter(arrayAdapter);

                    }
                    Toast.makeText(getApplicationContext(), "스케줄 참가신청 완료", 1000).show();
                }
                else if(result.equals("delete_ok")){
                    joinScd.setImageResource(R.drawable.schedule_attend);
                    if(item.getScdMeetOpnYn() == 'Y'){
                        peoples[0]--;
                        entry.setText(peoples[0] +" peoples");
                        meetList[0].remove(custInfo.getCustNick());
                        if(meetList[0].size() == 0)
                            meetList[0].add(0, "No Participant");
                        setListAdapter(arrayAdapter);
                    }
                    Toast.makeText(getApplicationContext(), "스케줄 참가신청 삭제 완료", 1000).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "서버와 연결할 수 없습니다.", 1000).show();
                }
            }
        });





    }

}