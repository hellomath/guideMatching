package com.kimtajo.guideMatching.dataClass;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Hellomath on 2014. 4. 28..
 * 가이드와 관련된 클래스
 */
public class Guide implements Serializable {

    private int guide_cust_no; //
    private String name; //
    private String nick;
    private String tour_area;
    private ArrayList<String> use_lang;
    private int friend_state;
    private String thumb_url;
    private String guide_kakao;
    private String guide_detail;


    public Guide(int guide_cust_no, String name, String tour_area, ArrayList<String> use_lang, int friend_state, String thumb_url, String guide_kakao, String guide_detail){
        this.guide_cust_no = guide_cust_no;
        this.name = name;
        this.tour_area = tour_area;
        this.use_lang = use_lang;
        this.friend_state = friend_state;
        this.thumb_url = thumb_url;
        this.guide_kakao = guide_kakao;
        this.guide_detail = guide_detail;
    }

    public Guide(int guide_cust_no, String name, String nick, ArrayList<String> use_lang, int friend_state){
        this.guide_cust_no = guide_cust_no;
        this.nick = nick;
        this.name = name;
        this.use_lang = use_lang;
        this.friend_state = friend_state;
    }

    public int getGuideCustNo(){
        return this.guide_cust_no;
    }
    public String getName(){
        return this.name;
    }
    public String getNick(){
        return this.nick;
    }
    public String getTourArea(){
        return this.tour_area;
    }
    public ArrayList<String> getUseLang(){
        return this.use_lang;
    }
    public String getThumbUrl(){ return this.thumb_url; }
    public String getGuideKakao(){ return this.guide_kakao; }
    public String getGuideDetail() { return this.guide_detail; }
    public int getFriendState(){ return this.friend_state; }
    public void setFriendState(int chk){ this.friend_state = chk; }
}
