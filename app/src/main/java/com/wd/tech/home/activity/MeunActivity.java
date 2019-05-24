package com.wd.tech.home.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.wd.tech.R;
import com.wd.tech.home.adapter.MeunAdapter;
import com.wd.tech.home.bean.MeunBean;
import com.wd.tech.utils.netWork.Api;
import com.wd.tech.utils.presenter.IPresentermpl;
import com.wd.tech.utils.view.IView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MeunActivity extends AppCompatActivity implements IView {

    @BindView(R.id.xing)
    TextView xing;
    @BindView(R.id.rang)
    TextView rang;
    @BindView(R.id.meun_xrecycle)
    RecyclerView meunXrecycle;
    MeunAdapter meunAdapter;
    IPresentermpl iPresentermpl;
    int PAGE_NUM=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meun);
        ButterKnife.bind(this);
        iPresentermpl=new IPresentermpl(this);
        meunAdapter=new MeunAdapter(this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        meunXrecycle.setLayoutManager(gridLayoutManager);

        init();
    }
    public void init(){
        iPresentermpl.getRequest(Api.FINDALLINFOPLATE,MeunBean.class);
    }
    @Override
    public void success(Object data) {
        if(data instanceof MeunBean){
            MeunBean bean= (MeunBean) data;
            List<MeunBean.ResultBean> result = bean.getResult();
            meunAdapter.setList(result);
            meunXrecycle.setAdapter(meunAdapter);
        }
    }
    @Override
    public void failure(String error) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPresentermpl.deatch();
    }
}
