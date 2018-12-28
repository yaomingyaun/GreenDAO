package com.bw.ymy.week2_text1.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.ymy.week2_text1.Adapter.Page;
import com.bw.ymy.week2_text1.R;
import com.bw.ymy.week2_text1.bean.BannerBean;
import com.bw.ymy.week2_text1.presenter.IPresentermpl;
import com.bw.ymy.week2_text1.view.IView;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoaderInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main2Activity extends AppCompatActivity implements IView {




    private TabLayout tab;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //获取资源id
        viewPager=findViewById(R.id.viewpage);
        tab=findViewById(R.id.tab);
        viewPager.setAdapter(new Page(getSupportFragmentManager()));
        //预加载三页
     //   viewPager.setOffscreenPageLimit(3);

        tab.setupWithViewPager(viewPager);



}

    @Override
    public void onsuccess(Object data) {

    }
}
