package com.tcsoft.searchmachinary.model.api;


import android.util.Log;

import com.tcsoft.searchmachinary.config.Config;
import com.tcsoft.searchmachinary.config.Constant;
import com.tcsoft.searchmachinary.model.network.HttpUtil;
import com.tcsoft.searchmachinary.utils.TimeUtil;

import okhttp3.FormBody;


public class BookApiImpl implements BookApi {


    @Override
    public String newBook(String page) {
        return HttpUtil.httpGet(Config.PATH_OPAC + "/search?isFacet=false&view=json&queryType=search_newpublications&searchType=newpub&limitStartDate=2019-" + TimeUtil.getMonth() + "-01&limitEndDate=2019-" + TimeUtil.getMonth() + "-30&return_fmt=json&page=" + page);
    }

    @Override
    public String hotBook() {
        return HttpUtil.httpGet(Config.PATH_OPAC + "/ranking/bookLoanRank/json?libcode=" + "&bookType=" + "&classno=" + "&limitDays=" + 30);
    }

    @Override
    public String searchBook(String type, String title, String page) {
        return HttpUtil.httpGet(Config.PATH_OPEN_LIB + "/service/book/searchbib?token=" + Constant.token + "&libcode=" + Constant.libCode + "&queryparam=" + type + "&queryvalue=" + title + "&page=" + page + "&rows=20");
    }

    @Override
    public String getBookDetails(String recNo) {
        return HttpUtil.httpGet(Config.PATH_OPEN_LIB + "/service/barcode/queryholding?token=" + Constant.token + "&bookrecno=" + recNo);
    }

    @Override
    public String getBookCover(String isbn) {
        FormBody.Builder form = new FormBody.Builder();
        form.add("glc", "SELFACS");
        form.add("cmdACT", "getImages");
        form.add("isbns", isbn);
        form.add("callback", "showCovers");

        return HttpUtil.httpPost(form, "http://api.interlib.com.cn:16690/interlibopac/websearch/metares");
    }


    @Override
    public String consultant(String msg) {
        return HttpUtil.httpGet("http://rc.interlib.com.cn:82/rc/web/api/kb/wx/getkblist.html?keyContent=" + msg + "&globalLibraryCode=WX_TEST_LIB");
    }
}
