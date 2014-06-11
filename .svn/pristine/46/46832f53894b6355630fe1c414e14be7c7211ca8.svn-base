package com.kimtajo.guideMatching;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import com.kimtajo.guideMatching.dataClass.Custom;
import com.kimtajo.guideMatching.guide.guideMainActivity;
import com.kimtajo.guideMatching.lib.HttpDataGet;
import com.kimtajo.guideMatching.tourist.locationActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 로그인을 수행하는 Activity
 * 서버에 저장되어있는 광고 이미지와 광고의 url을 불러온다.
 * 불러온 이미지와 url은 Activity 상단의 3개 광고 이미지에 넣어주며,
 * 광고 클릭 시 webViewActivity로 url을 넘겨준다.
 *
 * 로그인 시 서버내의 DB에 있는 CUSTOM 테이블에 아이디와 비밀번호를 검색하기 위해
 * 서배 내에 있는 /login/sendLoginData.php 로 POST방식으로 아이디와 비밀번호를 넘겨주며
 * 확인이 완료되면 회원번호, 회원 닉네임, 전화번호, 회원 타입(T: 관광객, G: 가이드)을 가져와
 * Custom 클래스에 데이터를 저장시켜준다.
 *
 * 또한 회원의 타입이 관광객인 경우 locationActivity로 이동한다.
 * 가이드라면 guideMainActivity로 이동한다.
 *
 */
public class loginActivity extends Activity {
    private EditText loginId;
    private EditText loginPwd;
    private ImageButton loginImgBtn;
    private ImageButton signImgBtn;
    private ImageButton adImgBtn[];

    final static String server = "http://hellomath.iptime.org/guideMatching"; // server url
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);



        String imgUrl = server+"/img/"; // 이미지 url 설정
        String url = server+"/ad/loadImg.php"; // db에서 검색
        final String advert_url[] = new String[3];

        loginId = (EditText)findViewById(R.id.loginId);
        loginPwd = (EditText)findViewById(R.id.loginPwd);
        loginImgBtn = (ImageButton)findViewById(R.id.loginImgBtn);

        adImgBtn = new ImageButton[3];
        adImgBtn[0] = (ImageButton)findViewById(R.id.noticeImgBtn);
        adImgBtn[1] = (ImageButton)findViewById(R.id.ad1ImgBtn);
        adImgBtn[2] = (ImageButton)findViewById(R.id.ad2ImgBtn);

        /***
         *
         * 광고 이미지와 그것에 설정 된 url을 불러옴
         *
         * */
        JSONArray test = HttpDataGet.changeStringToJSON(url);
        if(test != null){
            for(int i=0;i<test.length();i++){
                try {
                    JSONObject json_data = test.getJSONObject(i);
                    if(test.getJSONObject(0).isNull("state")){
                        String img_src= json_data.getString("IMG_SRC");
                        advert_url[i] = json_data.getString("ADVERT_URL"); // ArrayIndexOutOfBoundsException 에러남

                        URL myUrl = new URL(imgUrl+img_src);
                        InputStream inputStream = (InputStream)myUrl.getContent();
                        Drawable drawable = Drawable.createFromStream(inputStream, null);
                        adImgBtn[i].setImageDrawable(drawable);
                    }
                    else{
                        Log.d("hellomath", "Error!");
                        break;
                    }
                }
                catch (JSONException e) {
                    Log.d("hellomath", "JSONException: "+e.getMessage().toString());
                } catch (MalformedURLException e) {
                    Log.d("hellomath", "MalformedURLException: "+e.getMessage().toString());
                } catch (IOException e) {
                    Log.d("hellomath", "IOException: "+e.getMessage().toString());
                }
            }
        }
        /**
         * 상단 이미지 버튼 터치 시 실행되는 작업
         */
        adImgBtn[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(loginActivity.this, webViewActivity.class); // 햔재 클래스, 바뀔 클래스
                intent.putExtra("target", advert_url[0]);
                startActivity(intent); // 변경
            }
        });

        adImgBtn[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(loginActivity.this, webViewActivity.class); // 햔재 클래스, 바뀔 클래스
                intent.putExtra("target", advert_url[1]);
                startActivity(intent); // 변경
            }
        });

        adImgBtn[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(loginActivity.this, webViewActivity.class); // 햔재 클래스, 바뀔 클래스
                intent.putExtra("target", advert_url[2]);
                startActivity(intent); // 변경
            }
        });
        /**
         *
         * 로그인 버튼 터치 시 동작 수행
         *
         */
        loginImgBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Custom loginData =new Custom(loginId.getText().toString(), loginPwd.getText().toString());
                String url = server+"/login/sendLoginData.php";

                String state = loginData.sendLoginData(url); // 아이디와 비밀번호를 보냄
                /**
                 * 회원 타입이 관광객이라면 locationActivity 이동, Custom 클래스도 넘겨줌
                 */
                if(state.equals("ok")){ // 상태값이 ok인 경우 다음 엑티비티로
                    if(loginData.getCustType().equals("T")){
                        Intent intent = new Intent(loginActivity.this, locationActivity.class); // 햔재 클래스, 바뀔 클래스
                        intent.putExtra("custInfo", loginData); // 값을 넣어줌
                        startActivity(intent); // 변경
                        finish();
                    }
                    /**
                     * 회원 타입이 가이드라면 guideMainActivity 이동, Custom 클래스도 넘겨줌
                     */
                    else if(loginData.getCustType().equals("G")){
                        Intent intent = new Intent(loginActivity.this, guideMainActivity.class); // 햔재 클래스, 바뀔 클래스
                        intent.putExtra("custInfo", loginData); // 값을 넣어줌
                        startActivity(intent); // 변경
                        finish();
                    }



                }
                else if(state.equals("login_fail")){ // 로그인 실패 시
                    Toast.makeText(loginActivity.this, "로그인에 실패하였습니다.", 100).show();
                }
                else{ // 그 외 오류
                    Toast.makeText(loginActivity.this, "서버와 연결 할 수 없습니다. 인터넷을 확인 해 주세요.", 100).show();
                }
            }
        });

        signImgBtn = (ImageButton)findViewById(R.id.signImgBtn);

        /**
         *
         * 회원가입 버튼 터치 시 동작
         *
         */

        signImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signup = new Intent(getApplicationContext(), signupActivity.class);
                startActivity(signup);
            }
        });
    }

}

