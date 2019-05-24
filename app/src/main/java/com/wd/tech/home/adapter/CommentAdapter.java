package com.wd.tech.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;
import com.wd.tech.home.bean.CommentBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    Context mContext;
    List<CommentBean.ResultBean> list;

    public CommentAdapter(Context mContext) {
        this.mContext = mContext;
        list=new ArrayList<>();
    }

    public void setList(List<CommentBean.ResultBean> list) {
        this.list.clear();
        this.list = list;
        notifyDataSetChanged();
    }

    public void addList(List<CommentBean.ResultBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(mContext, R.layout.allcomment_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.ViewHolder viewHolder, int i) {
        viewHolder.mComment_image.setImageURI(list.get(i).getHeadPic());
        //long commentTime = list.get(i).getCommentTime();
        //String mm_dd = String.format(new SimpleDateFormat().format(), commentTime);
        viewHolder.mComment_name.setText(list.get(i).getNickName());
        String date = new SimpleDateFormat("yyyy-MM-dd").format( new java.util.Date(list.get(i).getCommentTime()));
        viewHolder.mComment_time.setText(date);
        viewHolder.mComment_message.setText(list.get(i).getContent());
       // String unicode = EmojiTransformationUtils.unicodeToString(list.get(i).getContent());
        //viewHolder.mComment_message.setText(unicode+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final SimpleDraweeView mComment_image;
        private final TextView mComment_name;
        private final TextView mComment_time;
        private final TextView mComment_message;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mComment_image = itemView.findViewById(R.id.comment_image);
            mComment_name = itemView.findViewById(R.id.comment_name);
            mComment_time = itemView.findViewById(R.id.comment_time);
            mComment_message = itemView.findViewById(R.id.comment_message);
        }
    }
}
