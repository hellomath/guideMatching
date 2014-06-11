package com.kimtajo.guideMatching.dataClass;

import android.util.Log;
import com.kimtajo.guideMatching.lib.HttpPostData;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Hellomath on 2014. 4. 28..
 */
public class RcmCourse implements Serializable {

    private long cust_no;
    private int rcm_code;
    private String rcm_time;
    private String rcm_location;
    private String rcm_note;

    public RcmCourse(){
    }
    public RcmCourse(int rcm_code, String rcm_location, String rcm_time, String rcm_note){
        this.rcm_code = rcm_code;
        this.rcm_location = rcm_location;
        this.rcm_time = rcm_time;
        this.rcm_note = rcm_note;
    }
    public int getRcmCode() { return this.rcm_code; }
    public long getGuideCustNo(){
        return this.cust_no;
    }
    public String getRcmTime(){
        return this.rcm_time;
    }
    public String getRcmLocation(){ return this.rcm_location; }
    public String getRcmNote(){ return rcm_note; }

    public void setRcmCode(int rcm_code){ this.rcm_code=rcm_code; }
    public void serRcmCustNo(int cust_no){ this.cust_no=cust_no; }
    public void setRcmTime(String rcm_time){ this.rcm_time=rcm_time; }
    public void setRcmLocation(String rcm_location){ this.rcm_location=rcm_location; }
    public void setRcmNote(String rcm_note){ this.rcm_note=rcm_note; }


    public String insertRcmData(String type, int cust_no, String url){

        ArrayList<NameValuePair> rcmInsert = new ArrayList<NameValuePair>();
        rcmInsert.add(new BasicNameValuePair("type", type));
        rcmInsert.add(new BasicNameValuePair("cust_no", cust_no+""));
        rcmInsert.add(new BasicNameValuePair("rcm_code", this.getRcmCode()+""));
        rcmInsert.add(new BasicNameValuePair("rcm_location", this.getRcmLocation()));
        rcmInsert.add(new BasicNameValuePair("rcm_time", this.getRcmTime()));
        rcmInsert.add(new BasicNameValuePair("rcm_note", this.getRcmNote()));

        JSONArray result = HttpPostData.sendPostJSONArray(rcmInsert, url);
        if(result != null){
            try {
                JSONObject json_data = result.getJSONObject(0);
                return json_data.getString("state");
            }
            catch (JSONException e) {
                return "error";
            }
        }
        else{
            return "error";
        }

    }

}
