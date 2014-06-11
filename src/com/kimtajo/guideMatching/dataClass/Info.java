package com.kimtajo.guideMatching.dataClass;

import android.util.Log;
import android.view.View;
import com.kimtajo.guideMatching.lib.HttpPostData;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Hellomath on 2014. 4. 7..
 */
public class Info implements Serializable{
    private String name;
    private char type;
    private String addr;
    private String tel_no;
    private double eval_point;
    private int eval_count;
    private long type_id;
    private String thumb_url;
    private String type_info;

    public Info(long type_id, String name, char type, String addr, String type_info, String tel_no, double eval_point, int eval_count, String thumb_url){
        this.type_id = type_id;
        this.name = name;
        this.type = type;
        this.addr = addr;
        this.type_info = type_info;
        this.tel_no = tel_no;
        this.eval_point = eval_point;
        this.eval_count = eval_count;
        this.thumb_url = thumb_url;
    }
    public float getEvalValue(String url){

        ArrayList<NameValuePair> evalData = new ArrayList<NameValuePair>();
        evalData.add(new BasicNameValuePair("target_no", this.getTypeId()+""));
        evalData.add(new BasicNameValuePair("eval_type", this.getType()+""));

        JSONArray result = HttpPostData.sendPostJSONArray(evalData, url);
        if(result != null){
            try {
                JSONObject json_data = result.getJSONObject(0);
                //String returnType =json_data.getString("EVAL_POINT");

                float returnType = (float)json_data.getDouble("EVAL_POINT");

                Log.d("hellomath2", "no: "+this.getTypeId()+" type:"+this.getType()+" eval:"+returnType);

                return returnType;
            }
            catch (JSONException e) {
                Log.d("Eval_SendEvalData", e.getMessage().toString());
                return 0;
            }
        }
        else{
            return 0;
        }
    }
    public long getTypeId(){
        return this.type_id;
    }
    public String getName(){
        return this.name;
    }
    public char getType(){
        return this.type;
    }
    public String getAddr(){
        return this.addr;
    }
    public String getTypeInfo(){ return this.type_info; }
    public String getTelNo(){
        return this.tel_no;
    }
    public double getEvalPoint(){
        return this.eval_point;
    }
    public int getEvalCount(){
        return this.eval_count;
    }
    public String getThumbUrl(){
        return thumb_url;
    }
}
