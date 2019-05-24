package com.wd.tech.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.wd.tech.home.bean.HomeBean;

import java.util.ArrayList;
import java.util.List;

public class MeunDetailsAdapter extends RecyclerView.Adapter<MeunDetailsAdapter.ViewHolder> {
    Context context;
    List<HomeBean.ResultBean.InfoAdvertisingVoBean> list;

    public MeunDetailsAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    public void setList(List<HomeBean.ResultBean.InfoAdvertisingVoBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MeunDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MeunDetailsAdapter.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
