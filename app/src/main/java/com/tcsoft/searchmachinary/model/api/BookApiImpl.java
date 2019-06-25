package com.tcsoft.searchmachinary.model.api;

import android.util.Log;

import com.tcsoft.searchmachinary.config.Config;
import com.tcsoft.searchmachinary.config.Constant;
import com.tcsoft.searchmachinary.model.network.HttpUtil;

import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;

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
       return HttpUtil.httpGet(Config.PATH_OPEN_LIB + "/service/book/searchbib?token=" + Constant.token + "&libcode=" + Constant.libCode + "&queryparam=title&queryvalue=" + title + "&page=" + page+"&rows=20");
    }

    @Override
    public String getBookDetails(String recNo) {
        return HttpUtil.httpGet(Config.PATH_OPEN_LIB + "/service/barcode/queryholding?token=" + Constant.token + "&bookrecno=" + recNo);
    }

    @Override
    public String getBookCover(String isbn) {
        FormBody.Builder form = new FormBody.Builder();
        form.add("glc","SELFACS");
        form.add("cmdACT","getImages");
        form.add("isbns",isbn);
        form.add("callback","showCovers");

        return HttpUtil.httpPost(form,"http://api.interlib.com.cn:16690/interlibopac/websearch/metares");
    }

    @Override
    public String consultant(String msg) {
        return null;
    }
}
