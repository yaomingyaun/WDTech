package com.wd.tech.message.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wd.tech.R;
import com.wd.tech.app.Apis;
import com.wd.tech.message.adapter.NewFriendAdapter;
import com.wd.tech.message.bean.FriendListBeans;
import com.wd.tech.message.bean.ReViewFriendBeans;
import com.wd.tech.message.view.HeadView;
import com.wd.tech.utils.presenter.IPresentermpl;
import com.wd.tech.utils.view.IView;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewFriendActivity extends AppCompatActivity implements IView {

    @BindView(R.id.activity_newfriend_recycle)
    XRecyclerView activityNewfriendRecycle;
    @BindView(R.id.newfriend_head_view)
    HeadView newfriendHeadView;
    private IPresentermpl iPresentermpl;
    private NewFriendAdapter newFriendAdapter;
    private ImageView head_back;
    private TextView head_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_friend);
        ButterKnife.bind(this);
        head_back = newfriendHeadView.findViewById(R.id.head_back);
        head_name = newfriendHeadView.findViewById(R.id.head_name);
        head_name.setText("通知");
        iPresentermpl = new IPresentermpl(this);
        activityNewfriendRecycle.setLayoutManager(new LinearLayoutManager(this));
        iPresentermpl.getRequest(Apis.FINDFRIENDNOTICEPAGELIST+"page="+"1"+"&count="+"5", FriendListBeans.class);
        newFriendAdapter = new NewFriendAdapter(this);
        activityNewfriendRecycle.setAdapter(newFriendAdapter);
        newFriendAdapter.setOnClickListener(new NewFriendAdapter.OnClickListener() {
            @Override
            public void ClickListener(int noticeId, int flag) {
                HashMap<String,String> hashMap=new HashMap();
                hashMap.put("noticeId",noticeId+"");
                hashMap.put("flag",flag+"");
                iPresentermpl.putRequest(Apis.REVIEWFRIENDAPPLY,hashMap,ReViewFriendBeans.class);
            }
        });
    }

    @Override
    public void success(Object data) {
        if (data instanceof FriendListBeans) {
            FriendListBeans friendListBeans = (FriendListBeans) data;
            if (friendListBeans.getStatus().equals("0000")) {
                List<FriendListBeans.ResultBean> result = friendListBeans.getResult();
                newFriendAdapter.setData(result);
            }
        }
        if (data instanceof ReViewFriendBeans) {
            ReViewFriendBeans reViewFriendBeans = (ReViewFriendBeans) data;
            Log.i("sadce", "success: "+reViewFriendBeans.getStatus());
        }
    }

    @Override
    public void failure(String error) {

    }
}
