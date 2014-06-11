package com.kimtajo.guideMatching.guide;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import com.kimtajo.guideMatching.R;
import com.kimtajo.guideMatching.dataClass.Custom;
import com.kimtajo.guideMatching.dataClass.Friend;
import com.kimtajo.guideMatching.listAdapterClass.FriendListAdapter;
import com.kimtajo.guideMatching.listClass.FriendList;

import java.util.ArrayList;

/**
 * Created by Hellomath on 2014. 5. 2..
 */
public class guideFriendListActivity extends ListActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide_list_view);

        Intent intent = getIntent();
        final Custom custInfo = (Custom)intent.getSerializableExtra("custInfo"); // 회원 정보를 받아옴
        final FriendList friendListInfo = (FriendList)intent.getSerializableExtra("friendList");

        ArrayList<Friend> mlist = new ArrayList<Friend>();

        mlist.addAll(friendListInfo.getList());
        final FriendListAdapter myAdapter = new FriendListAdapter(this, R.layout.guide_item, mlist, custInfo);
        setListAdapter(myAdapter);

    }
}