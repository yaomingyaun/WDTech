package com.wd.tech.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;
import com.wd.tech.bean.FindCollectBean;

import java.util.ArrayList;
import java.util.List;

public class CollectAdapter extends RecyclerView.Adapter<CollectAdapter.ViewHolder> {
    private Context context;
    private List<FindCollectBean.ResultBean> result;

    public CollectAdapter(Context context) {
        this.context = context;
        result=new ArrayList<>();
    }
    public void setList(List<FindCollectBean.ResultBean> result) {
        this.result = result;

        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.my_collect_item, viewGroup, false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final int id = result.get(i).getId();
        viewHolder.title.setText(result.get(i).getTitle());
        viewHolder.writer.setText(result.get(i).getInfoId());
        Uri uri=Uri.parse(result.get(i).getThumbnail());
        viewHolder.img.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView img;
        private final TextView title;
        private final TextView writer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.my_collect_img);
            title = itemView.findViewById(R.id.my_collect_title);
            writer= itemView.findViewById(R.id.my_collect_writer);
        }
    }
}
