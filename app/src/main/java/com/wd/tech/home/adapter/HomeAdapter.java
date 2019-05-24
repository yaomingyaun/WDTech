package com.wd.tech.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;
import com.wd.tech.home.activity.InformationDetailsActivity;
import com.wd.tech.home.activity.NewsAdDetailsActivity;
import com.wd.tech.home.bean.HomeBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<HomeBean.ResultBean> homelist;
    public static final int TYPE_RIGHT_IMAGE = 1;
    public static final int TYPE_THREE_IMAGE = 2;
    private int whetherAdvertising;

    public HomeAdapter(Context context) {
        this.context = context;
        homelist=new ArrayList<>();
    }

    public void setHomelist(List<HomeBean.ResultBean> homelist) {
        this.homelist.clear();
        this.homelist = homelist;
        notifyDataSetChanged();
    }
    public void addHomelist(List<HomeBean.ResultBean> homelist) {
        this.homelist.addAll(homelist);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        whetherAdvertising = homelist.get(position).getWhetherAdvertising();
        if(whetherAdvertising==2){
            return TYPE_RIGHT_IMAGE;
        }else {
            return TYPE_THREE_IMAGE;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(i==TYPE_RIGHT_IMAGE){
            View view = LayoutInflater.from(context).inflate(R.layout.informationnews_item, viewGroup, false);
            return new InformationListMessage(view);
        }else {
            View view = LayoutInflater.from(context).inflate(R.layout.informationad_item, viewGroup, false);
           return new InformationListGuangGao(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, final int i) {
        if (viewHolder instanceof InformationListMessage) {
            String thumbnail = homelist.get(i).getThumbnail();
            ((InformationListMessage) viewHolder).title.setText(homelist.get(i).getTitle());
            ((InformationListMessage) viewHolder).details.setText(homelist.get(i).getSummary());
            ((InformationListMessage) viewHolder).simpleview.setImageURI(thumbnail);
            ((InformationListMessage) viewHolder).zuozhe.setText(homelist.get(i).getSource());
            if (homelist.get(i).getWhetherPay() == 1) {
                ((InformationListMessage) viewHolder).qiandai.setVisibility(View.VISIBLE);
            } else {
                ((InformationListMessage) viewHolder).qiandai.setVisibility(View.GONE);
            }
            String date = new SimpleDateFormat("yyyy-MM-dd").format( new java.util.Date(homelist.get(i).getReleaseTime()));
            ((InformationListMessage) viewHolder).time.setText(date);
            ((InformationListMessage) viewHolder).fenxiangshu.setText(homelist.get(i).getShare() + "");
            ((InformationListMessage) viewHolder).shoucangshu.setText(homelist.get(i).getCollection() + "");
            ((InformationListMessage) viewHolder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = homelist.get(i).getId();
                    Intent intent = new Intent(context, InformationDetailsActivity.class);
                    intent.putExtra("id",id+"");
                    context.startActivity(intent);
                }
            });
            if (homelist.get(i).getWhetherCollection()==1){
                Glide.with(context).load(R.mipmap.common_icon_collect_s).into(((InformationListMessage) viewHolder).shoucang);
            }else{
                Glide.with(context).load(R.mipmap.common_icon_collect_n).into(((InformationListMessage) viewHolder).shoucang);
            }
            ((InformationListMessage) viewHolder).shoucang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(greatClick!=null){
                        if (homelist.get(i).getWhetherCollection()==1){
                            homelist.get(i).setCollection(homelist.get(i).getCollection()-1);
                            homelist.get(i).setWhetherCollection(2);
                        }else {
                            homelist.get(i).setWhetherCollection(1);
                            homelist.get(i).setCollection(homelist.get(i).getCollection()+1);
                        }
                        greatClick.click(homelist.get(i).getId(),homelist.get(i).getWhetherCollection()==1);
                        notifyDataSetChanged();
                    }
                }
            });
            ((InformationListMessage) viewHolder).fenxiang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    shareClick.click(homelist.get(i).getId());
                }
            });
        }
        if (viewHolder instanceof InformationListGuangGao) {
            if (homelist.get(i).getInfoAdvertisingVo().getPic().equals("")) {
                ((InformationListGuangGao) viewHolder).simpleview.setImageURI("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1550659913819&di=3fb9f9de20a37a9bf1a101983656298f&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201708%2F05%2F20170805134053_HzALE.png");
                ((InformationListGuangGao) viewHolder).textwenben.setText(homelist.get(i).getInfoAdvertisingVo().getContent());
            } else {
                String pic = homelist.get(i).getInfoAdvertisingVo().getPic();
                String[] split = pic.split("\\?");
                ((InformationListGuangGao) viewHolder).simpleview.setImageURI(split[0]);
                ((InformationListGuangGao) viewHolder).textwenben.setText(homelist.get(i).getInfoAdvertisingVo().getContent());
                ((InformationListGuangGao) viewHolder).itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String adurl = homelist.get(i).getInfoAdvertisingVo().getUrl();
                        Intent intent = new Intent(context, NewsAdDetailsActivity.class);
                        intent.putExtra("adurl", adurl);
                        context.startActivity(intent);
                    }
                });

            }
        }

    }

    @Override
    public int getItemCount() {
        return homelist.size();
    }

    public class InformationListMessage extends RecyclerView.ViewHolder {
        private SimpleDraweeView simpleview;
        private TextView title;
        private TextView details;
        private TextView zuozhe;
        private TextView time;
        private ImageView shoucang;
        private ImageView fenxiang;
        private TextView fenxiangshu;
        private TextView shoucangshu;
        private ImageView qiandai;

        public InformationListMessage(@NonNull View itemView) {
            super(itemView);
            simpleview = itemView.findViewById(R.id.simpleview);
            title = itemView.findViewById(R.id.title);
            details = itemView.findViewById(R.id.details);
            zuozhe = itemView.findViewById(R.id.zuozhe);
            time = itemView.findViewById(R.id.time);
            shoucang = itemView.findViewById(R.id.shoucang);
            fenxiangshu = itemView.findViewById(R.id.fenxiangshu);
            shoucangshu = itemView.findViewById(R.id.shoucangshu);
            fenxiang = itemView.findViewById(R.id.fenxiang);
            qiandai = itemView.findViewById(R.id.qiandai);

        }
    }
    public class InformationListGuangGao extends RecyclerView.ViewHolder {
        private TextView textwenben;
        private SimpleDraweeView simpleview;

        public InformationListGuangGao(@NonNull View itemView) {
            super(itemView);
            textwenben = itemView.findViewById(R.id.textwenben);
            simpleview = itemView.findViewById(R.id.simpleview);
        }
    }
    private GreatClick greatClick;
    public void setGreatClick(GreatClick greatClick)
    {
        this.greatClick = greatClick;
    }
    public interface GreatClick{
        void click(int circleId,boolean isGreat);
    }
    private ShareClick shareClick;
    public void setShareClick(ShareClick shareClick)
    {
        this.shareClick = shareClick;
    }
    public interface ShareClick{
        void click(int circleId);
    }
}
