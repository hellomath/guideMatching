package com.kimtajo.guideMatching.tourist;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.kimtajo.guideMatching.*;
import com.kimtajo.guideMatching.dataClass.Custom;
import com.kimtajo.guideMatching.dataClass.Schedule;
import com.kimtajo.guideMatching.listAdapterClass.ScheduleListAdapter;
import com.kimtajo.guideMatching.listClass.FriendList;
import com.kimtajo.guideMatching.listClass.ScheduleList;

import java.util.ArrayList;

/**
 * Created by Hellomath on 2014. 5. 1..
 */
public class myScheduleListActivity extends ListActivity {
    private TextView subject; // 상단 위치 주소
    final static String server = "http://hellomath.iptime.org/guideMatching"; // server url
    private Custom custInfo;
    private ScheduleList scdList;

    private ImageButton location;
    private ImageButton schedule;
    private ImageButton friend;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule);

        Intent intent = getIntent();
        custInfo = (Custom)intent.getSerializableExtra("custInfo"); // 회원 정보를 받아옴
        scdList = (ScheduleList)intent.getSerializableExtra("scdList");

        subject = (TextView)findViewById(R.id.gpsLocationOfSchedule); // 주소 출력 TextView
        subject.setText("My Schedule");


        location = (ImageButton)findViewById(R.id.locationOfSchedule);
        friend = (ImageButton)findViewById(R.id.friendOfSchedule);

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(myScheduleListActivity.this, locationActivity.class); // 햔재 클래스, 바뀔 클래스
                intent.putExtra("myLocation", "Detail Friend"); // 값을 넣어줌
                intent.putExtra("custInfo", custInfo); // 값을 넣어줌
                startActivity(intent); // 변경
                finish();
            }
        });

        friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FriendList friendList = new FriendList(custInfo.getCustNo());
                String url = server + "/friend/getFriendList.php";
                String state = friendList.sendFriendList(url);

                if(state.equals("ok")){ // 상태값이 ok인 경우 다음 엑티비티로
                    Intent intent = new Intent(myScheduleListActivity.this, friendActivity.class); // 햔재 클래스, 바뀔 클래스
                    intent.putExtra("custInfo", custInfo); // 값을 넣어줌
                    intent.putExtra("friendList", friendList);
                    startActivity(intent); // 변경
                    finish();
                }
                else if(state.equals("list_fail")){ // 로그인 실패 시
                    Toast.makeText(myScheduleListActivity.this, "목록 로딩에 실패하였습니다.", 100).show();
                }
                else{ // 그 외 오류
                    Toast.makeText(myScheduleListActivity.this, "서버와 연결 할 수 없습니다. 인터넷을 확인 해 주세요.", 100).show();
                }

            }
        });

        if(scdList.getList().size() > 0){
            ArrayList<Schedule> mlist = new ArrayList<Schedule>();
            mlist.addAll(scdList.getList());
            final ScheduleListAdapter myAdapter = new ScheduleListAdapter(this, R.layout.guide_item, mlist, custInfo.getCustNo());
            setListAdapter(myAdapter);

            getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                    Intent intent = new Intent(myScheduleListActivity.this, scheduleDetailActivity.class);
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

    @Override
    protected void onRestart() {
        super.onRestart();
        String url = server + "/schedule/getMyScdList.php";

        ScheduleList scheduleList = new ScheduleList(custInfo.getCustNo());

        String state = scheduleList.sendMyScdList(url);

        if (state.equals("ok")) { // 상태값이 ok인 경우 다음 엑티비티로
            scdList = scheduleList;


        } else if (state.equals("list_fail")) { // 로그인 실패 시
            Toast.makeText(getApplicationContext(), "list_fail", 100).show();
        } else { // 그 외 오류
            Toast.makeText(getApplicationContext(), "error", 100).show();
        }

        if(scdList.getList().size() > 0){
            ArrayList<Schedule> mlist = new ArrayList<Schedule>();
            mlist.addAll(scdList.getList());
            final ScheduleListAdapter myAdapter = new ScheduleListAdapter(this, R.layout.guide_item, mlist, custInfo.getCustNo());
            setListAdapter(myAdapter);

            getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                    Intent intent = new Intent(myScheduleListActivity.this, scheduleDetailActivity.class);
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