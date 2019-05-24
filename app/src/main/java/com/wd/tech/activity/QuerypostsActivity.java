package com.wd.tech.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wd.tech.R;
import com.wd.tech.adapter.CommunityNewsAdapter;
import com.wd.tech.adapter.QueryPostAdapter;
import com.wd.tech.bean.FailBean;
import com.wd.tech.bean.FocuSuccessBean;
import com.wd.tech.bean.NewsDZBean;
import com.wd.tech.bean.NewsQXBean;
import com.wd.tech.bean.PLBean;
import com.wd.tech.bean.QueryPostBean;
import com.wd.tech.message.activity.AddFriendsActivity;
import com.wd.tech.message.activity.UserInfoActivity;
import com.wd.tech.user.activity.MainActivity;
import com.wd.tech.utils.netWork.Api;
import com.wd.tech.utils.presenter.IPresentermpl;
import com.wd.tech.utils.view.IView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuerypostsActivity extends AppCompatActivity implements IView {
    @BindView(R.id.query_iconbj)
    SimpleDraweeView query_iconbj;
    @BindView(R.id.query_icon1)
    SimpleDraweeView query_icon1;
    @BindView(R.id.query_xrecyview)
    XRecyclerView query_xrecyview;
    QueryPostAdapter queryPostAdapter;
    @BindView(R.id.querypost_pop)
    ImageView querypost_pop;
        PopupWindow popupWindow;
    @BindView(R.id.query_nickname)
    TextView query_nickname;
    private String fromUid;
    IPresentermpl iPresentermpl;
    @BindView(R.id.querypost_gz)
    TextView querypost_gz;
    @BindView(R.id.querypost_jhy)
            TextView querypost_jhy;
    int page=1;
    private  int position;
    private  QueryPostBean queryPostBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queryposts);
        ButterKnife.bind(this);
        iPresentermpl=new IPresentermpl(this);
        Intent intent=getIntent();
        fromUid=   intent.getStringExtra("fromUid");
        //布局
        getManager();
}
@OnClick({R.id.querypost_pop,R.id.querypost_gz,R.id.querypost_jhy})
public  void  setlick(View view)
{
    switch (view.getId())
    {
        //点击出现加好友以及关注按钮
        case R.id.querypost_pop:
            querypost_gz.setVisibility(View.VISIBLE);
            querypost_jhy.setVisibility(View.VISIBLE);
            querypost_pop.setVisibility(View.GONE);
            break;
            //点击确定是否关注
        case R.id.querypost_gz:
            if(queryPostBean.getResult().get(0).getCommunityUserVo().getWhetherFollow()==1)
            {
                iPresentermpl.deleteRequest(String.format(Api.CANCELFOLLOW,fromUid),FailBean.class);
            }else
            {
                Map<String,String> map=new HashMap<>();
                map.put("focusId",fromUid);
                iPresentermpl.postData(Api.ADDFOLLOW,map,FocuSuccessBean.class);
            }
            break;
        case R.id.querypost_jhy:
            Intent intent = new Intent(QuerypostsActivity.this, AddFriendsActivity.class);
            //添加传送数据
            intent.putExtra("userid", Integer.parseInt(queryPostBean.getResult().get(0).getCommunityUserVo().getUserId()+""));
            intent.putExtra("img",queryPostBean.getResult().get(0).getCommunityUserVo().getHeadPic());
            intent.putExtra("name",queryPostBean.getResult().get(0).getCommunityUserVo().getNickName());
            startActivity(intent);
            break;
    }
}
    //布局
    public  void  getManager()
    {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        query_xrecyview.setLayoutManager(layoutManager);
        queryPostAdapter = new QueryPostAdapter(this);
        query_xrecyview.setAdapter(queryPostAdapter);
        query_xrecyview.setPullRefreshEnabled(true);
        query_xrecyview.setLoadingMoreEnabled(true);
        //刷新
        query_xrecyview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;
                lodata();
            }
            @Override
            public void onLoadMore() {
                lodata();
            }
        });
        lodata();
        //点赞
        queryPostAdapter.setContext(new QueryPostAdapter.setDZOnclick() {
            @Override
            public void onclick(int whetherGreat, int i, int communityId) {
                position = i;
                if(whetherGreat==1)
                {
                    cancelGreatData(communityId);
                }else
                {
                    getGreatData(communityId);
                }
            }

        });
        //点击跳去查看更多的评论
        queryPostAdapter.setMdata(new QueryPostAdapter.SetOnclickAll() {
            @Override
            public void onclick2(int position) {
                Intent intent1=new Intent(QuerypostsActivity.this,NewsAllPlActivity.class);
                intent1.putExtra("communityId",queryPostBean.getResult().get(0).getCommunityUserVo().getUserId()+"");
                intent1.putExtra("icon",queryPostBean.getResult().get(0).getCommunityUserVo().getHeadPic());
                intent1.putExtra("nickname",queryPostBean.getResult().get(0).getCommunityUserVo().getNickName());
                startActivity(intent1);
            }
        });

    }
    //点赞
    public void getGreatData(int communityId){
        Map<String,String> map=new HashMap<>();
        map.put("communityId",communityId+"");
        iPresentermpl.postRequest(Api.ADDCOMMUNITYGREAT,map,NewsDZBean.class);
    }
    //取消点赞
    public void cancelGreatData(int communityId){

        iPresentermpl.deleteRequest(String.format(Api.CANCELCOMMUNTYGREAT,communityId),NewsQXBean.class);
    }
    //刷新
    public void lodata ()
    {
        iPresentermpl.getRequest(String.format(Api.FINDUSERPOSTBYID,fromUid, page), QueryPostBean.class);
    }

    @Override
    public void success(Object data) {
        if(data instanceof QueryPostBean)
        {
              queryPostBean= (QueryPostBean) data;
               String name= queryPostBean.getResult().get(0).getCommunityUserVo().getNickName();
            Uri uri=Uri.parse(queryPostBean.getResult().get(0).getCommunityUserVo().getHeadPic());
            query_iconbj.setImageURI(uri);
            query_icon1.setImageURI(uri);
            query_nickname.setText(name);
            if(queryPostBean.getResult().get(0).getCommunityUserVo().getWhetherFollow()==1)
            {
                querypost_gz.setText("取消关注");
            }else if(queryPostBean.getResult().get(0).getCommunityUserVo().getWhetherFollow()==2)
            {
                querypost_gz.setText("+ 关注");
            }
            if(page==1)
            {
                queryPostAdapter.setlist(queryPostBean.getResult().get(0).getCommunityUserPostVoList());
            }else
            {
                queryPostAdapter.addlist(queryPostBean.getResult().get(0).getCommunityUserPostVoList());
            }
            page++;
            query_xrecyview.refreshComplete();
            query_xrecyview.loadMoreComplete();
        }
        if(data instanceof  NewsDZBean)
        {
            NewsDZBean newsDZBean= (NewsDZBean) data;
            if(newsDZBean.getMessage().equals("点赞成功"))
            {
                Toast.makeText(this, newsDZBean.getMessage()+"", Toast.LENGTH_SHORT).show();
                queryPostAdapter.getGivePraise(position);
            }else
            {
                Toast.makeText(this, newsDZBean.getMessage()+"", Toast.LENGTH_SHORT).show();
            }
        }
        if(data instanceof  NewsQXBean)
        {
            NewsQXBean newsQXBean= (NewsQXBean) data;
            if(newsQXBean.getMessage().equals("取消成功"))
            {
                Toast.makeText(this, "取消成功", Toast.LENGTH_SHORT).show();
                queryPostAdapter.getCancelPraise(position);
            }else
            {
                Toast.makeText(this, newsQXBean.getMessage()+"", Toast.LENGTH_SHORT).show();
            }
        }
        if(data instanceof FocuSuccessBean)
        {
            FocuSuccessBean focuSuccessBean= (FocuSuccessBean) data;
            if(focuSuccessBean.getMessage().equals("关注成功"))
            {
                Toast.makeText(this, focuSuccessBean.getMessage()+"", Toast.LENGTH_SHORT).show();
                queryPostBean.getResult().get(0).getCommunityUserVo().setWhetherFollow(1);
                lodata();
            }else
            {
                Toast.makeText(this, focuSuccessBean.getMessage()+"", Toast.LENGTH_SHORT).show();
            }
        }
        if(data instanceof FailBean)
        {
            FailBean bean= (FailBean) data;
            if(bean.getMessage().equals("取消成功"))
            {
                Toast.makeText(this, bean.getMessage()+"", Toast.LENGTH_SHORT).show();
                queryPostBean.getResult().get(0).getCommunityUserVo().setWhetherFollow(2);
                lodata();
            }else
            {
                Toast.makeText(this, bean.getMessage()+"", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void failure(String error) {

    }
}
