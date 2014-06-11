package com.kimtajo.guideMatching.tourist;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import com.kimtajo.guideMatching.*;
import com.kimtajo.guideMatching.dataClass.Custom;
import com.kimtajo.guideMatching.dataClass.Guide;
import com.kimtajo.guideMatching.lib.MyLocalManager;
import com.kimtajo.guideMatching.listAdapterClass.GuideListAdapter;
import com.kimtajo.guideMatching.listAdapterClass.RcmCourseList;
import com.kimtajo.guideMatching.listClass.GuideList;

import java.util.ArrayList;

/**
 * Created by Hellomath on 2014. 4. 29..
 */
public class guideListActivity extends ListActivity {
    private TextView locationView; // 상단 위치 주소
    private LocationManager locationManager;
    private MyLocalManager myLocalManager = new MyLocalManager();
    final static String server = "http://hellomath.iptime.org/guideMatching"; // server url
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        Intent intent = getIntent();
        final Custom custInfo = (Custom)intent.getSerializableExtra("custInfo"); // 회원 정보를 받아옴
        String myLocation = (String)intent.getSerializableExtra("myLocation");
        final GuideList guideListInfo = (GuideList)intent.getSerializableExtra("guideList");
        locationView = (TextView)findViewById(R.id.gpsLocationOfFoodList); // 주소 출력 TextView
        locationView.setText(myLocation);
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        myLocalManager.startLocations(locationManager, this, locationView);


        ArrayList<Guide> mlist = new ArrayList<Guide>();

        mlist.addAll(guideListInfo.getList());
        final GuideListAdapter myAdapter = new GuideListAdapter(this, R.layout.guide_item, mlist, custInfo.getCustNo());
        setListAdapter(myAdapter);

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Intent intent = new Intent(guideListActivity.this, guideDetailActivity.class);
                int index = (int) myAdapter.getItemId(position);

                String url = server+"/info/getRcmList.php";
                RcmCourseList rcmCourseList = new RcmCourseList(myAdapter.getItem(index).getGuideCustNo());

                String state = rcmCourseList.sendRcmList(url);

                if(state.equals("ok")){ // 상태값이 ok인 경우 다음 엑티비티로

                    intent.putExtra("guideDetail", myAdapter.getItem(index));
                    intent.putExtra("myLocation", locationView.getText().toString()); // 값을 넣어줌
                    intent.putExtra("custInfo", custInfo);
                    intent.putExtra("rcmList", rcmCourseList);


                }
                else if(state.equals("list_fail")){ // 로그인 실패 시
                    intent.putExtra("guideDetail", myAdapter.getItem(index));
                    intent.putExtra("myLocation", locationView.getText().toString()); // 값을 넣어줌
                    intent.putExtra("custInfo", custInfo);
                }
                else{ // 그 외 오류
                    intent.putExtra("guideDetail", myAdapter.getItem(index));
                    intent.putExtra("myLocation", locationView.getText().toString()); // 값을 넣어줌
                    intent.putExtra("custInfo", custInfo);
                }
                startActivity(intent); // 변경

            }
        });

    }
}