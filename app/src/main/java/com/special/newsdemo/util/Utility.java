package com.special.newsdemo.util;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.special.newsdemo.model.NewContentResponse;
import com.special.newsdemo.model.NewsResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Special on 2017/10/31.
 */

public class Utility {

    public static NewsResponse handleNewsResponse(String response){
            try{
                JSONObject jsonObject = new JSONObject(response);
                return new Gson().fromJson(jsonObject.toString(),NewsResponse.class);
            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
    }

    public static NewContentResponse handleNewsContentResponse(String response){
        try{
            JSONObject jsonObject = new JSONObject(response);
            return new Gson().fromJson(jsonObject.toString(),NewContentResponse.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static String[] getTabMenus(){
        return new String[]{"天大要闻","校园公告","社团风采","院系动态","视点观察"};
    }
}
