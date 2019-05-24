package com.wd.tech.adapter;

import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;

public class NewsIconAdapter extends RecyclerView.Adapter<NewsIconAdapter.ViewHolder>{
    private Context context;
    private String[] images;

    public NewsIconAdapter(Context context, String[] file) {
        this.images=file;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.newsicon_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.news_icon.setImageURI(images[i]);
        //获取下标
        viewHolder.news_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(iconSetonClick1!=null)
                {
                    iconSetonClick1.oncick(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public  class  ViewHolder extends RecyclerView.ViewHolder{
        SimpleDraweeView   news_icon;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            news_icon=itemView.findViewById(R.id.news_icon);
        }
    }

    private IconSetonClick1 iconSetonClick1;

    public  void  setImages(IconSetonClick1 iconSetonClick1)
    {
        this.iconSetonClick1=iconSetonClick1;
    }
    public  interface   IconSetonClick1{
        void  oncick(int position);
    }
}
