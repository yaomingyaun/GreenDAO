package com.bw.ymy.week2_text1.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bw.ymy.week2_text1.APIs;
import com.bw.ymy.week2_text1.Adapter.MyBase;
import com.bw.ymy.week2_text1.R;
import com.bw.ymy.week2_text1.bean.UserBean;
import com.bw.ymy.week2_text1.presenter.IPresentermpl;
import com.bw.ymy.week2_text1.view.IView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener ,IView {

    private XRecyclerView xRecyclerView;
    private int page=1;
    private IPresentermpl iPresentermpl;
    private MyBase adapter;
    private ImageView qie,fan;
    private boolean isLinear;
    private Button zong,xiao,jia;
    private EditText etname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取资源id
        initview();
        //刷新
        Xlodata();
    }
    //获取资源Id
    public  void  initview()
    {
        xRecyclerView=findViewById(R.id.xrecyclerview);
        qie=findViewById(R.id.qie);
        zong=findViewById(R.id.zong);
        xiao=findViewById(R.id.xiao);
        jia=findViewById(R.id.jia);
        fan=findViewById(R.id.fan);
        etname=findViewById(R.id.etname);
        qie.setOnClickListener(this);
        zong.setOnClickListener(this);
        xiao.setOnClickListener(this);
        jia.setOnClickListener(this);
        fan.setOnClickListener(this);
        //判断布局
        isLinear=true;
        //实列化
        iPresentermpl=new IPresentermpl(this);
    }
    public  void  Xlodata()
    {
        //上拉
        xRecyclerView.setPullRefreshEnabled(true);
        //下拉
        xRecyclerView.setLoadingMoreEnabled(true);
        //回调
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;
                lodata();
            }
            @Override
            public void onLoadMore() {
                lodata();
            }
        });
        //布局
        isLayoutManager();
    }

    //获取数据
    public  void lodata()
    {
        Map<String,String> map=new HashMap<>();
            String name=etname.getText().toString();
             map.put("keywords","手机");
             map.put("page",page+"");
            iPresentermpl.getRequest(APIs.TYPE_TEXT,map,UserBean.class);
    }


    //切换

    public  void  isLayoutManager()
    {
        if(isLinear)
        {
            LinearLayoutManager layoutManager=new LinearLayoutManager(this);
            xRecyclerView.setLayoutManager(layoutManager);
            findViewById(R.id.qie).setBackgroundResource(R.drawable.grid);

        }else
        {
            GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
            xRecyclerView.setLayoutManager(gridLayoutManager);
            findViewById(R.id.qie).setBackgroundResource(R.drawable.linear);
        }
        //必须写在这里 不能换
        adapter=new MyBase(this,isLinear);
        //点击跳转
        adapter.setOnClickListenter(new MyBase.ClickListenter() {
            @Override
            public void onClick(int position) {

                int pid=adapter.getpid(position);
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                intent.putExtra("pid",pid+"");
                startActivity(intent);
            }
        });

        xRecyclerView.setAdapter(adapter);
         isLinear=!isLinear;
        page=1;
        lodata();
    }
    //点击事件
    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            //点击搜素
            case  R.id.fan:
                page=1;
                Map<String,String> pa=new HashMap<>();
                String name=etname.getText().toString();
                pa.put("keywords",name);
                iPresentermpl.getRequest(APIs.TYPE_TEXT,pa,UserBean.class);
                break;
                //点击切换
            case  R.id.qie:
                Toast.makeText(this,"ss",Toast.LENGTH_LONG).show();
                isLayoutManager();
                break;
                //点击查看综合
            case  R.id.zong:
                page=1;
                zong.setTextColor(Color.RED);
                jia.setTextColor(Color.BLACK);
                xiao.setTextColor(Color.BLACK);
               Map<String,String> map1=new HashMap<>();
                map1.put("sort",0+"");
                map1.put("keywords","手机");
                map1.put("page",page+"");
                iPresentermpl.getRequest(APIs.TYPE_TEXT,map1,UserBean.class);
                break;
            //点击查看价钱
            case  R.id.jia:
                page=1;
                zong.setTextColor(Color.BLACK);
                jia.setTextColor(Color.RED);
                xiao.setTextColor(Color.BLACK);
                Map<String,String> map3=new HashMap<>();
                map3.put("sort",2+"");
                map3.put("keywords","手机");
                map3.put("page",page+"");
                iPresentermpl.getRequest(APIs.TYPE_TEXT,map3,UserBean.class);
                break;
            //点击查看销量
            case  R.id.xiao:
                page=1;
                zong.setTextColor(Color.BLACK);
                jia.setTextColor(Color.BLACK);
                xiao.setTextColor(Color.RED);
                Map<String,String> map2=new HashMap<>();
                map2.put("sort",1+"");
                map2.put("keywords","手机");
                map2.put("page",page+"");
                iPresentermpl.getRequest(APIs.TYPE_TEXT,map2,UserBean.class);
                break;
                default:break;
        }
    }
        //判断
    @Override
    public void onsuccess(Object data) {
        if(data instanceof UserBean)
        {
            UserBean userBean= (UserBean) data;
            if(page==1)
            {
               adapter.setlist(userBean.getData());
            }else
            {
            adapter.addlist(userBean.getData());
            }
            page++;
            //停止
            xRecyclerView.refreshComplete();
            xRecyclerView.loadMoreComplete();
        }
    }
    //解绑
    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPresentermpl.detach();
    }


}
