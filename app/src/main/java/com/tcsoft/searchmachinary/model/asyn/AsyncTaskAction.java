package com.tcsoft.searchmachinary.model.asyn;

import android.os.AsyncTask;

/**
 * Created by Admin on 2019/4/17.
 */

public class AsyncTaskAction extends AsyncTask<Void,Void,String> {


    private AsyncTaskListener<String> asyncTaskListener;


    public AsyncTaskAction(AsyncTaskListener<String> asyncTaskListener) {
        this.asyncTaskListener = asyncTaskListener;
    }


    @Override
    protected String doInBackground(Void... params) {
        return asyncTaskListener.background();
    }



    @Override
    protected void onPostExecute(String result) {
        asyncTaskListener.result(result);
    }




}
