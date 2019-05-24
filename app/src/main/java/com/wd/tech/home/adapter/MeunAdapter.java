package com.wd.tech.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;
import com.wd.tech.home.activity.MeunDetailsActivity;
import com.wd.tech.home.bean.MeunBean;

import java.util.ArrayList;
import java.util.List;

public class MeunAdapter extends RecyclerView.Adapter<MeunAdapter.ViewHolder> {
    Context context;
    List<MeunBean.ResultBean> list;

    public MeunAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    public void setList(List<MeunBean.ResultBean> list) {
        if(list!=null) {
            this.list.clear();
            this.list = list;
            notifyDataSetChanged();
        }
    }
    public void addList(List<MeunBean.ResultBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MeunAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_meun, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MeunAdapter.ViewHolder viewHolder, final int i) {
        Uri uri = Uri.parse(list.get(i).getPic());
        viewHolder.meun_img.setImageURI(uri);
        viewHolder.meun_nei.setText(list.get(i).getName());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MeunDetailsActivity.class);
                 intent.putExtra("id", list.get(i).getId()+"");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView meun_img;
        TextView mean_title,meun_nei;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            meun_img = itemView.findViewById(R.id.meun_img);
            meun_nei = itemView.findViewById(R.id.meun_nei);
        }
    }
}
