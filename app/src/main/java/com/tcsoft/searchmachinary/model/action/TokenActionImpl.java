package com.tcsoft.searchmachinary.model.action;

import android.util.Log;

import com.tcsoft.searchmachinary.model.api.TokenApi;
import com.tcsoft.searchmachinary.model.api.TokenApiImpl;
import com.tcsoft.searchmachinary.model.asyn.AsyncTaskAction;
import com.tcsoft.searchmachinary.model.asyn.AsyncTaskListener;

/**
 * Created by Admin on 2019/5/22.
 */
public class TokenActionImpl implements TokenAction {

    private TokenApi tokenApi;
    private static final String TAG = "TokenActionImpl";

    public TokenActionImpl() {
        tokenApi = new TokenApiImpl();
    }

    @Override
    public void getToken() {
        AsyncTaskAction asyncTaskAction = new AsyncTaskAction(new AsyncTaskListener<String>() {
            @Override
            public String background() {
                return tokenApi.getToken();
            }

            @Override
            public void result(String result) {
                if (result != null) {
                    Log.d(TAG, result);
                }
            }
        });
        asyncTaskAction.execute();
    }
}
