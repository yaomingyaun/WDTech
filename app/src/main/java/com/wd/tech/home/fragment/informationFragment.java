package com.wd.tech.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.stx.xhb.xbanner.XBanner;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.wd.tech.R;
import com.wd.tech.home.activity.InformationDetailsActivity;
import com.wd.tech.home.activity.MeunActivity;
import com.wd.tech.home.activity.SearchActivity;
import com.wd.tech.home.activity.WebActivity;
import com.wd.tech.home.adapter.HomeAdapter;
import com.wd.tech.home.bean.BannerBean;
import com.wd.tech.home.bean.GreatBean;
import com.wd.tech.home.bean.HomeBean;
import com.wd.tech.utils.AlertDialogUtils;
import com.wd.tech.utils.netWork.Api;
import com.wd.tech.utils.presenter.IPresentermpl;
import com.wd.tech.utils.view.IView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class informationFragment extends Fragment implements IView ,View.OnClickListener {
    @BindView(R.id.menu_img)
    ImageView menu_img;
    @BindView(R.id.search_img)
    ImageView search_img;
    @BindView(R.id.viewpager_draw)
    XBanner xBanner;
    @BindView(R.id.informa_xrecycle)
    RecyclerView xRecyclerView;
    IPresentermpl iPresentermpl;
    HomeAdapter homeAdapter;
    private IWXAPI api;
    private int PAGE=1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.informationfragment, container, false);
        ButterKnife.bind(this,view);
        SmartRefreshLayout refreshLayout = view.findViewById(R.id.refreshLayout);
        iPresentermpl=new IPresentermpl(this);
        homeAdapter = new HomeAdapter(getActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        xRecyclerView.setLayoutManager(layoutManager);
        xRecyclerView.setAdapter(homeAdapter);
        init();
        //刷新的监听事件
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                //请求数据
                PAGE=1;
                iPresentermpl.getRequest(String.format(Api.INFORECOMMENDLIST,PAGE,7),HomeBean.class);
                refreshLayout.finishRefresh();  //刷新完成
            }
        });
        //加载的监听事件
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                PAGE++;
                iPresentermpl.getRequest(String.format(Api.INFORECOMMENDLIST,PAGE,7),HomeBean.class);
                refreshLayout.finishLoadMore();      //加载完成
            }
        });
        //收藏
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
        homeAdapter.setShareClick(new HomeAdapter.ShareClick() {
            @Override
            public void click(int circleId) {
                WXWebpageObject webpage = new WXWebpageObject();
                webpage.webpageUrl ="http://www.hooxiao.com";
                //用 WXWebpageObject 对象初始化一个 WXMediaMessage 对象
                WXMediaMessage msg = new WXMediaMessage(webpage);
                api = WXAPIFactory.createWXAPI(getActivity(), "wx4c96b6b8da494224", true);
                // 将应用的appId注册到微信
                api.registerApp("wx4c96b6b8da494224");
//                msg.title =result.getTitle();
//                msg.description =result.getSummary();
                msg.mediaObject = webpage;
                SendMessageToWX.Req req = new SendMessageToWX.Req();
                //transaction用于唯一标识一个请求
                //req.transaction = buildTransaction("webpage");
                req.message = msg;
                req.scene = SendMessageToWX.Req.WXSceneSession;    //设置发送到朋友
                api.sendReq(req);
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        iPresentermpl.getRequest(String.format(Api.INFORECOMMENDLIST,PAGE,7),HomeBean.class);
    }

    //网络请求
    public void init(){
        iPresentermpl.getRequest(String.format(Api.INFORECOMMENDLIST,PAGE,10),HomeBean.class);
        iPresentermpl.getRequest(Api.BANNERSHOW,BannerBean.class);
    }
    //分类的点击事件
    @OnClick(R.id.menu_img)
    public void munu(){
        startActivity(new Intent(getActivity(),MeunActivity.class));
    }
    //搜索的点击事件
    @OnClick(R.id.search_img)
    public void search(){
        startActivity(new Intent(getActivity(),SearchActivity.class));
    }
    @Override
    public void success(Object data) {
        if(data instanceof BannerBean){
            //banner图
            final BannerBean bannerBean= (BannerBean) data;
            List<String> list = new ArrayList<>();
            for (int i=0;i<bannerBean.getResult().size();i++) {
                list.add(bannerBean.getResult().get(i).getTitle());
            }
            xBanner.setData(bannerBean.getResult(),list);
            xBanner.loadImage(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, Object model, View view, int position) {
                    BannerBean.ResultBean bean= (BannerBean.ResultBean) model;
                    Glide.with(getActivity()).load(bean.getImageUrl()).into((ImageView) view);
                    banner.setPageChangeDuration(1000);
                }
            });
            xBanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
                @Override
                public void onItemClick(XBanner banner, Object model, View view, int position) {
                    BannerBean.ResultBean bean = (BannerBean.ResultBean) model;
                    if(position==0){
                        Intent intent = new Intent(getActivity(), InformationDetailsActivity.class);
                        intent.putExtra("id",1+"");
                        startActivity(intent);
                    }else {
                        Intent intent = new Intent(getActivity(), WebActivity.class);
                        intent.putExtra("url", bean.getJumpUrl());
                        startActivity(intent);
                    }
                }
            });
        }
        if (data instanceof  HomeBean){
            HomeBean homeBean= (HomeBean) data;
            List<HomeBean.ResultBean> result = homeBean.getResult();
            if (PAGE==1){
                homeAdapter.setHomelist(result);
            }else {
                homeAdapter.addHomelist(result);
            }

        }
        //收藏
        if(data instanceof GreatBean){
            GreatBean bean= (GreatBean) data;
            if(bean.getStatus().equals("0000")){
                Toast.makeText(getActivity(),bean.getMessage(),Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getActivity(),bean.getMessage(),Toast.LENGTH_SHORT).show();
            }
            if(bean.getStatus().equals("9999")) {
                AlertDialogUtils alertDialogUtils = new AlertDialogUtils(getActivity());
                alertDialogUtils.show();
            }
        }
    }

    @Override
    public void failure(String error) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        iPresentermpl.deatch();
    }
    //点击事件
    @Override
    public void onClick(View v) {

    }
}
