package com.wd.tech.message.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.adapter.LinkmanFriendAdapter;
import com.wd.tech.app.Apis;
import com.wd.tech.message.activity.GroupActivity;
import com.wd.tech.message.activity.NewFriendActivity;
import com.wd.tech.message.bean.bean1;
import com.wd.tech.message.bean.bean2;
import com.wd.tech.utils.EListViewUtils;
import com.wd.tech.utils.presenter.IPresentermpl;
import com.wd.tech.utils.serchutils;
import com.wd.tech.utils.view.IView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class linkmanFragment extends Fragment implements LinkmanFriendAdapter.OnGroupExpanded, IView {


    @BindView(R.id.linkman_search_ed)
    EditText linkmanSearchEd;
    @BindView(R.id.linkman_search_iv)
    ImageView linkmanSearchIv;
    @BindView(R.id.linkman_delete_iv)
    ImageView linkmanDeleteIv;
    @BindView(R.id.search_edit_frame)
    FrameLayout searchEditFrame;
    @BindView(R.id.linkman_linear)
    LinearLayout linkmanLinear;
    @BindView(R.id.text_view)
    TextView textView;
    @BindView(R.id.linkman_expandable)
    ExpandableListView mElistview;
    Unbinder unbinder;
    public String[] titleStrings = {"朋友", "好朋友", "基友"};
    public String[][] nameStrings = {
            {"张先生", "刘先生", "李先生", "杜先生", "小弟弟"},
            {"鹿晗", "李易峰", "吴亦凡", "王俊凯"},
            {"马云", "马化腾", "雷军", "刘强东"}
    };
    @BindView(R.id.linkman_linear1)
    LinearLayout linkmanLinear1;
    private LinkmanFriendAdapter mAdapter;
    List<bean1.ResultBean> mList = new ArrayList<>();
    private IPresentermpl iPresentermpl;
    private List<bean2.ResultBean> firendsResult;
    private List<bean1.ResultBean> groupResult;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //玳瑁
        View view = inflater.inflate(R.layout.fragment_linkman, container, false);
        unbinder = ButterKnife.bind(this, view);
        serchutils.clearButtonListener(linkmanSearchEd, linkmanDeleteIv, linkmanSearchIv);
        //数据请求
        getData();
        //分组 好友
        initAdapter();
        initListenter();
        return view;
    }

    private void getData() {
        iPresentermpl = new IPresentermpl(this);
        iPresentermpl.getRequest(Apis.FINDFRIENDGOURPLIST, bean1.class);
        iPresentermpl.getRequest(Apis.FINDFRIENDLISTBYGROUPID, bean2.class);
    }

    /**
     * ExpandableListView条目点击事件
     */
    private void initListenter() {
        //子对象点击监听事件
        mElistview.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(getActivity(), nameStrings[groupPosition][childPosition] + "", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        //组对象点击监听事件
        mElistview.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;//请务必返回false，否则分组不会展开
            }
        });
        //组对象判断分组监听事件
        mAdapter.setOnGroupExPanded(this);
    }

    /**
     * 初始化适配器
     */
    private void initAdapter() {
        mAdapter = new LinkmanFriendAdapter(mList, getActivity());
        mElistview.setAdapter(mAdapter);
    }

    /**
     * 初始化数据源
     */
    private void initList() {

        /*for (int i = 0; i < titleStrings.length; i++) {
            //创建组对象
            bean1 info = new bean1();
            //循环添加组的标题名
            info.setTitle(titleStrings[i]);
            //创建子对象数据源
            List<bean2> list = new ArrayList<>();
            for (int j = 0; j < nameStrings.length; j++) {
                //创建子对象
                bean2 info2 = new bean2();
                //添加用户名或者头像
                info2.setName(nameStrings[i][j]);
                //将子对象添加到数据源
                list.add(info2);
            }
            //将子对象数据源复制给组对象
            info.setInfo(list);
            //将组对象添加到总数据源中
            mList.add(info);
        }*/
        for (int i = 0; i < groupResult.size(); i++) {
            //创建组对象
            bean1.ResultBean resultBean = new bean1.ResultBean();
            //循环添加组的标题名
            resultBean.setGroupName(groupResult.get(i).getGroupName());
            //创建子对象数据源
            List<bean2.ResultBean> list = new ArrayList<>();
            for (int j = 0; j < firendsResult.size(); j++) {
                //创建子对象
                bean2.ResultBean resultBean2 = new bean2.ResultBean();
                //添加用户名或者头像
                resultBean2.setNickName(firendsResult.get(i).getNickName());
                resultBean2.setFriendUid(firendsResult.get(i).getFriendUid());
                //将子对象添加到数据源
                list.add(resultBean2);
            }
            //将子对象数据源复制给组对象
            resultBean.setInfo(list);
            //将组对象添加到总数据源中
            mList.add(resultBean);
        }
    }

    /**
     * 监听是否关闭其他的组对象
     *
     * @param groupPostion
     */
    @Override
    public void onGroupExpanded(int groupPostion) {
        EListViewUtils utils = new EListViewUtils();
        utils.expandOnlyOne(groupPostion, mElistview);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void success(Object data) {
        if (data instanceof bean1) {
            bean1 beans = (bean1) data;
            groupResult = beans.getResult();
            if (groupResult.size() == 0) {
                groupResult.get(0).setGroupName("我的好友");
                groupResult.get(1).setGroupName("黑名单");
                mList.add((bean1.ResultBean) groupResult);
            } else {
                initList();
            }
        } else if (data instanceof bean2) {
            bean2 beans = (bean2) data;
            firendsResult = beans.getResult();

        }

    }

    @Override
    public void failure(String error) {

    }

    @OnClick({R.id.linkman_linear,R.id.linkman_linear1})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.linkman_linear :
                linkmanLinear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), GroupActivity.class);
                        startActivity(intent);
                    }
                });
                break;
            case R.id.linkman_linear1 :
                linkmanLinear1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(),NewFriendActivity.class);
                        startActivity(intent);
                    }
                });
                break;
        }

    }
}
