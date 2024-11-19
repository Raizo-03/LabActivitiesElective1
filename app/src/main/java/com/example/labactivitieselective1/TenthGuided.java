package com.example.labactivitieselective1;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TenthGuided extends AppCompatActivity {
    WebView browser;
    AutoCompleteTextView suggestedURL;
    ArrayAdapter adapter;
    Button submit;
    String [] urls = {"google.com","yahoo.com","facebook.com","youtube.com"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tenth_guided);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        browser = findViewById(R.id.webView);
        suggestedURL = findViewById(R.id.actvURLGE10);
        submit = findViewById(R.id.btnOpenURLGE10);

        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,urls);
        suggestedURL.setThreshold(2);
        suggestedURL.setAdapter(adapter);

        initializeWebView();
        loadWebURL();

    }
    public void initializeWebView(){
        browser.getSettings().setLoadsImagesAutomatically(true);
        // enabled java script
        browser.getSettings().setJavaScriptEnabled(true);
        // Android webview launches browser when calling loadurl
        browser.setWebViewClient(new WebViewClient());
        // enabled Scrollbar
        browser.setScrollBarStyle(browser.SCROLLBARS_INSIDE_OVERLAY);
    }

    public void loadWebURL(){
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = suggestedURL.getText().toString();

                if(!url.startsWith("www.") && !url.startsWith("http://") ){
                    url = "www." + url;
                }
                if(!url.startsWith("http://") ){
                    url = "http://" + url;
                }
                browser.loadUrl(url);
            }
        });
    }
}

