package com.bw.ymy.week2_text1.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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

    private  int pid;
    private Banner banner1;
    private TextView title,price,barginprice;
    private IPresentermpl iPresentermpl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //获取资源id
        banner1=findViewById(R.id.banner1);
        title=findViewById(R.id.title);
        price=findViewById(R.id.price);
        iPresentermpl= new IPresentermpl(this);
        barginprice=findViewById(R.id.barginprice);
        //接受过来的值
        Intent intent=getIntent();
        final  String pids=intent.getStringExtra("pid");
        banner1.setImageLoader(new ImageLoaderInterface<ImageView>() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }

            @Override
            public ImageView createImageView(Context context) {
                ImageView imageView=new ImageView(context);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                return imageView;
            }
        });
        Map<String,String> map=new HashMap<>();
        map.put("pid",pids);
        iPresentermpl.getRequest("http://www.zhaoapi.cn/product/getProductDetail",map,BannerBean.class);
    }

    @Override
    public void onsuccess(Object data) {
        List<String> list=new ArrayList<>();
        BannerBean bannerBean= (BannerBean) data;
        String[] split=bannerBean.getData().getImages().split("\\|");
        for (int i=0;i<split.length;i++)
        {
            list.add(split[i]);
        }
        banner1.setImages(list);
        banner1.start();
        barginprice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        price.setText("¥"+bannerBean.getData().getPrice()+"");
        title.setPaintFlags(Paint.FAKE_BOLD_TEXT_FLAG);
        title.setText(bannerBean.getData().getTitle());
        barginprice.setText("¥"+bannerBean.getData().getBargainPrice()+"");

    }
}
