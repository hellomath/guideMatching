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
 * Created by Hellomath on 2014. 3. 18..
 */
public class Schedule implements Serializable{
    private int scdNo;
    //private int scdCustNo;
    private String scdName;
    private String scdDatetime;
    private String scdInfo;
    private String scdPlace;
    private String scdMemo;
    private char scdMeetOpnYn;
    private ArrayList<String> meetList;


    public Schedule(int scdNo,/* int scdCustNo,*/ String scdName, String scdDatetime, String scdInfo, String scdPlace, String scdMemo, char scdMeetOpnYn, ArrayList<String> meetList){

        this.scdNo = scdNo;
        //this.scdCustNo = scdCustNo;
        this.scdName = scdName;
        this.scdDatetime = scdDatetime;
        this.scdInfo = scdInfo;
        this.scdPlace = scdPlace;
        this.scdMemo = scdMemo;
        this.scdMeetOpnYn = scdMeetOpnYn;
        this.meetList = meetList;

    }

    public int getScdNo(){
        return this.scdNo;
    }/*
    public int getScdCustNo() {
        return scdCustNo;
    }*/
    public String getScdDatetime() {
        return scdDatetime;
    }
    public String getScdPlace() {
        return scdPlace;
    }
    public String getScdName() {
        return this.scdName;
    }
    public String getScdMemo() {
        return scdMemo;
    }
    public String getScdInfo() {
        return scdInfo;
    }
    public char getScdMeetOpnYn() {
        return scdMeetOpnYn;
    }
    public ArrayList<String> getMeetList() {
        return meetList;
    }

    public int chkScdJoin(int cust_no, String url){
        ArrayList<NameValuePair> chk_join = new ArrayList<NameValuePair>();
        chk_join.add(new BasicNameValuePair("scd_no", ""+this.getScdNo()));
        chk_join.add(new BasicNameValuePair("meet_cust_no", ""+cust_no));
        JSONArray result = HttpPostData.sendPostJSONArray(chk_join, url);
        if(result != null){
            try {
                JSONObject json_data = result.getJSONObject(0);
                return json_data.getInt("chk");
            }
            catch (JSONException e) {
                Log.d("Custom_SendLoginData", e.getMessage().toString());
                return 999;
            }
        }
        else{
            return 999;
        }
    }
    public String joinScdData(int meetCustNo, String url){

        ArrayList<NameValuePair> join = new ArrayList<NameValuePair>();
        join.add(new BasicNameValuePair("meet_cust_no", meetCustNo+""));
        join.add(new BasicNameValuePair("scd_no", this.getScdNo()+""));
        JSONArray result = HttpPostData.sendPostJSONArray(join, url);
        if(result != null){
            try {
                JSONObject json_data = result.getJSONObject(0);
                return json_data.getString("state");
            }
            catch (JSONException e) {
                Log.d("Custom_SendLoginData", e.getMessage().toString());
                return "error";
            }
        }
        else{
            return "error";
        }

    }
}
