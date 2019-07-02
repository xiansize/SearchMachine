package com.tcsoft.searchmachinary.model.action;

import android.util.Log;

import com.tcsoft.searchmachinary.model.listener.ActionListener;
import com.tcsoft.searchmachinary.utils.FileUtil;

import java.util.List;

/**
 * Created by Admin on 2019/6/19.
 */

public class FileActionImpl implements FileAction {

    private static final String TAG = "FileActionImpl";

    @Override
    public void getFileContent(ActionListener<List<String>> listener) {
        List<String> list = FileUtil.getTxtContent();
        if (list != null && list.size() > 0)
            listener.onSuccess(list);
        else
            Log.d(TAG,"文件没配置");
    }

}
