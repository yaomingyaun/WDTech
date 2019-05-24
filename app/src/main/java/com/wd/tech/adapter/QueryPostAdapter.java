package com.wd.tech.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wd.tech.R;
import com.wd.tech.activity.NewsAllPlActivity;
import com.wd.tech.activity.PhotoViewActivity;
import com.wd.tech.activity.QuerypostsActivity;
import com.wd.tech.bean.PLBean;
import com.wd.tech.bean.QueryPostBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueryPostAdapter extends RecyclerView.Adapter<XRecyclerView.ViewHolder> {



    private List<QueryPostBean.ResultBean.CommunityUserPostVoListBean> mdata;
    private Context context;

    public QueryPostAdapter(Context context) {
        this.context = context;
        mdata=new ArrayList<>();
    }

    public void  setlist(List<QueryPostBean.ResultBean.CommunityUserPostVoListBean> list) {
        mdata.clear();
        mdata.addAll(list);
        notifyDataSetChanged();
    }
    public void  addlist(List<QueryPostBean.ResultBean.CommunityUserPostVoListBean> list) {

        mdata.addAll(list);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public XRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.querypost_item,viewGroup,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull XRecyclerView.ViewHolder viewHolder, final int i) {
        final ViewHolder viewHolder1= (ViewHolder) viewHolder;
        viewHolder1.querypost_content.setText(mdata.get(i).getContent());
        String[] file = mdata.get(i).getFile().split(",");
        final ArrayList<String> objects = new ArrayList<>();

        objects.addAll(Arrays.asList(file));
        viewHolder1.querypost_num.setText(mdata.get(i).getPraise()+"");
        viewHolder1.querypost_countnum0.setText(mdata.get(i).getComment()+"");

        if(mdata.get(i).getFile().length()==0)
        {
            viewHolder1.queryposticon_view.setVisibility(View.GONE);
        }else
        {
            GridLayoutManager gridLayoutManager=new GridLayoutManager(context,3);
            viewHolder1.queryposticon_view.setLayoutManager(gridLayoutManager);
            NewsIconAdapter newsIconAdapter=new NewsIconAdapter(context,file);
            viewHolder1.queryposticon_view.setAdapter(newsIconAdapter);

            //点击图片放大
            newsIconAdapter.setImages(new NewsIconAdapter.IconSetonClick1() {
                @Override
                public void oncick(int position) {
                    Intent intent=new Intent(context,PhotoViewActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putStringArrayList("image",objects);
                    intent.putExtras(bundle);
                    intent.putExtra("imageposition",position);
                    context.startActivity(intent);
                }
            });
        }
        //点赞
        if(mdata.get(i).getWhetherGreat()==1)
        {
            viewHolder1.querypost_dz.setImageResource(R.mipmap.common_icon_praise_s);
        }else
        {
            viewHolder1.querypost_dz.setImageResource(R.mipmap.common_icon_prise_n);
        }
        viewHolder1.querypost_dz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(setDZOnclick!=null)
                {
                    setDZOnclick.onclick(mdata.get(i).getWhetherGreat(),i,mdata.get(i).getId());
                }
            }
        });
        viewHolder1.querypost_countnum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(setOnclickAll!=null)
                {
                    setOnclickAll.onclick2(i);
                }
            }
        });
    }

    //点赞的方法
    public void getGivePraise(int position) {
        mdata.get(position).setWhetherGreat(1);
        mdata.get(position).setPraise(mdata.get(position).getPraise() + 1);
        notifyDataSetChanged();
    }
    //取消点赞的方法
    public void getCancelPraise(int position) {
        mdata.get(position).setWhetherGreat(2);
        mdata.get(position).setPraise(mdata.get(position).getPraise() - 1);
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public  class  ViewHolder extends RecyclerView.ViewHolder{

        TextView querypost_content,querypost_num,querypost_countnum0;
      RecyclerView queryposticon_view;
        ImageView querypost_dz,querypost_countnum;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            queryposticon_view=itemView.findViewById(R.id.queryposticon_view);
            querypost_content=itemView.findViewById(R.id.querypost_content);
            querypost_dz=itemView.findViewById(R.id.querypost_dz);
            querypost_num=itemView.findViewById(R.id.querypost_num);
            querypost_countnum=itemView.findViewById(R.id.querypost_countnum);
            querypost_countnum0=itemView.findViewById(R.id.querypost_countnum0);
        }
    }
    /* 接口回调 */
    private setDZOnclick setDZOnclick;


    public  void  setContext(setDZOnclick setDZOnclick)
    {
        this.setDZOnclick=setDZOnclick;
    }
    public  interface setDZOnclick
    {
        void  onclick( int whetherGreat,int i,int communityId);
    }

    //点击


    private  SetOnclickAll  setOnclickAll;
    public  void  setMdata(SetOnclickAll setOnclick)
    {
        this.setOnclickAll=setOnclick;
    }

    public  interface  SetOnclickAll
    {
        void  onclick2(int position);
    }

}
