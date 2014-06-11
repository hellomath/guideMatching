package com.kimtajo.guideMatching.tourist;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.*;
import com.kimtajo.guideMatching.R;
import com.kimtajo.guideMatching.dataClass.RcmCourse;

/**
 * Created by Hellomath on 2014. 4. 30..
 */
public class rcmDetailDialog extends Dialog {
    private TextView rcmLocation;
    private TextView rcmNote;
    private Button okBtn;
    private RcmCourse rcmCourse;

    final static String server = "http://hellomath.iptime.org/guideMatching"; // server url
    public rcmDetailDialog(Context context, RcmCourse rcmCourse) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.rcm_detail);
        this.rcmCourse = rcmCourse;

    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rcmLocation = (TextView)findViewById(R.id.rcmLocationDetail);
        rcmNote = (TextView)findViewById(R.id.rcmNoteDetail);
        okBtn = (Button)findViewById(R.id.rcmOkBtn);

        rcmLocation.setText(rcmCourse.getRcmLocation());
        rcmNote.setText("Time: "+rcmCourse.getRcmTime()+"\n"+rcmCourse.getRcmNote());

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
}