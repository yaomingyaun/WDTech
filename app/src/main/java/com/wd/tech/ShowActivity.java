package com.wd.tech;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.bean.infoByUserBean;
import com.wd.tech.home.fragment.communityFragment;
import com.wd.tech.home.fragment.informationFragment;
import com.wd.tech.home.fragment.messageFragment;
import com.wd.tech.home.view.NoScrollViewPager;
import com.wd.tech.utils.netWork.Api;
import com.wd.tech.utils.presenter.IPresentermpl;
import com.wd.tech.utils.view.IView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShowActivity extends AppCompatActivity implements IView {

    @BindView(R.id.pager)
    NoScrollViewPager pager;
    @BindView(R.id.information_button)
    RadioButton informationButton;
    @BindView(R.id.message_button)
    RadioButton messageButton;
    @BindView(R.id.community_button)
    RadioButton communityButton;
    @BindView(R.id.group)
    RadioGroup group;
    List<Fragment> list = new ArrayList<>();
    @BindView(R.id.my_collect)
    RelativeLayout myCollect;
    @BindView(R.id.my_attention)
    RelativeLayout myAttention;
    @BindView(R.id.my_card)
    RelativeLayout myCard;
    @BindView(R.id.my_notice)
    RelativeLayout myNotice;
    @BindView(R.id.my_integratiion)
    RelativeLayout myIntegratiion;
    @BindView(R.id.my_task)
    RelativeLayout myTask;
    @BindView(R.id.my_setting)
    RelativeLayout mySetting;
    @BindView(R.id.main_lin)
    LinearLayout mainLin;
    @BindView(R.id.my_tou)
    SimpleDraweeView myTou;
    @BindView(R.id.my_name)
    TextView myName;
    @BindView(R.id.my_geqian)
    TextView myGeqian;
    @BindView(R.id.my_qianda)
    ImageView myQianda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        ButterKnife.bind(this);
        IPresentermpl iPresentermpl = new IPresentermpl(this);
        iPresentermpl.getRequest(Api.INFOBYUSERID, infoByUserBean.class);
        //添加list
        list = new ArrayList<>();
        list.add(new informationFragment());
        list.add(new messageFragment());
        list.add(new communityFragment());
        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.information_button:
                        pager.setCurrentItem(0);
                        break;
                    case R.id.message_button:
//                        Intent intent=new Intent(ShowActivity.this,LoginActivity.class);
//                        startActivity(intent);
                        pager.setCurrentItem(1);
                        break;
                    case R.id.community_button:
                        pager.setCurrentItem(2);
                        break;
                }
            }
        });
    }

    @OnClick({R.id.my_collect, R.id.my_attention, R.id.my_card, R.id.my_notice, R.id.my_integratiion, R.id.my_task, R.id.my_setting,R.id.my_tou, R.id.my_qianda})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_collect:
                Intent intent = new Intent(ShowActivity.this, CollectActivity.class);
                startActivity(intent);
                break;
            case R.id.my_attention:
                Intent intent1 = new Intent(ShowActivity.this, AttentionActivity.class);
                startActivity(intent1);
                break;
            case R.id.my_card:
//                Intent intent2=new Intent(ShowActivity.this,CollectActivity.class);
//                startActivity(intent2);
                break;
            case R.id.my_notice:
//                Intent intent3=new Intent(ShowActivity.this,CollectActivity.class);
//                startActivity(intent3);
                break;
            case R.id.my_integratiion:
//                Intent intent4=new Intent(ShowActivity.this,CollectActivity.class);
//                startActivity(intent4);
                break;
            case R.id.my_task:
//                Intent intent5=new Intent(ShowActivity.this,CollectActivity.class);
//                startActivity(intent5);
                break;
            case R.id.my_setting:
                Intent intent6 = new Intent(ShowActivity.this, SettingActivity.class);
                startActivity(intent6);
                break;
            case R.id.my_tou:
                Intent intent7 = new Intent(ShowActivity.this, MessageActivity.class);
                startActivity(intent7);
                break;
            case R.id.my_qianda:
                break;
        }
    }

    @Override
    public void success(Object data) {
        if (data instanceof infoByUserBean) {
            infoByUserBean info = (infoByUserBean) data;
            infoByUserBean.ResultBean result = info.getResult();
            Uri uri=Uri.parse(result.getHeadPic());
//            myTou.setImageURI(uri);
//            myName.setText(result.getNickName());
//            myGeqian.setText(result.getSignature());
        }
    }

    @Override
    public void failure(String error) {

    }
}
