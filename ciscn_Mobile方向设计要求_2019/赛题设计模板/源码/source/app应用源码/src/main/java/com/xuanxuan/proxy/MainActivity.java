package com.xuanxuan.proxy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText e1;
        final EditText e2;
        Button b1;
        final WebView web1;
        web1  = (WebView)findViewById(R.id.web);
        b1  = (Button)findViewById(R.id.button);
        e1  = (EditText)findViewById(R.id.editText);
        e2  = (EditText)findViewById(R.id.editText2);
        final String url  = "http://"+e2.getText().toString()+"/api.php?ip=";
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String url  = "http://"+e2.getText().toString()+"/api.php?ip=";
                String proxy = e1.getText().toString();
                web1.loadUrl(url+proxy);
            }
        });

    }
}
