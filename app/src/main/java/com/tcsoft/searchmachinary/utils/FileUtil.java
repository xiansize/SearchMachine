package com.tcsoft.searchmachinary.utils;

import android.os.Environment;
import android.util.Log;

import com.tcsoft.searchmachinary.config.Config;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2019/6/19.
 */

public class FileUtil {
    private static final String TAG = "FileUtil";


    public static boolean initFile() {
        boolean mkdirs = false, newFile = false;
        File dir = new File(Environment.getExternalStorageDirectory() + Config.DIRS_CONFIG);
        if (!dir.exists()) {
            mkdirs = dir.mkdirs();
        }

        File file = new File(Environment.getExternalStorageDirectory() + Config.DIRS_CONFIG + Config.FILE_CONFIG);
        if (!file.exists()) try {
            newFile = file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mkdirs && newFile;
    }


    public static List<String> getTxtContent() {
        List<String> list = new ArrayList<>();
        File file = new File(Environment.getExternalStorageDirectory() + Config.DIRS_CONFIG + Config.FILE_CONFIG);
        if (!file.exists()) {
            return null;
        }
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            inputStream = new FileInputStream(file);
            inputStreamReader = new InputStreamReader(inputStream, "GB2312");
            bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                list.add(line);
            }
        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
            return null;
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                Log.d(TAG, e.getMessage());
            }
        }

        return list;
    }


}