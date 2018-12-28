package com.bw.ymy.week2_text1.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.ymy.week2_text1.R;
import com.bw.ymy.week2_text1.bean.Message;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class three extends Fragment {
    private TextView p;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.three,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        p=view.findViewById(R.id.p);
        EventBus.getDefault().register(this);

    }
    @Subscribe(threadMode =ThreadMode.MAIN,sticky = true)   //必写  重要
    public  void onMessage(Message bean)
    {
        if (bean.getId().equals("price"))
        {
            p.setText(bean.getObject()+"");
        }

    }
    //销毁
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
