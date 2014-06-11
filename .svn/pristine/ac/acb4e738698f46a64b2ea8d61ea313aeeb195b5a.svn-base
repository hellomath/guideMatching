package com.kimtajo.guideMatching;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.*;
import com.kimtajo.guideMatching.dataClass.Custom;

/**
 * Created by Hellomath on 2014. 3. 5..
 */
public class signupActivity extends Activity {



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        final EditText idJoin = (EditText)findViewById(R.id.idJoin);
        final EditText pwdJoin = (EditText)findViewById(R.id.pwdJoin);
        final EditText nameJoin = (EditText)findViewById(R.id.nameJoin);
        final EditText nickJoin = (EditText)findViewById(R.id.nickJoin);
        final EditText phoneJoin = (EditText)findViewById(R.id.phoneJoin);
        final EditText nationalJoin = (EditText)findViewById(R.id.nationalJoin);
        final Spinner custType = (Spinner)findViewById(R.id.typeJoin);

        EditText infOpnJoin = (EditText)findViewById(R.id.infoOpnJoin);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.cust_type, android.R.layout.simple_spinner_item);
        custType.setAdapter(adapter);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ImageButton sendJoin = (ImageButton)findViewById(R.id.sendJoinImgBtn);
        sendJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Custom joinData =new Custom(
                idJoin.getText().toString(), pwdJoin.getText().toString(), nameJoin.getText().toString(),
                        nickJoin.getText().toString(), phoneJoin.getText().toString(), nationalJoin.getText().toString(),
                        custType.getPrompt().toString(), "Y");

                String url = "http://hellomath.iptime.org/guideMatching/join/saveJoinData.php";
                joinData.sendJoinData(url);
            }
        });


    }

}