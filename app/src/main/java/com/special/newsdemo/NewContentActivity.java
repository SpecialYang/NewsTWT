package com.special.newsdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.special.newsdemo.model.NewContent;
import com.special.newsdemo.model.NewContentResponse;
import com.special.newsdemo.util.HttpUtility;
import com.special.newsdemo.util.Utility;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class NewContentActivity extends AppCompatActivity {
    //private int index;
    private static final String PERFIX_URL = "http://open.twtstudio.com/api/v1/news/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_content);
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
        String url = PERFIX_URL + index;
        HttpUtility.sendOkHttpRequest(url,new Callback(){
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable(){
                    @Override
                    public void run() {
                       showError();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseString = response.body().string();
                final NewContentResponse newContentResponse = Utility.handleNewsContentResponse(responseString);
                runOnUiThread(new Runnable(){
                    @Override
                    public void run() {
                        if(newContentResponse.error_code == -1){
                            NewContent newContent = newContentResponse.data;
                            showNewContent(newContent);
                        }
                    }
                });
            }
        });
    }

    public void showNewContent(NewContent newContent){

    }
}
