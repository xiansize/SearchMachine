package com.tcsoft.searchmachinary.model.action;


import android.util.Log;

import com.tcsoft.searchmachinary.config.Constant;
import com.tcsoft.searchmachinary.model.api.TokenApi;
import com.tcsoft.searchmachinary.model.api.TokenApiImpl;
import com.tcsoft.searchmachinary.model.asyn.AsyncTaskAction;
import com.tcsoft.searchmachinary.model.asyn.AsyncTaskListener;
import com.tcsoft.searchmachinary.model.listener.ActionListener;

import org.json.JSONException;
import org.json.JSONObject;


public class TokenActionImpl implements TokenAction {

    private TokenApi tokenApi;
    private static final String TAG = "TokenActionImpl";

    public TokenActionImpl() {
        tokenApi = new TokenApiImpl();
    }

    @Override
    public void getToken(final ActionListener<String> listener) {
        AsyncTaskAction asyncTaskAction = new AsyncTaskAction(new AsyncTaskListener<String>() {
            @Override
            public String background() {
                return tokenApi.getToken();
            }

            @Override
            public void result(String result) {
                if (result != null) {
                    Log.d(TAG, result);
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        Constant.token = jsonObject.getJSONArray("messagelist").getJSONObject(0).getString("token");
                        listener.onSuccess("获取token成功");

                    } catch (JSONException e) {
                        listener.onFailure(TAG, "获取token异常");

                    }
                } else {
                    listener.onFailure(TAG, "获取token异常");
                }
            }
        });
        asyncTaskAction.execute();
    }
}
