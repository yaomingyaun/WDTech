package com.wd.tech.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;
import com.wd.tech.home.activity.InformationDetailsActivity;
import com.wd.tech.home.bean.InforDetailsBean;

import java.util.ArrayList;
import java.util.List;

public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.ViewHolder> {
    Context context;
    List<InforDetailsBean.ResultBean.InformationListBean> list;

    public RecommendAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    public void setList(List<InforDetailsBean.ResultBean.InformationListBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecommendAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.information_recommend_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendAdapter.ViewHolder viewHolder, final int i) {
        if(list.get(i)!=null){
            viewHolder.mRecommend_image.setImageURI(list.get(i).getThumbnail());
            viewHolder.mRecommend_title.setText(list.get(i).getTitle());
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,InformationDetailsActivity.class);
                    intent.putExtra("id", list.get(i).getId());
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final SimpleDraweeView mRecommend_image;
        private final TextView mRecommend_title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mRecommend_image = itemView.findViewById(R.id.recommend_image);
            mRecommend_title = itemView.findViewById(R.id.recommend_title);
        }
    }
}
