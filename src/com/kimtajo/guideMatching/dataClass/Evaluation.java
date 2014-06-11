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
 * Created by Hellomath on 2014. 4. 18..
 */
public class Evaluation implements Serializable{
    private char type;
    private long typeNo;
    private int custNo;


    public Evaluation(char type, long typeNo, int custNo){
        this.type = type;
        this.typeNo = typeNo;
        this.custNo = custNo;
    }

    public String sendEvalData(float evalPoint, String evalNote, String url){
        ArrayList<NameValuePair> evalData = new ArrayList<NameValuePair>();
        evalData.add(new BasicNameValuePair("type", this.getType()+""));
        evalData.add(new BasicNameValuePair("typeNo", this.getTypeNo()+""));
        evalData.add(new BasicNameValuePair("custNo", this.getCustNo()+""));
        evalData.add(new BasicNameValuePair("evalPoint", evalPoint+""));
        evalData.add(new BasicNameValuePair("evalNote", evalNote));

        JSONArray result = HttpPostData.sendPostJSONArray(evalData, url);
        if(result != null){
            try {
                JSONObject json_data = result.getJSONObject(0);
                String returnType =json_data.getString("state");
                return returnType;
            }
            catch (JSONException e) {
                Log.d("Eval_SendEvalData", e.getMessage().toString());
                return "error";
            }
        }
        else{
            return "error";
        }
    }


    public char getType() {
        return type;
    }

    public long getTypeNo() {
        return typeNo;
    }

    public int getCustNo() {
        return custNo;
    }
}
