package com.kimtajo.guideMatching.listClass;

import android.util.Log;
import com.kimtajo.guideMatching.lib.HttpPostData;
import com.kimtajo.guideMatching.dataClass.Guide;
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
public class GuideList implements Serializable{
    private String nowAddr;
    private ArrayList<Guide> list;
    private int cust_no;
    final static String server = "http://hellomath.iptime.org/guideMatching/img/"; // server url

    public GuideList(int cust_no, String nowAddr){
        this.cust_no = cust_no;
        this.nowAddr = nowAddr;
        this.list = new ArrayList<Guide>();
    }

    public String sendGuideList(String url){
        ArrayList<NameValuePair> myGuide = new ArrayList<NameValuePair>();
        myGuide.add(new BasicNameValuePair("nowAddr", this.getNowAddr()));
        myGuide.add(new BasicNameValuePair("cust_no", ""+this.getCustNo()));
        JSONArray result = HttpPostData.sendPostJSONArray(myGuide, url);
        if(result != null){
            for(int i=0;i<result.length();i++){
                try {
                    JSONObject json_data = result.getJSONObject(i);
                    String imgUrl = null;
                    if(result.getJSONObject(0).isNull("state")){
                        int no = json_data.getInt("CUST_NO");
                        String name =json_data.getString("CUST_NAME");
                        String area = json_data.getString("AREA");
                        String detail = json_data.getString("GUIDE_DETAIL");
                        String kakao = json_data.getString("GUIDE_KAKAO");
                        JSONArray arrayUseLang = json_data.getJSONArray("USE_LANG");
                        int friend_chk = json_data.getInt("FRIEND_STATE");
                        String thumb = "";
                        //String thumb = server+imgUrl+json_data.getString("THUMB");
                        ArrayList<String> use_lang = new ArrayList<String>();
                        if(arrayUseLang != null){
                            for(int j=0;j<arrayUseLang.length();j++){
                                JSONObject tmp = arrayUseLang.getJSONObject(j);
                                use_lang.add(j, tmp.getString("NATIONAL_NAME"));
                            }
                        }

                        this.list.add(i, new Guide(no, name, area, use_lang, friend_chk, thumb, kakao, detail));
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

    public String getNowAddr() {
        //return this.nowAddr;
        /*
        테스트용. 실제 주소 값을 넣을라면 return this.nowAddr; 을 쓰면 됨.
         */
        return "102 Jeongwang 1(il)-dong, Siheung-si, Gyeonggi-do, South Korea";
    }
    public int getCustNo(){ return this.cust_no; }
    public ArrayList<Guide> getList(){
        return this.list;
    }

}
