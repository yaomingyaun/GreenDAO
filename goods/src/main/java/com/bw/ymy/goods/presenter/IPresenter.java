package com.bw.ymy.goods.presenter;

import java.util.Map;

public interface IPresenter {
    void  getRequest(String urlstr, Map<String, String> para, Class clazz);
}
