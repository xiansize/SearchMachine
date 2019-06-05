package com.tcsoft.searchmachinary.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.tcsoft.searchmachinary.R;


public class NotificationDialog extends Dialog implements View.OnClickListener {


    private Context context;
    private int layoutId;
    private String content;
    private String libName;
    private String date;


    public NotificationDialog(@NonNull Context context, int layoutId) {
        super(context, R.style.SearchDialog);
        this.context = context;
        this.layoutId = layoutId;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window dialogWindow = getWindow();
        if (dialogWindow != null)
            dialogWindow.setGravity(Gravity.CENTER);
        setContentView(layoutId);

        //windows
        WindowManager windowManager = ((Activity) context).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = display.getWidth() * 4 / 5;
        setCanceledOnTouchOutside(true);

        //btn dismiss
        findViewById(R.id.rl_dismiss_notification_layout).setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        dismiss();
    }


}
