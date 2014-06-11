package com.kimtajo.guideMatching.listClass;

import android.util.Log;
import com.kimtajo.guideMatching.lib.HttpPostData;
import com.kimtajo.guideMatching.dataClass.Friend;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Hellomath on 2014. 4. 9..
 */
public class FriendList implements Serializable{
    private ArrayList<Friend> list;
    private int cust_no;
    final static String server = "http://hellomath.iptime.org/guideMatching/img/"; // server url

    public FriendList(int cust_no){
        this.cust_no = cust_no;
        this.list = new ArrayList<Friend>();
    }

    public String sendFriendList(String url){
        ArrayList<NameValuePair> myFriend = new ArrayList<NameValuePair>();
        myFriend.add(new BasicNameValuePair("cust_no", ""+this.getCustNo()));
        JSONArray result = HttpPostData.sendPostJSONArray(myFriend, url);
        if(result != null){
            for(int i=0;i<result.length();i++){
                try {
                    JSONObject json_data = result.getJSONObject(i);
                    String imgUrl = null;
                    if(result.getJSONObject(0).isNull("state")){
                        int no = json_data.getInt("FRIEND_CUST_NO");
                        String name =json_data.getString("FRIEND_NAME");
                        String area = json_data.getString("AREA");
                        String detail = json_data.getString("GUIDE_DETAIL");
                        String kakao = json_data.getString("GUIDE_KAKAO");
                        JSONArray arrayUseLang = json_data.getJSONArray("USE_LANG");
                        int friend_state = json_data.getInt("FRIEND_STATE");
                        String thumb = "";
                        //String thumb = server+imgUrl+json_data.getString("THUMB");
                        ArrayList<String> use_lang = new ArrayList<String>();
                        if(arrayUseLang != null){
                            for(int j=0;j<arrayUseLang.length();j++){
                                JSONObject tmp = arrayUseLang.getJSONObject(j);
                                use_lang.add(j, tmp.getString("NATIONAL_NAME"));
                            }
                        }
                        this.list.add(i, new Friend(no, name, area, use_lang, friend_state, kakao, detail));

                    }
                    else if(!result.getJSONObject(0).isNull("state")){
                        Log.d("list_fail", json_data.getString("state"));
                        return "list_fail";
                    }
                    //return "ok";
                }
                catch (JSONException e) {
                    Log.d("hellomath", "JSONException: "+e.getMessage().toString());
                    return "error";
                }
            }
            return "ok";

        }
        else{
            return "error";
        }

    }

    public String sendGuideFriendList(String url){
        ArrayList<NameValuePair> myFriend = new ArrayList<NameValuePair>();
        myFriend.add(new BasicNameValuePair("cust_no", ""+this.getCustNo()));
        JSONArray result = HttpPostData.sendPostJSONArray(myFriend, url);
        if(result != null){
            for(int i=0;i<result.length();i++){
                try {
                    JSONObject json_data = result.getJSONObject(i);
                    String imgUrl = null;
                    if(result.getJSONObject(0).isNull("state")){
                        int no = json_data.getInt("FRIEND_CUST_NO");
                        String name =json_data.getString("FRIEND_NAME");
                        String nick = json_data.getString("FRIEND_NICK");
                        int friend_state = json_data.getInt("FRIEND_STATE");
                        ArrayList<String> use_lang = new ArrayList<String>();
                        use_lang.add(0, json_data.getString("USE_LANG"));

                        this.list.add(i, new Friend(no, name, nick, use_lang, friend_state));

                    }
                    else if(!result.getJSONObject(0).isNull("state")){
                        Log.d("list_fail", json_data.getString("state"));
                        return "list_fail";
                    }
                    //return "ok";
                }
                catch (JSONException e) {
                    Log.d("hellomath", "JSONException: "+e.getMessage().toString());
                    return "error";
                }
            }
            return "ok";

        }
        else{
            return "error";
        }

    }
/*
    public String getNowAddr() {
    //return this.nowAddr;
        /*
        테스트용. 실제 주소 값을 넣을라면 return this.nowAddr; 을 쓰면 됨.
         *
        return "102 Jeongwang 1(il)-dong, Siheung-si, Gyeonggi-do, South Korea";
    }*/
    public int getCustNo(){ return this.cust_no; }
    public ArrayList<Friend> getList(){
        return this.list;
    }

}
