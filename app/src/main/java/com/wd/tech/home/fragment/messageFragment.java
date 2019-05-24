package com.wd.tech.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.wd.tech.R;
import com.wd.tech.home.view.MessagePop;
import com.wd.tech.message.activity.AddFriendActivity;
import com.wd.tech.message.activity.AddGroupActivity;
import com.wd.tech.message.fragment.linkmanFragment;
import com.wd.tech.message.fragment.messagesoonFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class messageFragment extends Fragment {
    @BindView(R.id.mfragment_messge)
    RadioButton mfragmentMessge;
    @BindView(R.id.mfragment_linkman)
    RadioButton mfragmentLinkman;
    @BindView(R.id.rg_layout)
    RadioGroup rgLayout;
    @BindView(R.id.mfragment_vp)
    ViewPager mfragmentVp;
    Unbinder unbinder;
    @BindView(R.id.mfragment_add)
    ImageView mfragmentAdd;
    @BindView(R.id.mfragment_pop)
    MessagePop mfragmentPop;
    private ArrayList<Fragment> list;
    private int a=1;
    private TextView add_friend;
    private TextView add_group;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.messagefragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        //添加list
        list = new ArrayList<>();
        //消息
        list.add(new messagesoonFragment());
        //联系人
        list.add(new linkmanFragment());

        mfragmentVp.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        //获取添加好友和添加群的id
        //add_friend = mfragmentPop.findViewById(R.id.add_friend);
        //add_group = mfragmentPop.findViewById(R.id.add_group);
        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    @OnClick({R.id.mfragment_messge, R.id.mfragment_linkman,R.id.mfragment_add,R.id.add_friend,R.id.add_group})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mfragment_messge:
                mfragmentVp.setCurrentItem(0);
                break;
            case R.id.mfragment_linkman:
                mfragmentVp.setCurrentItem(1);
                break;
            case R.id.mfragment_add:
                mfragmentPop.setVisibility(View.VISIBLE);
                if (a==1){
                    mfragmentPop.setVisibility(View.VISIBLE);
                    a=2;
                }else if((a==2)){
                    mfragmentPop.setVisibility(View.GONE);
                    a=1;
                }
                break;
            case  R.id.add_friend:
                mfragmentPop.setVisibility(View.GONE);
                a=1;
                Intent intent = new Intent(getActivity(),AddFriendActivity.class);
                startActivity(intent);
                break;
            case  R.id.add_group:
                mfragmentPop.setVisibility(View.GONE);
                a=1;
                Intent intent1 = new Intent(getActivity(),AddGroupActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
