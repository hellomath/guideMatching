package com.kimtajo.guideMatching.dataClass;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Hellomath on 2014. 4. 30..
 */
public class Friend extends Guide implements Serializable {

    public Friend(int friend_cust_no, String friend_name, String friend_area, ArrayList<String> friend_use_lang, int friend_chk, String friend_kakao, String friend_detail){
        super(friend_cust_no, friend_name, friend_area, friend_use_lang, friend_chk, "", friend_kakao, friend_detail);

    }

    public Friend(int friend_cust_no, String friend_name, String friend_nick, ArrayList<String> friend_use_lang, int friend_chk){
        super(friend_cust_no, friend_name, friend_nick, friend_use_lang, friend_chk);

    }

    public int getFriendCustNo(){ return super.getGuideCustNo(); }
    public String getFriendName(){ return super.getName(); }
    public String getFriendNick(){ return super.getNick(); }
    public String getFriendArea() { return super.getTourArea(); }
    public ArrayList<String> getFriendUseLang(){ return super.getUseLang(); }
    public int getFriendState() { return super.getFriendState(); }
    public String getFriendDetail(){ return super.getGuideDetail(); }
    public String getFriendKakao(){ return super.getGuideKakao(); }


}
