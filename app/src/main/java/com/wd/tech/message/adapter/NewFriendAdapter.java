package com.wd.tech.message.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;
import com.wd.tech.message.activity.NewFriendActivity;
import com.wd.tech.message.bean.FriendListBeans;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewFriendAdapter extends RecyclerView.Adapter<NewFriendAdapter.ViewHolder> {

    Context context;

    public NewFriendAdapter(NewFriendActivity newFriendActivity) {
        this.context = newFriendActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_newfriend, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        int status = result.get(i).getStatus();
        if (status == 1) {
            viewHolder.activityNewfriendStatus.setText("待处理");
            viewHolder.activityNewfriendVisibility.setVisibility(View.VISIBLE);
        }
        if (status == 2) {
            viewHolder.activityNewfriendStatus.setText("已通过");
            viewHolder.activityNewfriendVisibility.setVisibility(View.GONE);
        }
        if (status == 3) {
            viewHolder.activityNewfriendStatus.setText("已拒绝");
            viewHolder.activityNewfriendVisibility.setVisibility(View.GONE);
        }
        viewHolder.activityNewfriendImg.setImageURI(Uri.parse(result.get(i).getFromHeadPic()));
        viewHolder.activityNewfriendName.setText(result.get(i).getFromNickName());
        viewHolder.activityNewfriendRemark.setText(result.get(i).getRemark());
        viewHolder.activityNewfriendOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.ClickListener(result.get(i).getNoticeId(), 2);
                viewHolder.activityNewfriendStatus.setText("已通过");
                viewHolder.activityNewfriendVisibility.setVisibility(View.GONE);
            }
        });
        viewHolder.activityNewfriendNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.ClickListener(result.get(i).getNoticeId(), 3);
                viewHolder.activityNewfriendStatus.setText("已拒绝");
                viewHolder.activityNewfriendVisibility.setVisibility(View.GONE);
            }
        });

    }

    public interface OnClickListener {
        void ClickListener(int noticeId, int flag);
    }

    OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    List<FriendListBeans.ResultBean> result = new ArrayList<>();

    public void setData(List<FriendListBeans.ResultBean> result) {
        if (result != null) {
            this.result.clear();
            this.result.addAll(result);
            notifyDataSetChanged();
        }
    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.activity_newfriend_time)
        TextView activityNewfriendTime;
        @BindView(R.id.activity_newfriend_img)
        SimpleDraweeView activityNewfriendImg;
        @BindView(R.id.activity_newfriend_name)
        TextView activityNewfriendName;
        @BindView(R.id.activity_newfriend_remark)
        TextView activityNewfriendRemark;
        @BindView(R.id.activity_newfriend_no)
        Button activityNewfriendNo;
        @BindView(R.id.activity_newfriend_ok)
        Button activityNewfriendOk;
        @BindView(R.id.activity_newfriend_status)
        TextView activityNewfriendStatus;
        @BindView(R.id.activity_newfriend_visibility)
        RelativeLayout activityNewfriendVisibility;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
