package com.wd.tech;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.wd.tech.adapter.CollectAdapter;
import com.wd.tech.bean.FindCollectBean;
import com.wd.tech.utils.netWork.Api;
import com.wd.tech.utils.presenter.IPresentermpl;
import com.wd.tech.utils.presenter.Presenter;
import com.wd.tech.utils.view.IView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CollectActivity extends AppCompatActivity implements IView {

    @BindView(R.id.my_back)
    ImageView myBack;
    @BindView(R.id.my_clear)
    ImageView myClear;
    @BindView(R.id.my_collect_relative)
    RecyclerView myCollectRelative;
    private CollectAdapter collectAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        ButterKnife.bind(this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myCollectRelative.setLayoutManager(linearLayoutManager);
        IPresentermpl iPresentermpl=new IPresentermpl(this);
        iPresentermpl.getRequest(Api.FINDALLINFOCOLLECTION,FindCollectBean.class);
        collectAdapter = new CollectAdapter(this);
    }

    @Override
    public void success(Object data) {
        if (data instanceof FindCollectBean){
            FindCollectBean findCollectBean= (FindCollectBean) data;
            final List<FindCollectBean.ResultBean> result = findCollectBean.getResult();
            Log.e("AABB",result+"");
            collectAdapter.setList(result);
            myCollectRelative.setAdapter(collectAdapter);
        }
    }

    @Override
    public void failure(String error) {

    }
}
