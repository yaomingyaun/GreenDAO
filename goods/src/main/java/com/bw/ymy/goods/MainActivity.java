package com.bw.ymy.goods;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.bw.ymy.goods.bean.Bean;
import com.bw.ymy.goods.presenter.IPresentermpl;
import com.bw.ymy.goods.view.IView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements IView {

    private   IPresentermpl iPresentermpl;
     private Adapter adapter;
     private  int page=1;
     private XRecyclerView xRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xRecyclerView=findViewById(R.id.xlistview);
        iPresentermpl=new  IPresentermpl(this);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xRecyclerView.setLayoutManager(layoutManager);
        //适配器
        adapter=new Adapter(this);
        xRecyclerView.setAdapter(adapter);

        //是否下拉刷新
        xRecyclerView.setPullRefreshEnabled(true);
        //上啦加载
        xRecyclerView.setLoadingMoreEnabled(true);
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
lodata();

    }
    private  void  lodata()
    {
        Map<String,String> map=new HashMap<>();
        map.put("page",page+"");
        iPresentermpl.getRequest("http://www.xieast.com/api/news/news.php",map,Bean.class);
    }



    @Override
    public void onsuccess(Object data) {
        if(data instanceof  Bean)
        {
            Bean userBean= (Bean) data;
            if(page==1)
            {
                adapter.setlist(userBean.getData());
            }else
            {
                adapter.addlist(userBean.getData());
            }
            page++;
            xRecyclerView.refreshComplete();
            xRecyclerView.loadMoreComplete();
        }



    }
}
