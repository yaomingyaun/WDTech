package com.wd.tech.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.wd.tech.R;
import com.wd.tech.home.adapter.HomeAdapter;
import com.wd.tech.home.adapter.MeunDetailsAdapter;
import com.wd.tech.home.bean.GreatBean;
import com.wd.tech.home.bean.HomeBean;
import com.wd.tech.utils.AlertDialogUtils;
import com.wd.tech.utils.netWork.Api;
import com.wd.tech.utils.presenter.IPresentermpl;
import com.wd.tech.utils.view.IView;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MeunDetailsActivity extends AppCompatActivity implements IView,View.OnClickListener {

    @BindView(R.id.meunback)
    ImageView meunback;
    @BindView(R.id.meundetails_title)
    TextView meundetailsTitle;
    @BindView(R.id.zi)
    RelativeLayout zi;
    @BindView(R.id.menudetails_recyclerview)
    RecyclerView menudetailsRecyclerview;
    @BindView(R.id.mun_refreshLayout)
    SmartRefreshLayout munRefreshLayout;
    @BindView(R.id.meunsearch_img)
    ImageView meunsearch_img;
    MeunDetailsAdapter meunDetailsAdapter;
    HomeAdapter homeAdapter;
    IPresentermpl iPresentermpl;
    int mid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meun_details);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String plateId = intent.getStringExtra("id");
        mid = Integer.valueOf(plateId);
        meunback.setOnClickListener(this);
        meunsearch_img.setOnClickListener(this);
        iPresentermpl=new IPresentermpl(this);
        iPresentermpl.getRequest(String.format(Api.MINFORECOMMENDLIST,mid,1,15),HomeBean.class);
        homeAdapter = new HomeAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        menudetailsRecyclerview.setLayoutManager(layoutManager);
        menudetailsRecyclerview.setAdapter(homeAdapter);
        homeAdapter.setGreatClick(new HomeAdapter.GreatClick() {
            @Override
            public void click(int circleId, boolean isGreat) {
                HashMap<String, String> map = new HashMap<>();
                map.put("infoId",circleId+"");
                if (isGreat){
                    iPresentermpl.postRequest(Api.ADDCOLLECTION,map,GreatBean.class);
                }else {
                    iPresentermpl.deleteRequest(String.format(Api.CANCELCOLLECTION,circleId),GreatBean.class);
                }
                homeAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.meunback:
                finish();
                break;
            case R.id.meunsearch_img:
                startActivity(new Intent(this, SearchActivity.class));
                break;
        }
    }

    @Override
    public void success(Object data) {
       if(data instanceof HomeBean){
           HomeBean bean= (HomeBean) data;
           List<HomeBean.ResultBean> result = bean.getResult();
           homeAdapter.setHomelist(result);
       }
       if (data instanceof GreatBean){
           GreatBean bean = (GreatBean) data;
           if (bean.getStatus().equals("0000")) {
               Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
           } else {
               Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
           }
           if(bean.getStatus().equals("9999")) {
               AlertDialogUtils alertDialogUtils = new AlertDialogUtils(this);
               alertDialogUtils.show();
           }
       }
    }

    @Override
    public void failure(String error) {

    }
}
