package com.bw.ymy.goods;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.ymy.goods.bean.Bean;

import java.util.ArrayList;
import java.util.List;

public class Adapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Bean.DataBean> mdata;
    private Context context;

    public Adapter(Context context) {
        mdata = new ArrayList<>();
       this.context=context;
    }
public  void setlist(List<Bean.DataBean> datas)
{
    mdata.clear();
    mdata.addAll(datas);
    notifyDataSetChanged();
}
    public  void addlist(List<Bean.DataBean> datas)
    {

        mdata.addAll(datas);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        View view=LayoutInflater.from(context).inflate(R.layout.item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, final int i) {

        ViewHolder viewHolder1= (ViewHolder) viewHolder;
        //点击删除
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context)
                        .setTitle("删除")
                        .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //获取下标
                        int pos=viewHolder.getLayoutPosition();
                        mdata.remove(i);
                        notifyDataSetChanged();
                    }
                }).setNeutralButton("s取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                builder.show();

            }
        });
        viewHolder1.title.setText(mdata.get(i).getTitle());
        Glide.with(context).load(mdata.get(i).getThumbnail_pic_s03()).into(viewHolder1.icon);

    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }
    class  ViewHolder extends RecyclerView.ViewHolder {


        private TextView title;
        private ImageView icon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title=itemView.findViewById(R.id.title);
            icon=itemView.findViewById(R.id.icon);
        }
    }


}
