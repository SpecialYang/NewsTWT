package com.special.newsdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.special.newsdemo.model.APi;
import com.special.newsdemo.model.NewContent;
import com.special.newsdemo.model.NewContentResponse;
import com.special.newsdemo.model.NewsResponse;
import com.special.newsdemo.util.HttpUtility;
import com.special.newsdemo.util.Utility;

import java.io.IOException;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewContentActivity extends AppCompatActivity {
    //private int index;
    private static final String PERFIX_URL = "http://open.twtstudio.com/api/v1/";

    private TextView textView;
    private WebView  webView;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_content);
        textView = (TextView) findViewById(R.id.new_subject);
        webView = (WebView) findViewById(R.id.new_content);
        button = (Button) findViewById(R.id.back_button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        Intent intent = getIntent();
        int index = intent.getIntExtra("index",0);
        if(index == 0)
            showError();
        else
            requestNewContent(index);
    }
    public void showError(){
        Toast.makeText(this,"you encoutered a error!",Toast.LENGTH_SHORT).show();
    }

    public void requestNewContent(int index){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PERFIX_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APi api = retrofit.create(APi.class);
        Call<NewContentResponse> call = api.getNewContent(index);
        call.enqueue(new Callback<NewContentResponse>() {
            @Override
            public void onResponse(Call<NewContentResponse> call, Response<NewContentResponse> response) {
                if (response.isSuccessful()) {
                    final NewContentResponse newContentResponse = response.body();
                    runOnUiThread(new Runnable(){
                        @Override
                        public void run() {
                            if(newContentResponse.error_code == -1){
                                NewContent newContent = newContentResponse.data;
                                showNewContent(newContent);
                            }
                            else
                                showError();
                        }
                    });
                } else {
                    showError();
                }
            }
            @Override
            public void onFailure(Call<NewContentResponse> call, Throwable t) {
                t.printStackTrace();
                runOnUiThread(new Runnable(){
                    @Override
                    public void run() {
                        showError();
                    }
                });
            }
        });
    }

    public void showNewContent(NewContent newContent){
        textView.setText(newContent.getSubject());
        webView.loadData(newContent.getContent(),"text/html;charset='utf-8'",null);
    }
}
