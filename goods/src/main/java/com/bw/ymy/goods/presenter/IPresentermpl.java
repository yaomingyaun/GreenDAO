package com.bw.ymy.goods.presenter;

import com.bw.ymy.goods.model.IModelmpl;
import com.bw.ymy.goods.mycallback.MyCallBack;
import com.bw.ymy.goods.view.IView;


import java.util.Map;

public class IPresentermpl implements IPresenter {

    private IView mIView;
    private IModelmpl iModelmpl;

    public IPresentermpl(IView mIView) {
        this.mIView = mIView;
        iModelmpl=new IModelmpl();
    }
    //解绑
    public  void  detach()
    {
            iModelmpl=null;
            mIView=null;
    }

    @Override
    public void getRequest(String urlstr, Map<String, String> para, Class clazz) {

        iModelmpl.gerRequest(urlstr, para, clazz, new MyCallBack() {
            @Override
            public void onsuccess(Object data) {
                mIView.onsuccess(data);
            }
        });

    }
}
