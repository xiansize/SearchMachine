package com.tcsoft.searchmachinary.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.tcsoft.searchmachinary.R;


public class CusEditView extends AppCompatEditText {

    //EditText右侧的删除按钮
    private Drawable mDeleteDrawable;
    private Context mContext;

    public CusEditView(Context context) {
        super(context);
        init(context);
    }

    public CusEditView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CusEditView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        this.mContext = context;
        mDeleteDrawable = getCompoundDrawables()[2];
        if (mDeleteDrawable == null) {
            mDeleteDrawable = getResources().getDrawable(R.drawable.icon_input_delete);
        }
        //设置mDeleteDrawable占的面积
        mDeleteDrawable.setBounds(0, 0, mDeleteDrawable.getIntrinsicWidth(), mDeleteDrawable.getIntrinsicHeight());
        // 默认设置隐藏图标
        setClearIconVisible(true);
        //输入字离输入框的边界
        setPadding(dip2px(3), dip2px(3), dip2px(3), dip2px(3));
        // 设置输入框里面内容发生改变的监听
        addTextChangedListener(watcher);
    }


    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() > 0) {
                setClearIconVisible(true);
            } else {
                setClearIconVisible(false);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (getCompoundDrawables()[2] != null) {

                boolean touchable = event.getX() > (getWidth() - getTotalPaddingRight())
                        && (event.getX() < ((getWidth() - getPaddingRight())));
                if (touchable) {
                    this.setText("");
                }
            }
        }

        return super.onTouchEvent(event);
    }


    private void setClearIconVisible(boolean visible) {
        Drawable right = visible ? mDeleteDrawable : null;
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], right, getCompoundDrawables()[3]);
    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    private int dip2px(float dpValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


}
