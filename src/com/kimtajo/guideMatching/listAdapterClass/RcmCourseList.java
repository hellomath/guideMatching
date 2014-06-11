package com.kimtajo.guideMatching.listAdapterClass;

import android.util.Log;
import com.kimtajo.guideMatching.lib.HttpPostData;
import com.kimtajo.guideMatching.dataClass.RcmCourse;
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
public class RcmCourseList implements Serializable{
    private long guide_cust_no;
    private ArrayList<RcmCourse> list;

    public RcmCourseList(long guide_cust_no){
        this.guide_cust_no = guide_cust_no;
        this.list = new ArrayList<RcmCourse>();
    }

    public String sendRcmList(String url){
        ArrayList<NameValuePair> myRcm = new ArrayList<NameValuePair>();
        myRcm.add(new BasicNameValuePair("cust_no", ""+this.getGuideCustNo()));
        JSONArray result = HttpPostData.sendPostJSONArray(myRcm, url);
        if(result != null){
            for(int i=0;i<result.length();i++){
                try {
                    JSONObject json_data = result.getJSONObject(i);
                    if(result.getJSONObject(0).isNull("state")){
                        int rcm_no = json_data.getInt("RCM_CODE");
                        String location =json_data.getString("RCM_LOCATION");
                        String time = json_data.getString("RCM_TIME");
                        String note = json_data.getString("RCM_NOTE");
                        this.list.add(i, new RcmCourse(rcm_no,location, time, note));
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

    public long getGuideCustNo() {
        return this.guide_cust_no;
    }
    public ArrayList<RcmCourse> getList(){
        return this.list;
    }

}
