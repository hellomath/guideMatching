package com.kimtajo.guideMatching;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

/**
 * Created by Hellomath on 2014. 5. 1..
 */
public class webViewActivity extends Activity {
    private WebView webView;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view);

        webView = (WebView)findViewById(R.id.webView);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String targetUrl = extras.getString("target");
        webView.loadUrl(targetUrl);

    }
}