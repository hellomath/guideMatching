package com.kimtajo.guideMatching.tourist;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.*;
import com.kimtajo.guideMatching.R;
import com.kimtajo.guideMatching.dataClass.Custom;
import com.kimtajo.guideMatching.dataClass.Evaluation;
import com.kimtajo.guideMatching.tourist.infoDetailActivity;

/**
 * Created by Hellomath on 2014. 4. 18..
 */
public class insertEvalDialog extends Dialog {
    private RatingBar evalPoint;
    private EditText evalNote;
    private Button insertOk;
    private Button insertCancel;
    private Custom custInfo;
    private Evaluation eval;
    final static String server = "http://hellomath.iptime.org/guideMatching"; // server url
    public insertEvalDialog(Context context, Evaluation eval) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.insert_eval);
        this.eval = eval;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        evalPoint = (RatingBar)findViewById(R.id.inputEvalPoint);
        evalNote = (EditText)findViewById(R.id.inputEvalNote);
        insertOk = (Button)findViewById(R.id.inputEvalOk);
        insertCancel = (Button)findViewById(R.id.inputEvalCancel);
        final String url = server+"/info/sendEvalData.php";
        insertOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String result = eval.sendEvalData(evalPoint.getRating(), evalNote.getText().toString(), url);
                if(result.equals("insert"))         Toast.makeText(getContext(), "입력 완료!", 1000).show();
                else if(result.equals("update"))    Toast.makeText(getContext(), "수정 완료!", 1000).show();
                else                                Toast.makeText(getContext(), "평가 입력에 실패하였습니다.", 1000).show();
                Intent intent = new Intent(getContext(), infoDetailActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra("evalPoint", evalPoint.getRating());
                getContext().startActivity(intent);
                
                dismiss();
            }
        });
        insertCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

    }
}