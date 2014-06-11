package com.kimtajo.guideMatching.tourist;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.TextView;
import com.kimtajo.guideMatching.*;
import com.kimtajo.guideMatching.dataClass.Custom;
import com.kimtajo.guideMatching.dataClass.Friend;
import com.kimtajo.guideMatching.listAdapterClass.FriendListAdapter;
import com.kimtajo.guideMatching.listClass.FriendList;
import com.kimtajo.guideMatching.listClass.ScheduleList;

import java.util.ArrayList;

/**
 * Created by Hellomath on 2014. 4. 30..
 */
public class friendActivity extends ListActivity {

    final static String server = "http://hellomath.iptime.org/guideMatching"; // server url

    private ImageButton location;
    private ImageButton schedule;
    private ImageButton option;
    private TextView subject;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend);

        Intent intent = getIntent();
        final Custom custInfo = (Custom)intent.getSerializableExtra("custInfo"); // 회원 정보를 받아옴
        final FriendList friendListInfo = (FriendList)intent.getSerializableExtra("friendList");
        subject = (TextView)findViewById(R.id.gpsLocationOfFriend);
        subject.setText("Friend List");

        location = (ImageButton)findViewById(R.id.locationOfFriend);
        schedule = (ImageButton)findViewById(R.id.scheduleOfFriend);
        option = (ImageButton)findViewById(R.id.optionOfFriend);



        ArrayList<Friend> mlist = new ArrayList<Friend>();

        mlist.addAll(friendListInfo.getList());
        final FriendListAdapter myAdapter = new FriendListAdapter(this, R.layout.guide_item, mlist, custInfo);
        setListAdapter(myAdapter);

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                friendDetailDialog test = new friendDetailDialog(friendActivity.this, myAdapter.getItem(position), "Detail Friend", custInfo);
                test.show();
            }
        });

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(friendActivity.this, locationActivity.class); // 햔재 클래스, 바뀔 클래스
                    intent.putExtra("myLocation", "Detail Friend"); // 값을 넣어줌
                    intent.putExtra("custInfo", custInfo); // 값을 넣어줌
                    startActivity(intent); // 변경
                    finish();
            }
        });
        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(friendActivity.this, myScheduleListActivity.class);
                String url = server + "/schedule/getMyScdList.php";
                ScheduleList scheduleList = new ScheduleList(custInfo.getCustNo());

                String state = scheduleList.sendMyScdList(url);

                if (state.equals("ok")) { // 상태값이 ok인 경우 다음 엑티비티로
                    intent.putExtra("custInfo", custInfo);
                    intent.putExtra("scdList", scheduleList);
                    //intent.putExtra("friendCustNo", friend.getFriendCustNo());


                } else if (state.equals("list_fail")) { // 로그인 실패 시
                    intent.putExtra("custInfo", custInfo);
                    intent.putExtra("scdList", scheduleList);
                    //intent.putExtra("friendCustNo", friend.getFriendCustNo());
                } else { // 그 외 오류
                    intent.putExtra("custInfo", custInfo);
                    intent.putExtra("scdList", scheduleList);
                    //intent.putExtra("friendCustNo", friend.getFriendCustNo());
                }
                startActivity(intent);
                finish();
            }
        });


    }
}