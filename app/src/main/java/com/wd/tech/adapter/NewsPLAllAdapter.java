package com.wd.tech.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;
import com.wd.tech.bean.NewsPLAllBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class NewsPLAllAdapter extends RecyclerView.Adapter<NewsPLAllAdapter.ViewHolder> {

    private List<NewsPLAllBean.ResultBean> mdata;
    private Context context;

    public NewsPLAllAdapter(Context context) {
        this.context = context;
        mdata=new ArrayList<>();
    }
    public  void  setlist( List<NewsPLAllBean.ResultBean> list)
    {
        this.mdata=list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.action_communitynews_allpl_item, viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

         Uri uri=Uri.parse(mdata.get(i).getHeadPic());
        viewHolder.allpl_icon.setImageURI(uri);
        viewHolder.allpl_name.setText(mdata.get(i).getNickName());
        String date = new SimpleDateFormat("HH").format(new java.util.Date(mdata.get(i).getCommentTime()));
       viewHolder.allpl_time.setText(date+"小时前");
    viewHolder.allpl_content.setText(mdata.get(i).getContent());

    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public  class  ViewHolder extends RecyclerView.ViewHolder{

        TextView allpl_name,allpl_time,allpl_content;
        SimpleDraweeView allpl_icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            allpl_name=itemView.findViewById(R.id.allpl_name);
            allpl_time=itemView.findViewById(R.id.allpl_time);
            allpl_content=itemView.findViewById(R.id.allpl_content);
            allpl_icon=itemView.findViewById(R.id.allpl_icon);
        }
    }
}
