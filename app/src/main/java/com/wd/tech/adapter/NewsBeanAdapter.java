package com.wd.tech.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;
import com.wd.tech.bean.PLBean;

import java.util.ArrayList;
import java.util.List;

public class NewsBeanAdapter extends RecyclerView.Adapter<NewsBeanAdapter.ViewHolder>{
    private Context context;
    private List<PLBean.ResultBean.CommunityCommentVoListBean> mdata=new ArrayList<>();
    public NewsBeanAdapter(Context context, List<PLBean.ResultBean.CommunityCommentVoListBean> communityCommentVoList) {

        this.context=context;
        this.mdata=communityCommentVoList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.news_bean_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        viewHolder.newtext1.setText(mdata.get(i).getNickName());
        viewHolder.newtext2.setText(mdata.get(i).getContent());
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public  class  ViewHolder extends RecyclerView.ViewHolder{
        TextView newtext1,newtext2;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            newtext1=itemView.findViewById(R.id.newtext1);
            newtext2=itemView.findViewById(R.id.newstext2);
        }
    }


}
