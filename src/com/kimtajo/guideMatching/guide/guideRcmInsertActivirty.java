package com.kimtajo.guideMatching.guide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.kimtajo.guideMatching.R;
import com.kimtajo.guideMatching.dataClass.Custom;
import com.kimtajo.guideMatching.dataClass.RcmCourse;
import com.kimtajo.guideMatching.listAdapterClass.RcmCourseList;

/**
 * Created by Hellomath on 2014. 5. 2..
 */
public class guideRcmInsertActivirty extends Activity {
    private Custom custInfo;
    private RcmCourse rcmCourse;
    private String type;
    private EditText location;
    private EditText time;
    private EditText note;
    final static String server = "http://hellomath.iptime.org/guideMatching"; // server url
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rcm_insert);

        Intent intent = getIntent();
        custInfo = (Custom)intent.getSerializableExtra("custInfo"); // 회원 정보를 받아옴
        rcmCourse = (RcmCourse)intent.getSerializableExtra("myRcmCourse");

        type = intent.getStringExtra("type");

        location = (EditText)findViewById(R.id.rcmLocationInsert);
        time = (EditText)findViewById(R.id.rcmTimeInsert);
        note = (EditText)findViewById(R.id.rcmNoteInsert);
        Button ok = (Button)findViewById(R.id.rcmOkInsert);
        Button cancel = (Button)findViewById(R.id.rcmCancelInsert);

        if(type.equals("U")){
            location.setText(rcmCourse.getRcmLocation());
            time.setText(rcmCourse.getRcmTime());
            note.setText(rcmCourse.getRcmNote());

        }
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String url = server+"/info/insertRcmData.php";
                rcmCourse.setRcmLocation(location.getText().toString());
                rcmCourse.setRcmTime(time.getText().toString());
                rcmCourse.setRcmNote(note.getText().toString());
                String state = rcmCourse.insertRcmData(type, custInfo.getCustNo(),url);

                if(state.equals("ok")){
                    Intent intent = new Intent(guideRcmInsertActivirty.this, guideRcmListActivity.class);

                    String url2 = server+"/info/getRcmList.php";
                    RcmCourseList rcmCourseList = new RcmCourseList(custInfo.getCustNo());

                    String state2 = rcmCourseList.sendRcmList(url2);

                    if(state2.equals("ok")){ // 상태값이 ok인 경우 다음 엑티비티로
                        intent.putExtra("custInfo", custInfo);
                        intent.putExtra("rcmList", rcmCourseList);
                    }
                    else if(state2.equals("list_fail")){ // 로그인 실패 시
                        intent.putExtra("custInfo", custInfo);
                    }
                    else{ // 그 외 오류
                        intent.putExtra("custInfo", custInfo);
                    }
                    startActivity(intent); // 변경
                    Toast.makeText(getApplicationContext(), "수정 완료", 1000).show();
                    finish();
                }
                else
                    Toast.makeText(getApplicationContext(), "수정 실패", 1000).show();
            }
        });
    }
}