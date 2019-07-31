package com.tcsoft.searchmachinary.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.tcsoft.searchmachinary.utils.TimeUtil;



public class DateTimeView extends android.support.v7.widget.AppCompatTextView{

    private DateTimeView textView;

    private TimeHanlder timeHanlder = new TimeHanlder();

    public DateTimeView(Context context) {
        this(context,null);
    }

    public DateTimeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.textView = this;
        init();
    }


    private void init(){
        try {
            textView.setText(TimeUtil.getTimeDate());
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

    public  class TimeHanlder extends Handler {
        private boolean mStopped;
        private void post(){
            sendMessageDelayed(obtainMessage(0),1000);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (!mStopped){
                textView.setText(TimeUtil.getTimeDate());
                post();
            }
        }

        void startScheduleUpdate(){
            mStopped = false;
            post();
        }
    }


}
