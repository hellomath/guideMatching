package com.kimtajo.guideMatching.guide;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import com.kimtajo.guideMatching.R;
import com.kimtajo.guideMatching.dataClass.Custom;
import com.kimtajo.guideMatching.dataClass.Guide;
import com.kimtajo.guideMatching.dataClass.RcmCourse;
import com.kimtajo.guideMatching.listAdapterClass.RcmCourseList;
import com.kimtajo.guideMatching.listAdapterClass.RcmListAdapter;
import com.kimtajo.guideMatching.startActivity;
import com.kimtajo.guideMatching.tourist.rcmDetailDialog;

import java.util.ArrayList;

/**
 * Created by Hellomath on 2014. 5. 2..
 */
public class guideRcmListActivity extends ListActivity {
    private Custom custInfo;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide_list_view);
        Intent intent = getIntent();
        custInfo = (Custom)intent.getSerializableExtra("custInfo"); // 회원 정보를 받아옴
        final RcmCourseList rcmList = (RcmCourseList)intent.getSerializableExtra("rcmList");

        ArrayList<RcmCourse> mlist = new ArrayList<RcmCourse>();
        if(rcmList != null){
            mlist.addAll(rcmList.getList());
            final RcmListAdapter myAdapter = new RcmListAdapter(this, R.layout.rcm_item, mlist);
            setListAdapter(myAdapter);

            getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                    Intent intent = new Intent(guideRcmListActivity.this, guideRcmInsertActivirty.class);
                    intent.putExtra("myRcmCourse", myAdapter.getItem(position));
                    intent.putExtra("custInfo", custInfo);
                    intent.putExtra("type", "U");
                    startActivity(intent);

                }
            });

        }
        else{
            mlist.add(0, new RcmCourse(0, "-", "-", "-"));
            final RcmListAdapter myAdapter = new RcmListAdapter(this, R.layout.rcm_item, mlist);
            setListAdapter(myAdapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem itemAdd = menu.add(0,1,0, "추가하기");
        MenuItem itemExit = menu.add(0,2,0,"뒤로가기");
        itemAdd.setIcon(android.R.drawable.ic_menu_add);
        itemExit.setIcon(android.R.drawable.ic_menu_close_clear_cancel);
        return super.onCreateOptionsMenu(menu);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case 1:
                Intent intent = new Intent(guideRcmListActivity.this, guideRcmInsertActivirty.class);
                intent.putExtra("myRcmCourse", new RcmCourse());
                intent.putExtra("custInfo", custInfo);
                intent.putExtra("type", "I");
                startActivity(intent);
                finish();
                break;
            case 2:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}