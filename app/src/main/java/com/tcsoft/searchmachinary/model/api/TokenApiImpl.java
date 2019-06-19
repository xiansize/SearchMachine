package com.tcsoft.searchmachinary.model.api;

import com.tcsoft.searchmachinary.config.Config;
import com.tcsoft.searchmachinary.config.Constant;
import com.tcsoft.searchmachinary.model.network.HttpUtil;

/**
 * Created by Admin on 2019/5/22.
 */

public class TokenApiImpl implements TokenApi{


    @Override
    public String getToken() {
        return HttpUtil.httpGet(Config.PATH_OPEN_LIB + "/service/barcode/token?appid="+Config.ID_OPEB_LIB+"&secret="+Config.PASS_OPEN_LIB);
    }
}
