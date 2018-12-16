package com.bw.ymy.goods.model;

import com.bw.ymy.goods.mycallback.MyCallBack;

import java.util.Map;

public interface IModel {
    void  gerRequest(String urlstr, Map<String, String> para, Class clazz, MyCallBack callBack);
}
