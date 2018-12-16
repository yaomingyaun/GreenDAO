package com.bw.ymy.week2_text1.model;

import com.bw.ymy.week2_text1.mycallback.MyCallBack;

import java.util.Map;

public interface IModel {
    void  gerRequest(String urlstr, Map<String,String> para, Class clazz, MyCallBack callBack);
}
