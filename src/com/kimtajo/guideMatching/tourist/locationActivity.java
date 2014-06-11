package com.kimtajo.guideMatching.tourist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.*;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.kimtajo.guideMatching.*;
import com.kimtajo.guideMatching.dataClass.Custom;
import com.kimtajo.guideMatching.lib.MyLocalManager;
import com.kimtajo.guideMatching.listClass.FriendList;
import com.kimtajo.guideMatching.listClass.GuideList;
import com.kimtajo.guideMatching.listClass.InfoList;
import com.kimtajo.guideMatching.listClass.ScheduleList;

/**
 * Created by Hellomath on 2014. 3. 31..
 * 사용자의 위치를 MyLocation 클래스를 통해 받아온다.
 * 음식점/숙박지/관광지/가이드를 선택할 수 있는 메뉴가 있다.
 * 또한 loginActivity에서 받아온 로그인 정보를
 * custInfo 로 받는다.
 */
public class locationActivity extends Activity {
    private TextView locationView; // 상단 위치 주소
    LocationManager locationManager;
    MyLocalManager myLocalManager = new MyLocalManager();

    private ImageButton food;
    private ImageButton stay;
    private ImageButton tour;
    private ImageButton guide;

    private ImageButton friend;
    private ImageButton schedule;
    private ImageButton option;

    final static String server = "http://hellomath.iptime.org/guideMatching"; // server url

    @Override
    protected void onStop() {
        super.onStop();
        myLocalManager.removeGPS(); // GPS 업데이트 중지
    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location);

        Intent intent = getIntent();
        final Custom custInfo = (Custom)intent.getSerializableExtra("custInfo"); // 회원 정보를 받아옴
        String myLocation = (String)intent.getSerializableExtra("myLocation");


        locationView = (TextView)findViewById(R.id.gpsLocation); // 주소 출력 TextView
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        myLocalManager.startLocations(locationManager, this, locationView); // 위치정보를 받아오는 것을 시작한다.

        food = (ImageButton)findViewById(R.id.food);
        stay = (ImageButton)findViewById(R.id.stay);
        tour = (ImageButton)findViewById(R.id.tour);
        guide = (ImageButton)findViewById(R.id.guide);

        friend = (ImageButton)findViewById(R.id.friendOfLocation);
        schedule = (ImageButton)findViewById(R.id.scheduleOfLocation);
        option = (ImageButton)findViewById(R.id.optionOfLocation);

        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                InfoList infoListData = new InfoList('F', myLocalManager.getJusoFull()); // 현재 자신의 위치의 전체 주소를 저장한다.
                String url = server+"/info/getInfoList.php";
                String state = infoListData.sendInfoList(url);

                if(state.equals("ok")){ // 상태값이 ok인 경우 다음 엑티비티로

                    Intent intent = new Intent(locationActivity.this, infoListActivity.class); // 햔재 클래스, 바뀔 클래스
                    intent.putExtra("myLocation", locationView.getText().toString()); // 값을 넣어줌
                    intent.putExtra("custInfo", custInfo); // 값을 넣어줌
                    intent.putExtra("infoListData", infoListData);

                    startActivity(intent); // 변경
                }
                else if(state.equals("list_fail")){ // 로그인 실패 시
                    Toast.makeText(locationActivity.this, "목록 로딩에 실패하였습니다.", 100).show();
                }
                else{ // 그 외 오류
                    Toast.makeText(locationActivity.this, "서버와 연결 할 수 없습니다. 인터넷을 확인 해 주세요.", 100).show();
                }
            }
        });

        stay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                InfoList infoListData = new InfoList('S', myLocalManager.getJusoFull());
                String url = server+"/info/getInfoList.php";
                String state = infoListData.sendInfoList(url);

                if(state.equals("ok")){ // 상태값이 ok인 경우 다음 엑티비티로

                    Intent intent = new Intent(locationActivity.this, infoListActivity.class); // 햔재 클래스, 바뀔 클래스
                    intent.putExtra("myLocation", locationView.getText().toString()); // 값을 넣어줌
                    intent.putExtra("custInfo", custInfo); // 값을 넣어줌
                    intent.putExtra("infoListData", infoListData);

                    startActivity(intent); // 변경
                }
                else if(state.equals("list_fail")){ // 로그인 실패 시
                    Toast.makeText(locationActivity.this, "목록 로딩에 실패하였습니다.", 100).show();
                }
                else{ // 그 외 오류
                    Toast.makeText(locationActivity.this, "서버와 연결 할 수 없습니다. 인터넷을 확인 해 주세요.", 100).show();
                }
            }
        });

        tour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                InfoList infoListData = new InfoList('T', myLocalManager.getJusoFull());
                String url = server+"/info/getInfoList.php";
                String state = infoListData.sendInfoList(url);

                if(state.equals("ok")){ // 상태값이 ok인 경우 다음 엑티비티로

                    Intent intent = new Intent(locationActivity.this, infoListActivity.class); // 햔재 클래스, 바뀔 클래스
                    intent.putExtra("myLocation", locationView.getText().toString()); // 값을 넣어줌
                    intent.putExtra("custInfo", custInfo); // 값을 넣어줌
                    intent.putExtra("infoListData", infoListData);

                    startActivity(intent); // 변경
                }
                else if(state.equals("list_fail")){ // 로그인 실패 시
                    Toast.makeText(locationActivity.this, "목록 로딩에 실패하였습니다.", 100).show();
                }
                else{ // 그 외 오류
                    Toast.makeText(locationActivity.this, "서버와 연결 할 수 없습니다. 인터넷을 확인 해 주세요.", 100).show();
                }
            }
        });
        guide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GuideList guideListData = new GuideList(custInfo.getCustNo(), myLocalManager.getJusoFull());
                String url = server+"/info/getGuideList.php";
                String state = guideListData.sendGuideList(url);


                if(state.equals("ok")){ // 상태값이 ok인 경우 다음 엑티비티로
                    Intent intent = new Intent(locationActivity.this, guideListActivity.class); // 햔재 클래스, 바뀔 클래스
                    intent.putExtra("myLocation", locationView.getText().toString()); // 값을 넣어줌
                    intent.putExtra("custInfo", custInfo); // 값을 넣어줌
                    intent.putExtra("guideList", guideListData);
                    startActivity(intent); // 변경
                }
                else if(state.equals("list_fail")){ // 로그인 실패 시
                    Toast.makeText(locationActivity.this, "목록 로딩에 실패하였습니다.", 100).show();
                }
                else{ // 그 외 오류
                    Toast.makeText(locationActivity.this, "서버와 연결 할 수 없습니다. 인터넷을 확인 해 주세요.", 100).show();
                }

            }
        });

        friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FriendList friendList = new FriendList(custInfo.getCustNo());
                String url = server + "/friend/getFriendList.php";
                String state = friendList.sendFriendList(url);

                if(state.equals("ok")){ // 상태값이 ok인 경우 다음 엑티비티로
                    Intent intent = new Intent(locationActivity.this, friendActivity.class); // 햔재 클래스, 바뀔 클래스
                    intent.putExtra("myLocation", locationView.getText().toString()); // 값을 넣어줌
                    intent.putExtra("custInfo", custInfo); // 값을 넣어줌
                    intent.putExtra("friendList", friendList);
                    startActivity(intent); // 변경
                    finish();
                }
                else if(state.equals("list_fail")){ // 로그인 실패 시
                    Toast.makeText(locationActivity.this, "목록 로딩에 실패하였습니다.", 100).show();
                }
                else{ // 그 외 오류
                    Toast.makeText(locationActivity.this, "서버와 연결 할 수 없습니다. 인터넷을 확인 해 주세요.", 100).show();
                }

            }
        });
        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(locationActivity.this, myScheduleListActivity.class);
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