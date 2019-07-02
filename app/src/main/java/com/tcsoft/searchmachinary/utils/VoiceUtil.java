package com.tcsoft.searchmachinary.utils;

import android.content.Context;
import android.util.Log;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Admin on 2019/7/2.
 */

public class VoiceUtil {

    private Context context;


    public VoiceUtil(Context context) {
        this.context = context;
        SpeechUtility.createUtility(context, SpeechConstant.APPID + "=5a1d19dc");
    }

    public void showTalkDialog(final InputVoiceListener inputVoiceListener) {
        final RecognizerDialog recognizerDialog = new RecognizerDialog(context, null);
        recognizerDialog.setParameter(SpeechConstant.LANGUAGE, "zh_cn");//语种，这里可以有zh_cn和en_us
        recognizerDialog.setParameter(SpeechConstant.ACCENT, "mandarin");//设置口音，这里设置的是汉语普通话 具体支持口音请查看讯飞文档，
        recognizerDialog.setParameter(SpeechConstant.TEXT_ENCODING, "utf-8");//设置编码类型

        //其他设置请参考文档http://www.xfyun.cn/doccenter/awd
        recognizerDialog.setListener(new RecognizerDialogListener() {
            @Override
            public void onResult(com.iflytek.cloud.RecognizerResult recognizerResult, boolean b) {
                String result = recognizerResult.getResultString();
                StringBuilder stringBuffer = new StringBuilder();
                try {
                    JSONObject json = new JSONObject(result);
                    JSONArray jsonArray = json.getJSONArray("ws");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        JSONArray jsonArray1 = jsonObject1.getJSONArray("cw");
                        JSONObject jsonObject2 = jsonArray1.getJSONObject(0);
                        String w = jsonObject2.getString("w");
                        stringBuffer.append(w);
                    }

                    String word = stringBuffer.toString();
                    if ("。".equals(word)) {
                        Log.d("ERROR", ".");
                    } else {
                        inputVoiceListener.inputVoiceListener(word);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(SpeechError speechError) {
                if (speechError.getErrorCode() == 10118)
                    recognizerDialog.dismiss();
            }
        });
        //显示讯飞语音识别视图
        recognizerDialog.show();
    }


    public interface InputVoiceListener {
        void inputVoiceListener(String title);
    }


}
