package com.bw.ymy.week2_text1.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.ymy.week2_text1.R;
import com.bw.ymy.week2_text1.bean.BannerBean;
import com.bw.ymy.week2_text1.bean.Message;
import com.bw.ymy.week2_text1.bean.UserBean;
import com.bw.ymy.week2_text1.presenter.IPresentermpl;
import com.bw.ymy.week2_text1.view.IView;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoaderInterface;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class one extends Fragment implements IView {
     private  int pid;
  private Banner banner1;
  private TextView title,price,barginprice;
  private IPresentermpl iPresentermpl;
    private Button cz,jg;
    private BannerBean.DataBean data1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.one,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        banner1=view.findViewById(R.id.banner1);
        title=view.findViewById(R.id.title);
        price=view.findViewById(R.id.price);
        cz=view.findViewById(R.id.cz);
        jg=view.findViewById(R.id.jg);

        //商品名字
        cz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


              Message  object=new Message(data1.getTitle(),"title");
                //黏型事件
                EventBus.getDefault().postSticky(object);
            }
        });
        //价格
        jg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //黏贴时间
                Message  object=new Message(data1.getPrice(),"price");
                Toast.makeText(getActivity(),data1.getPrice()+"",Toast.LENGTH_LONG).show();
                //黏型事件
                EventBus.getDefault().postSticky(object);
            }
        });


        iPresentermpl= new IPresentermpl(this);
        barginprice=view.findViewById(R.id.barginprice);
        //接受过来的值

        Intent intent1=getActivity().getIntent();
        final  String pids=intent1.getStringExtra("pid");
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
        data1 = bannerBean.getData();
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

