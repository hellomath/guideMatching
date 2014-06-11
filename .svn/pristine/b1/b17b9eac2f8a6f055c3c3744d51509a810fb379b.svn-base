package com.kimtajo.guideMatching.lib;

import android.util.Log;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by Hellomath on 2014. 3. 11..
 */
public class HttpPostData {
    public static int sendPost(ArrayList<NameValuePair> data, String url){
        HttpEntity resEntity = null;
        String returnData = null;
        int result = 999;
        HttpClient http = new DefaultHttpClient();
        try {
            HttpParams params = http.getParams();
            HttpConnectionParams.setConnectionTimeout(params, 5000);
            HttpConnectionParams.setSoTimeout(params, 5000);

            HttpPost httpPost = new HttpPost(url);
            UrlEncodedFormEntity entityRequest = new UrlEncodedFormEntity(data, "UTF-8");

            httpPost.setEntity(entityRequest);
            HttpResponse responsePost = http.execute(httpPost);
            resEntity = responsePost.getEntity();
            returnData = EntityUtils.toString(resEntity);
            result = Integer.parseInt(returnData);

        }catch(Exception e){
            result = 999;
        }finally {
            return result;
        }
    }

    public static JSONArray sendPostJSONArray(ArrayList<NameValuePair> data, String url){
        HttpEntity resEntity = null;
        String result = null;
        HttpClient http = new DefaultHttpClient();
        JSONArray jArray = null;
        try {
            HttpParams params = http.getParams();
            HttpConnectionParams.setConnectionTimeout(params, 5000);
            HttpConnectionParams.setSoTimeout(params, 5000);

            HttpPost httpPost = new HttpPost(url);
            UrlEncodedFormEntity entityRequest = new UrlEncodedFormEntity(data, "UTF-8");

            httpPost.setEntity(entityRequest);
            HttpResponse responsePost = http.execute(httpPost);
            resEntity = responsePost.getEntity();
            result = EntityUtils.toString(resEntity);

            Log.d("hellomath", "Result: "+result);

            try {
                jArray = new JSONArray(result);
            } catch (JSONException e) {
                Log.d("hellomath", "JSONException: "+e.getMessage().toString());
            }


        }catch(Exception e){
            Log.d("hellomath", "Exception: "+e.getMessage().toString());
        }finally {
            return jArray;
        }
    }

}
