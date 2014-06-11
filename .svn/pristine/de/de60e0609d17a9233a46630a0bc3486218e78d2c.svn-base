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
 * 고객 정보와 관련된 클래스
 */
public class Custom implements Serializable{
    private int cust_no; // 고객 식별 번호
    private String cust_id; // 아이디
    private String cust_pwd; // 비밀번호
    private String cust_name; // 이름
    private String cust_nick; // 닉네임
    private String hp_no; // 전화번호
    private String national_code; // 국적
    private String cust_type; // 고객 타입
    private String cust_inf_opn_yn; // 고객정보 열람 on/off
    //private ArrayList<NameValuePair> cust_info;

    /**
     * 회원가입 시 사용하는 생성자
     *
     * @param id 아이디
     * @param pwd 비밀번호
     * @param name 이름
     * @param nick 닉네임
     * @param hp_no 핸드폰 번호
     * @param national_code 국가 코드
     * @param type 회원 타입
     * @param inf_opn_yn 고객정보 열람 on/off
     */
    public Custom(String id, String pwd, String name, String nick, String hp_no, String national_code, String type, String inf_opn_yn){
        this.cust_id = id;
        this.cust_pwd = pwd;
        this.cust_name = name;
        this.cust_nick = nick;
        this.hp_no = hp_no;
        this.national_code = national_code;
        this.cust_type = type;
        this.cust_inf_opn_yn = inf_opn_yn;

    }

    /**
     * 로그인 체크 시 사용하는 생성자
     * @param id 아이디
     * @param pwd 비밀번호
     */
    public Custom(String id, String pwd){
        this.cust_id = id;
        this.cust_pwd = pwd;
    }

    /**
     * 로그인 완료 시 회원정보를 저장함
     * @param cust_no 회원 식별 번호
     * @param cust_nick 회원 닉네임
     * @param hp_no 전화번호
     * @param cust_type 회원 타입
     */
    public void setCustData(int cust_no, String cust_nick, String hp_no, String cust_type){
        this.cust_no = cust_no;
        this.cust_nick = cust_nick;
        this.hp_no = hp_no;
        this.cust_type = cust_type;

    }


    /**
     * 회원 가입 시 회원정보를 전달한다.
     * url은 /join/sendJoinData.php 를 지정해주면 된다.
     * @param url
     * @return
     */
    public int sendJoinData(String url){
        ArrayList<NameValuePair> cust_info = new ArrayList<NameValuePair>();
        cust_info.add(new BasicNameValuePair("cust_id", this.getCustId()));
        cust_info.add(new BasicNameValuePair("cust_pwd", this.getCustPwd()));
        cust_info.add(new BasicNameValuePair("cust_name", this.getCustName()));
        cust_info.add(new BasicNameValuePair("cust_nick", this.getCustNick()));
        cust_info.add(new BasicNameValuePair("hp_no", this.getHpNo()));
        cust_info.add(new BasicNameValuePair("national_code", this.getNationalCode()));
        cust_info.add(new BasicNameValuePair("cust_type", this.getCustType()));
        cust_info.add(new BasicNameValuePair("cust_inf_opn_yn", this.getCustInfOpnYn()));

        int result = HttpPostData.sendPost(cust_info, url);
        return result;
    }

    /**
     * 로그인 시 회원 정보를 가져온다.
     * url은 /login/sendLoginData.php 를 지정해주면 된다.
     * @param url
     * @return
     */
    public String sendLoginData(String url){
        ArrayList<NameValuePair> cust_info = new ArrayList<NameValuePair>();
        cust_info.add(new BasicNameValuePair("cust_id", this.getCustId()));
        cust_info.add(new BasicNameValuePair("cust_pwd", this.getCustPwd()));
        JSONArray result = HttpPostData.sendPostJSONArray(cust_info, url);
        if(result != null){
            try {
                JSONObject json_data = result.getJSONObject(0);
                if(result.getJSONObject(0).isNull("state")){
                    Log.d("Hellomath", json_data.getString("CUST_NICK"));
                    this.setCustData(Integer.parseInt(json_data.getString("CUST_NO")), json_data.getString("CUST_NICK"), json_data.getString("HP_NO"), json_data.getString("CUST_TYPE"));
                    return "ok";
                }
                else if(!result.getJSONObject(0).isNull("state")){
                        Log.d("login_fail", json_data.getString("state"));
                    return "login_fail";
                }
            }
            catch (JSONException e) {
                Log.d("Custom_SendLoginData", e.getMessage().toString());
                return "error";
            }
            return "error";
        }
        else{
            return "error";
        }
    }
    public int getCustNo(){
        return this.cust_no;
    }
    public String getCustId(){
        return this.cust_id;
    }
    public String getCustPwd(){
        return this.cust_pwd;
    }
    public String getCustName(){
        return this.cust_name;
    }

    public String getCustNick(){
        return this.cust_nick;
    }
    public String getHpNo(){
        return this.hp_no;
    }
    public String getNationalCode(){
        return this.national_code;
    }
    public String getCustType(){
        return this.cust_type;
    }
    public String getCustInfOpnYn(){
        return this.cust_inf_opn_yn;
    }
}
