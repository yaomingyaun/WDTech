package com.wd.tech.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;

import com.wd.tech.adapter.NewsPLAllAdapter;
import com.wd.tech.bean.NewsPLAllBean;
import com.wd.tech.utils.netWork.Api;
import com.wd.tech.utils.presenter.IPresentermpl;
import com.wd.tech.utils.view.IView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewsAllPlActivity extends AppCompatActivity implements IView {

    @BindView(R.id.newsallpl_RecyclerView)
    RecyclerView newsallpl_RecyclerView;
    NewsPLAllAdapter newsPLAllAdapter;
    IPresentermpl iPresentermpl;

    @BindView(R.id.allpl_icon1)
    SimpleDraweeView allpl_icon1;
    @BindView(R.id.plnickname)
    TextView plnickname;
    @BindView(R.id.pl_back)
    ImageView pl_back;
    private  NewsPLAllBean newsPLAllBean;
    String communityId;
    String icon;
    String nickname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_all_pl);
        //绑定
        ButterKnife.bind(this);
        iPresentermpl = new IPresentermpl(this);
        //接受传值
        Intent intent = getIntent();
        communityId = intent.getStringExtra("communityId");
        icon = intent.getStringExtra("icon");
        nickname = intent.getStringExtra("nickname");
        //赋值
        Uri uri=Uri.parse(icon);
        allpl_icon1.setImageURI(uri);
        plnickname.setText(nickname);
        //创建布局
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        newsallpl_RecyclerView.setLayoutManager(layoutManager);
        newsPLAllAdapter=new NewsPLAllAdapter(this);
        newsallpl_RecyclerView.setAdapter(newsPLAllAdapter);
        //查询
        iPresentermpl.getRequest(String.format(Api.FINDCOMMUNITYUSERCOMMENTLIST,communityId), NewsPLAllBean.class);
    }
@OnClick({R.id.pl_back})
public  void setlick(View view)
{
    switch (view.getId())
    {
        case R.id.pl_back:
            finish();
            break;
            default:break;
    }
}
    @Override
    public void success(Object data) {
        if(data instanceof NewsPLAllBean)
        {
             newsPLAllBean= (NewsPLAllBean) data;

            if(newsPLAllBean.getResult().isEmpty())
            {
                Toast.makeText(this, "当前无最新评论！", Toast.LENGTH_SHORT).show();

            }else
            {
                newsPLAllAdapter.setlist(newsPLAllBean.getResult());
            }
        }
    }
    @Override
    public void failure(String error) {

    }
}
