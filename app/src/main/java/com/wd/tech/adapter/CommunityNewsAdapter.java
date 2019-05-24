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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommunityNewsAdapter extends RecyclerView.Adapter<XRecyclerView.ViewHolder> {
    private List<PLBean.ResultBean> mdata;
    private Context context;

    public CommunityNewsAdapter(Context context) {
        this.context = context;
        mdata=new ArrayList<>();
    }

    public void  setlist(List<PLBean.ResultBean> list) {
        mdata.clear();
        mdata.addAll(list);
        notifyDataSetChanged();
    }
    public void  addlist(List<PLBean.ResultBean> list) {

        mdata.addAll(list);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public XRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.action_communitynews_item,viewGroup,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull XRecyclerView.ViewHolder viewHolder, final int i) {
        final ViewHolder viewHolder1= (ViewHolder) viewHolder;
        Uri uri=Uri.parse(mdata.get(i).getHeadPic());
//
        viewHolder1.Communtiy_simple1.setImageURI(uri);

        String[] file = mdata.get(i).getFile().split(",");
        final ArrayList<String> objects = new ArrayList<>();

        objects.addAll(Arrays.asList(file));
        viewHolder1.Communtiy_text1.setText(mdata.get(i).getNickName());
        //日期
        String date = new SimpleDateFormat("HH").format(new java.util.Date(mdata.get(i).getPublishTime()));
        viewHolder1.Communtiy_text2.setText(date+"小时前");
        viewHolder1.Communtiy_text5.setText(mdata.get(i).getContent());
        viewHolder1.signature.setText(mdata.get(i).getSignature());
        viewHolder1.Communtiy_text3.setText(mdata.get(i).getComment()+"");
        viewHolder1.Communtiy_text4.setText(mdata.get(i).getPraise()+"");

        //点金查看更多的评论
        viewHolder1.allpl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( mdata.get(i).getCommunityCommentVoList().size()==0)
                {
                    Toast.makeText(context, "暂时还没有评论哦，快去评论吧…", Toast.LENGTH_SHORT).show();
                }else
                {
                    Intent intent=new Intent(context,NewsAllPlActivity.class);
                    intent.putExtra("communityId",mdata.get(i).getId()+"");
                    intent.putExtra("icon",mdata.get(i).getHeadPic());
                    intent.putExtra("nickname",mdata.get(i).getNickName());
                    context.startActivity(intent);

                }

            }
        });
        //点赞
        if(mdata.get(i).getWhetherGreat()==1)
        {
            viewHolder1.Communtiy_image2.setImageResource(R.mipmap.common_icon_praise_s);
        }else
        {
            viewHolder1.Communtiy_image2.setImageResource(R.mipmap.common_icon_prise_n);
        }

        viewHolder1.Communtiy_image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(setDZOnclick!=null)
                {
                    setDZOnclick.onclick(mdata.get(i).getWhetherGreat(),i,mdata.get(i).getId());
                }
            }
        });

        //判断图片是否有数据
        if(mdata.get(i).getFile().length()==0)
        {
            viewHolder1.asdasdasdasdasdas.setVisibility(View.GONE);
        }else
        {
            GridLayoutManager gridLayoutManager=new GridLayoutManager(context,3);
            viewHolder1.asdasdasdasdasdas.setLayoutManager(gridLayoutManager);
            NewsIconAdapter newsIconAdapter=new NewsIconAdapter(context,file);
            viewHolder1.asdasdasdasdasdas.setAdapter(newsIconAdapter);

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

        viewHolder1.Communtiy_image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(setTuiSetonClick1!=null)
                {
                    setTuiSetonClick1.oncick(mdata.get(i).getId());


                }
            }
        });
        //展示下面评论的标签
        LinearLayoutManager layoutManager=new LinearLayoutManager(context);
        viewHolder1.newstextview.setLayoutManager(layoutManager);
        NewsBeanAdapter newsBeanAdapter=new NewsBeanAdapter(context,mdata.get(i).getCommunityCommentVoList());
        viewHolder1.newstextview.setAdapter(newsBeanAdapter);

        if( mdata.get(i).getCommunityCommentVoList().size()>3)
        {

            viewHolder1.allpl.setText("点击查看更多评论^");
            viewHolder1.allpl.setTextColor(Color.RED);
        }
        else if( mdata.get(i).getCommunityCommentVoList().size()==0)
        {
            viewHolder1.allpl.setText("快来评论呀^");
        }

        //点击头像查看更多
        viewHolder1.Communtiy_simple1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context,QuerypostsActivity.class);
                intent.putExtra("fromUid",mdata.get(i).getUserId()+"");
                intent.putExtra("ickName",mdata.get(i).getNickName());
                intent.putExtra("Headpic",mdata.get(i).getHeadPic());
                context.startActivity(intent);
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
        SimpleDraweeView Communtiy_simple1;
        TextView Communtiy_text1,Communtiy_text5,Communtiy_text2,allpl,signature,Communtiy_text3,Communtiy_text4;
        ImageView Communtiy_image2,Communtiy_image1;
        RecyclerView asdasdasdasdasdas,newstextview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Communtiy_simple1=itemView.findViewById(R.id.Communtiy_simple1);
            Communtiy_text1=itemView.findViewById(R.id.Communtiy_text1);
            Communtiy_text5=itemView.findViewById(R.id.Communtiy_text5);
            Communtiy_text2=itemView.findViewById(R.id.Communtiy_text2);
            signature = itemView.findViewById(R.id.signature);
            allpl = itemView.findViewById(R.id.allpl);
            Communtiy_text3=itemView.findViewById(R.id.Communtiy_text3);
            Communtiy_text4=itemView.findViewById(R.id.Communtiy_text4);
            Communtiy_image2=itemView.findViewById(R.id.Communtiy_image2);
            asdasdasdasdasdas=itemView.findViewById(R.id.asdasdasdasdasdas);
            Communtiy_image1=itemView.findViewById(R.id.Communtiy_image1);
            allpl=itemView.findViewById(R.id.allpl);
            newstextview=itemView.findViewById(R.id.newstextview);
        }
    }
    private setTuiSetonClick1 setTuiSetonClick1;

    public  void  setLick1(setTuiSetonClick1 setTuiSetonClick1)
    {
        this.setTuiSetonClick1=setTuiSetonClick1;
    }
    public  interface   setTuiSetonClick1{
        void  oncick(int mid);
    }


    /* 接口回调 */
    private setDZOnclick setDZOnclick;


    public  void  setMdata(setDZOnclick setDZOnclick)
    {
        this.setDZOnclick=setDZOnclick;
    }
    public  interface setDZOnclick
    {
        void  onclick( int whetherGreat,int i,int communityId);
    }
}
