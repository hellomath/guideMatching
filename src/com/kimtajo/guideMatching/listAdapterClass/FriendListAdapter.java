package com.kimtajo.guideMatching.listAdapterClass;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import com.kimtajo.guideMatching.R;
import com.kimtajo.guideMatching.dataClass.Custom;
import com.kimtajo.guideMatching.dataClass.Friend;

import java.util.ArrayList;

/**
 * Created by Hellomath on 2014. 4. 7..
 */
public class FriendListAdapter extends ArrayAdapter<Friend> {
    private final ArrayList<Friend> items;
    Context mContext;
    private final Custom custInfo;
    final static String server = "http://hellomath.iptime.org/guideMatching"; // server url

    public FriendListAdapter(Context context, int textViewResourceId, ArrayList<Friend> items, Custom custInfo){
        super(context, textViewResourceId, items);
        this.items = items;
        this.custInfo = custInfo;
        mContext = context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View v = convertView;
        if(v == null){
            LayoutInflater vi = LayoutInflater.from(mContext.getApplicationContext());
            if(custInfo.getCustType().equals("T"))
                v = vi.inflate(R.layout.guide_item, null);
            else if(custInfo.getCustType().equals("G"))
                v = vi.inflate(R.layout.guide_friend_item, null);

        }
        final Friend item = items.get(position);


        if(item != null){
            TextView name;
            TextView area;
            TextView use_lang;
            final ImageButton add_friend;
            if(custInfo.getCustType().equals("T")){
                name = (TextView)v.findViewById(R.id.guideName);
                area = (TextView)v.findViewById(R.id.guideArea);
                use_lang = (TextView)v.findViewById(R.id.guideUseLang);
                add_friend = (ImageButton)v.findViewById(R.id.guideAddFriend);

                if(name != null){ name.setText(item.getFriendName()); }
                if(area != null) area.setText(item.getFriendArea());
                if(use_lang != null){
                    String langs = "";
                    for(int i=0;i<item.getFriendUseLang().size();i++){
                        if(i<item.getFriendUseLang().size()-1)
                            langs += item.getFriendUseLang().get(i)+" / ";
                        else
                            langs += item.getFriendUseLang().get(i);
                    }
                    use_lang.setText(langs);
                }
                if(add_friend != null){
                    add_friend.setFocusable(false); // listview 내에 button이 있으면 listview의 item이 안눌려짐. 눌러지게 할라면 이거 설정
                    Log.d("hellomath2", item.getFriendState()+"");

                    if(item.getFriendState() == 200 || item.getFriendState() == 000)
                        add_friend.setImageResource(R.drawable.friend_wait);
                    else if(item.getFriendState() == 100)
                        add_friend.setImageResource(R.drawable.friend_already);
                    else if(item.getFriendState() == 999)
                        add_friend.setImageResource(R.drawable.friend_add);

                }

            }else if(custInfo.getCustType().equals("G")){
                name = (TextView)v.findViewById(R.id.friendNameForGuide);
                area = (TextView)v.findViewById(R.id.friendNickForGuide);
                use_lang = (TextView)v.findViewById(R.id.friendCountryForGuide);
                add_friend = (ImageButton)v.findViewById(R.id.friendStateForGuide);

                if(name != null) name.setText(item.getFriendName());
                if(area != null) area.setText(item.getFriendNick());
                if(use_lang != null) use_lang.setText(item.getFriendUseLang().get(0));
                if(add_friend != null){
                    add_friend.setFocusable(false); // listview 내에 button이 있으면 listview의 item이 안눌려짐. 눌러지게 할라면 이거 설정
                    Log.d("hellomath2", item.getFriendState()+"");

                    if(item.getFriendState() == 200 || item.getFriendState() == 000)
                        add_friend.setImageResource(R.drawable.friend_wait);
                    else if(item.getFriendState() == 100)
                        add_friend.setImageResource(R.drawable.friend_already);
                    else if(item.getFriendState() == 999)
                        add_friend.setImageResource(R.drawable.friend_add);

                }

            }

        }

        return v;
    }
}