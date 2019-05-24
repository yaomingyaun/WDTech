package com.wd.tech.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wd.tech.R;
import com.wd.tech.home.activity.InformationDetailsActivity;
import com.wd.tech.home.bean.SearchBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    Context context;
    List<SearchBean.ResultBean> list;

    public SearchAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    public void setList(List<SearchBean.ResultBean> list) {
       // this.list.clear();
        this.list = list;
        list=new ArrayList<>();
        notifyDataSetChanged();
    }

    public void addList(List<SearchBean.ResultBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.searchtitle_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        String date = new SimpleDateFormat("yyyy-MM-dd").format( new java.util.Date(list.get(i).getReleaseTime()));
        viewHolder.time_search.setText(date);
        viewHolder.title_search.setText(list.get(i).getTitle());
        viewHolder.name_search.setText(list.get(i).getSource());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InformationDetailsActivity.class);
                list.get(i).getId();
                intent.putExtra("id",list.get(i).getId()+"");
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title_search,name_search,time_search,minute_search;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title_search = itemView.findViewById(R.id.title_search);
            name_search = itemView.findViewById(R.id.name_search);
            time_search = itemView.findViewById(R.id.time_search);
            minute_search = itemView.findViewById(R.id.minute_search);
        }
    }
}
