package com.bw.ymy.week2_text1.model;

import com.bw.ymy.week2_text1.Okhttp.ICallBack;
import com.bw.ymy.week2_text1.Okhttp.OkHttpNutils;
import com.bw.ymy.week2_text1.mycallback.MyCallBack;

import java.util.Map;

public class IModelmpl implements IModel {
    @Override
    public void gerRequest(String urlstr, Map<String, String> para, Class clazz, final MyCallBack callBack) {

        OkHttpNutils.getInstance().PostExenqueue(urlstr, para, clazz, new ICallBack() {
            @Override
            public void onsuccess(Object obj) {
                callBack.onsuccess(obj);
            }

            @Override
            public void onFain(Exception e) {

                callBack.onsuccess(e);
            }
        });

    }
}
