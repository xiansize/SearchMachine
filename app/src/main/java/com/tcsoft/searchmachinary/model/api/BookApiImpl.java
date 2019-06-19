package com.tcsoft.searchmachinary.model.api;

import com.tcsoft.searchmachinary.config.Config;
import com.tcsoft.searchmachinary.config.Constant;
import com.tcsoft.searchmachinary.model.network.HttpUtil;

/**
 * Created by Admin on 2019/5/22.
 */

public class BookApiImpl implements BookApi {


    @Override
    public String newBook() {
        return null;
    }

    @Override
    public String hotBook() {
        return null;
    }

    @Override
    public String searchBook(String title, String page) {
        return HttpUtil.httpGet(Config.PATH_OPEN_LIB + "?token=" + Constant.token + "&libcode=" + Constant.libCode + "&queryparam=title&queryvalue=" + title + "&page=" + page);
    }

    @Override
    public String getBookDetails(String recNo) {
        return HttpUtil.httpGet(Config.PATH_OPEN_LIB + "?token=" + Constant.token + "&bookrecno=" + recNo);
    }

    @Override
    public String consultant(String msg) {
        return null;
    }
}
