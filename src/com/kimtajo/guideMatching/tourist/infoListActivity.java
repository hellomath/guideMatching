package com.kimtajo.guideMatching.tourist;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.kimtajo.guideMatching.*;
import com.kimtajo.guideMatching.dataClass.Custom;
import com.kimtajo.guideMatching.dataClass.Info;
import com.kimtajo.guideMatching.lib.MyLocalManager;
import com.kimtajo.guideMatching.listAdapterClass.InfoListAdapter;
import com.kimtajo.guideMatching.listClass.InfoList;

import java.util.ArrayList;

/**
 * Created by Hellomath on 2014. 4. 7..
 */
public class infoListActivity extends ListActivity {
    private TextView locationView; // 상단 위치 주소
    private LocationManager locationManager;
    private MyLocalManager myLocalManager = new MyLocalManager();
    final static String server = "http://hellomath.iptime.org/guideMatching"; // server url
    private InfoList infoList;
    private Custom custInfo;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        Intent intent = getIntent();
        custInfo = (Custom)intent.getSerializableExtra("custInfo"); // 회원 정보를 받아옴
        String myLocation = (String)intent.getSerializableExtra("myLocation");
        infoList = (InfoList)intent.getSerializableExtra("infoListData");

        locationView = (TextView)findViewById(R.id.gpsLocationOfFoodList); // 주소 출력 TextView
        locationView.setText(myLocation);
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        myLocalManager.startLocations(locationManager, this, locationView);

        ArrayList<Info> mlist = new ArrayList<Info>();

        mlist.addAll(infoList.getList());
        final InfoListAdapter myAdapter = new InfoListAdapter(this, R.layout.info_item, mlist);
        setListAdapter(myAdapter);

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(infoListActivity.this, infoDetailActivity.class);
                int index = (int) myAdapter.getItemId(position);
                intent.putExtra("infoDetail", myAdapter.getItem(index));
                intent.putExtra("type", infoList.getType());
                intent.putExtra("myLocation", locationView.getText().toString()); // 값을 넣어줌
                intent.putExtra("custInfo", custInfo);
                startActivity(intent); // 변경
                //startActivityForResult(intent, DatabaseHelper.DETAIL_CODE);
            }
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        final InfoList infoListData = new InfoList(infoList.getType(), myLocalManager.getJusoFull());
        String url = server+"/info/getInfoList.php";
        String state = infoListData.sendInfoList(url);

        if(state.equals("ok")){ // 상태값이 ok인 경우 다음 엑티비티로
            ArrayList<Info> mlist = new ArrayList<Info>();
            Log.d("hellomath2", state.toString());
            mlist.addAll(infoListData.getList());
            final InfoListAdapter myAdapter = new InfoListAdapter(this, R.layout.info_item, mlist);
            setListAdapter(myAdapter);

            getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    Intent intent = new Intent(infoListActivity.this, infoDetailActivity.class);
                    int index = (int) myAdapter.getItemId(position);
                    intent.putExtra("infoDetail", myAdapter.getItem(index));
                    intent.putExtra("type", infoListData.getType());
                    intent.putExtra("myLocation", locationView.getText().toString()); // 값을 넣어줌
                    intent.putExtra("custInfo", custInfo);
                    startActivity(intent); // 변경
                    //startActivityForResult(intent, DatabaseHelper.DETAIL_CODE);
                }
            });
        }
        else if(state.equals("list_fail")){ // 로그인 실패 시
            Toast.makeText(infoListActivity.this, "목록 로딩에 실패하였습니다.", 100).show();
        }
        else{ // 그 외 오류
            Toast.makeText(infoListActivity.this, "서버와 연결 할 수 없습니다. 인터넷을 확인 해 주세요.", 100).show();
        }
    }
}