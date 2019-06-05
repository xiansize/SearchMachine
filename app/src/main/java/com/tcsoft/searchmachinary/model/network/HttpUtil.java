package com.tcsoft.searchmachinary.model.network;

import java.io.IOException;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Admin on 2019/5/22.
 */
public class HttpUtil {



    public static String httpGet(String path) {
        String resp = null;
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(path).build();
            Response response;
            response = client.newCall(request).execute();
            if (response.isSuccessful()){
                resp = response.body().string();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }




    public static String httpPost(FormBody.Builder form, String path) {
        String resp = null;
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(path).post(form.build()).build();
            Response response;
            response = client.newCall(request).execute();
            if(response.isSuccessful()){
                resp = response.body().string();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }


}
