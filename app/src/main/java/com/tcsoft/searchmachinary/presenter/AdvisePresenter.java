package com.tcsoft.searchmachinary.presenter;

import android.content.Context;

import com.tcsoft.searchmachinary.R;
import com.tcsoft.searchmachinary.bean.Consult;
import com.tcsoft.searchmachinary.model.action.BookAction;
import com.tcsoft.searchmachinary.model.action.BookActionImpl;
import com.tcsoft.searchmachinary.model.listener.ActionListener;
import com.tcsoft.searchmachinary.utils.TimeUtil;
import com.tcsoft.searchmachinary.view.AdviseView;



public class AdvisePresenter extends BasePresenter<AdviseView> {


    private BookAction bookAction;
    private AdviseView adviseView;
    private Context context;


    public AdvisePresenter(AdviseView adviseView, Context context) {
        this.adviseView = adviseView;
        this.context = context;
        this.bookAction = new BookActionImpl();
    }


    public void showWeather() {
        adviseView.showWeather();
    }


    public void question() {
        Consult consult = new Consult();
        consult.setClient(false);
        consult.setText(context.getResources().getString(R.string.service_response));
        consult.setTime(TimeUtil.getAllTime());
        adviseView.getQuestion(consult);

    }


    public void sendMsg(String content) {
        if (!content.equals("")) {
            Consult consult = new Consult();
            consult.setClient(true);
            consult.setText(content);
            consult.setTime(TimeUtil.getAllTime());
            adviseView.sendMsg(consult);
            getMsg(content);
        }
    }


    private void getMsg(String content) {
        if (!isViewAttached()) return;
        bookAction.consultant(content, new ActionListener<Consult>() {
            @Override
            public void onSuccess(Consult consult) {
                adviseView.getMsg(consult);
            }

            @Override
            public void onFailure(String tag, String msg) {
                adviseView.showToast(msg);
            }
        });
    }

}
