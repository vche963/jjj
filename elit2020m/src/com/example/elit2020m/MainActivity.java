package com.example.elit2020m;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import android.webkit.WebView;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        WebView browser = new WebView(this);
        setContentView(browser);
        browser.getSettings().setJavaScriptEnabled(true);        
        browser.loadUrl(getString(R.string.url));
    }
}
