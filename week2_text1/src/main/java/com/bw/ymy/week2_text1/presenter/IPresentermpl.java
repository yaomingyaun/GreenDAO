package com.bw.ymy.week2_text1.presenter;

import com.bw.ymy.week2_text1.model.IModelmpl;
import com.bw.ymy.week2_text1.mycallback.MyCallBack;
import com.bw.ymy.week2_text1.view.IView;

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
