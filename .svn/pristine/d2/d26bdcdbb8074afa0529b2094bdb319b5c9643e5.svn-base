package com.kimtajo.guideMatching.guide;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.kimtajo.guideMatching.*;
import com.kimtajo.guideMatching.dataClass.Custom;
import com.kimtajo.guideMatching.listAdapterClass.RcmCourseList;
import com.kimtajo.guideMatching.listClass.FriendList;
import com.kimtajo.guideMatching.listClass.ScheduleList;
import com.kimtajo.guideMatching.tourist.friendActivity;
import com.kimtajo.guideMatching.tourist.friendScheduleListActivity;
import com.kimtajo.guideMatching.tourist.guideDetailActivity;

/**
 * Created by Hellomath on 2014. 3. 31..
 * 위치 기반 정보 메인
 */
public class guideMainActivity extends Activity {

    private ImageButton friendList;
    private ImageButton lang;
    private ImageButton rcmCourse;
    private ImageButton schedule;

    final static String server = "http://hellomath.iptime.org/guideMatching"; // server url

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide_main);
        Intent intent = getIntent();
        final Custom custInfo = (Custom)intent.getSerializableExtra("custInfo"); // 회원 정보를 받아옴

        friendList = (ImageButton)findViewById(R.id.friendListForGuide);
        lang = (ImageButton)findViewById(R.id.langForGuide);
        rcmCourse = (ImageButton)findViewById(R.id.rcmCourseForGuide);
        schedule = (ImageButton)findViewById(R.id.scdForGuide);

        friendList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FriendList friendList = new FriendList(custInfo.getCustNo());
                String url = server + "/friend/getGuideFriendList.php";
                String state = friendList.sendGuideFriendList(url);

                if(state.equals("ok")){ // 상태값이 ok인 경우 다음 엑티비티로
                    Intent intent = new Intent(guideMainActivity.this, guideFriendListActivity.class); // 햔재 클래스, 바뀔 클래스
                    intent.putExtra("custInfo", custInfo); // 값을 넣어줌
                    intent.putExtra("friendList", friendList);
                    startActivity(intent); // 변경
                }
                else if(state.equals("list_fail")){ // 로그인 실패 시
                    Toast.makeText(guideMainActivity.this, "목록 로딩에 실패하였습니다.", 100).show();
                }
                else{ // 그 외 오류
                    Toast.makeText(guideMainActivity.this, "서버와 연결 할 수 없습니다. 인터넷을 확인 해 주세요.", 100).show();
                }
            }
        });

        rcmCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(guideMainActivity.this, guideRcmListActivity.class);

                String url = server+"/info/getRcmList.php";
                RcmCourseList rcmCourseList = new RcmCourseList(custInfo.getCustNo());

                String state = rcmCourseList.sendRcmList(url);

                if(state.equals("ok")){ // 상태값이 ok인 경우 다음 엑티비티로
                    intent.putExtra("custInfo", custInfo);
                    intent.putExtra("rcmList", rcmCourseList);
                }
                else if(state.equals("list_fail")){ // 로그인 실패 시
                    intent.putExtra("custInfo", custInfo);
                }
                else{ // 그 외 오류
                    intent.putExtra("custInfo", custInfo);
                }
                startActivity(intent); // 변경
            }
        });
        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(guideMainActivity.this, guideScdListActivity.class);
                String url = server + "/schedule/getScdList.php";
                ScheduleList scheduleList = new ScheduleList(custInfo.getCustNo(),custInfo.getCustNo());

                String state = scheduleList.sendScdList(url);

                if (state.equals("ok")) { // 상태값이 ok인 경우 다음 엑티비티로
                    intent.putExtra("custInfo", custInfo);
                    intent.putExtra("scdList", scheduleList);

                } else if (state.equals("list_fail")) { // 로그인 실패 시
                    intent.putExtra("custInfo", custInfo);
                    intent.putExtra("scdList", scheduleList);
                } else { // 그 외 오류
                    intent.putExtra("custInfo", custInfo);
                    intent.putExtra("scdList", scheduleList);
                }
                startActivity(intent); // 변경
            }
        });

    }
}