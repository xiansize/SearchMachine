package com.tcsoft.searchmachinary.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;


import com.tcsoft.searchmachinary.utils.TimeUtil;



public class CustomTimeView extends android.support.v7.widget.AppCompatTextView{

    private CustomTimeView textView;

    private TimeHanlder timeHanlder = new TimeHanlder();

    public CustomTimeView(Context context) {
        this(context,null);
    }

    public CustomTimeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.textView = this;
        init();
    }


    private void init(){
        try {
            textView.setText(TimeUtil.getTimeMin());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    timeHanlder.startScheduleUpdate();
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class TimeHanlder extends Handler{
        private boolean mStopped;
        private void post(){
            sendMessageDelayed(obtainMessage(0),1000);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (!mStopped){
                textView.setText(TimeUtil.getTimeMin());
                post();
            }
        }

        void startScheduleUpdate(){
            mStopped = false;
            post();
        }
    }


}
