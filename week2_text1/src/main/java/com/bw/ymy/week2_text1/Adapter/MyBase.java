package com.bw.ymy.week2_text1.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bw.ymy.week2_text1.R;
import com.bw.ymy.week2_text1.bean.UserBean;

import java.util.ArrayList;
import java.util.List;

public class MyBase extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<UserBean.DataBean> mdata;
    private Context context;
    private  boolean isLinear;


    public MyBase(Context context,boolean isLinear) {
       mdata = new ArrayList<>();
       this.context=context;
       this.isLinear=isLinear;
    }

    public  int  getpid(int position)
    {
        return  mdata.get(position).getPid();
    }
    //刷新
    public  void setlist(List<UserBean.DataBean> datas)
    {
        mdata.clear();
        mdata.addAll(datas);
        notifyDataSetChanged();
    }
    public  void addlist(List<UserBean.DataBean> datas)
    {

        mdata.addAll(datas);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder viewHolder=null;
         if(isLinear)
         {
             View view=LayoutInflater.from(context).inflate(R.layout.item1,viewGroup,false);
             viewHolder= new ViewHolder(view);
         }else
         {
             View view=LayoutInflater.from(context).inflate(R.layout.item2,viewGroup,false);
             viewHolder =new ViewHolder(view);
         }
         return  viewHolder;


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {

         final UserBean.DataBean bean=mdata.get(i);

        ViewHolder viewHolder1= (ViewHolder) viewHolder;
       viewHolder1.title.setText(bean.getTitle());
        viewHolder1.price.setText("￥"+bean.getPrice());
        //viewHolder1.shou.setText("已售"+bean.getSalenum()+"台");
        String images=mdata.get(i).getImages();
        String[] split=images.split("\\|");
        Glide.with(context).load(split[0]).into(viewHolder1.icon);

        viewHolder1.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listenter!=null)
                {
                    listenter.onClick(i);
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return mdata.size();
    }
    //获取资源id
    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView title,price;
        private ImageView icon;
        private LinearLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title= itemView.findViewById(R.id.title);
            price= itemView.findViewById(R.id.price);
            icon= itemView.findViewById(R.id.icon);
            layout=itemView.findViewById(R.id.linear);
            TextPaint paint=title.getPaint();
            paint.setFakeBoldText(true);

        }

    }

    //点击监听
    public ClickListenter listenter;

    public void setOnClickListenter(ClickListenter clickListenter){
        listenter=clickListenter;
    }

    public interface ClickListenter{
        void onClick(int position);
    }
}
