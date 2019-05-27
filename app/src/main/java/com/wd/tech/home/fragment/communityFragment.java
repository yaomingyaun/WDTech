package com.wd.tech.home.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wd.tech.R;
import com.wd.tech.activity.NewsMessageActivity;
import com.wd.tech.adapter.CommunityNewsAdapter;
import com.wd.tech.bean.AddNewsBean;
import com.wd.tech.bean.CommunityNewsBean;
import com.wd.tech.bean.NewsDZBean;
import com.wd.tech.bean.NewsQXBean;
import com.wd.tech.bean.PLBean;
import com.wd.tech.user.activity.LoginActivity;
import com.wd.tech.utils.AlertDialogUtils;
import com.wd.tech.utils.netWork.Api;
import com.wd.tech.utils.presenter.IPresentermpl;
import com.wd.tech.utils.view.IView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class communityFragment extends Fragment implements IView {

    @BindView(R.id.news_XRecyclerView)
    XRecyclerView news_XRecyclerView;
    CommunityNewsAdapter communityNewsAdapter;
    IPresentermpl iPresentermpl;
    int page=1;
    private  int position;
    private SharedPreferences sharedPreferences;
    private String userId,sessionId;
    @BindView(R.id.news_yuan)
    ImageView news_yuan;
    PopupWindow popupWindow;
    private  EditText edit_talk;
    private ScrollView ll_scroll;
    private  TextView submit_talk;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.communityfragment, container, false);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        iPresentermpl = new IPresentermpl(this);
        //创建布局
        getLineManager();
        sharedPreferences = getContext().getSharedPreferences("User", Context.MODE_PRIVATE);
        userId = sharedPreferences.getString("userId", "");
        sessionId = sharedPreferences.getString("sessionId", "");
    }
    @OnClick({R.id.news_yuan})
    public  void  setlick(View view)
    {
        switch (view.getId())
        {
            case R.id.news_yuan:
                    Intent intent=new Intent(getContext(),NewsMessageActivity.class);
                    startActivity(intent);
                    break;
                    default:break;
        }
    }

    private void getLineManager() {
        //创建布局
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        news_XRecyclerView.setLayoutManager(layoutManager);
            //adapter
        communityNewsAdapter = new CommunityNewsAdapter(getContext());
        news_XRecyclerView.setAdapter(communityNewsAdapter);
        //设置支持刷新
        news_XRecyclerView.setPullRefreshEnabled(true);
        news_XRecyclerView.setLoadingMoreEnabled(true);
        news_XRecyclerView.setHasFixedSize(true);
        news_XRecyclerView.setNestedScrollingEnabled(false);
        news_XRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
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
        //点赞取消点赞
        communityNewsAdapter.setMdata(new CommunityNewsAdapter.setDZOnclick() {
            @Override
            public void onclick(int whetherGreat, int i,int communityId) {
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
        //点击评论
        communityNewsAdapter.setLick1(new CommunityNewsAdapter.setTuiSetonClick1() {
            @Override
            public void oncick(final int mid) {
                final View view = getLayoutInflater().inflate(R.layout.pop_reviews, null,false);
                 submit_talk=view.findViewById(R.id.submit_talk);
                 edit_talk=view.findViewById(R.id.edit_talk);
                ll_scroll=view.findViewById(R.id.ll_scroll);
                popupWindow=new PopupWindow(view,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT,true);
                //点击外部 pop消失
                popupWindow.setTouchable(true);
                popupWindow.setBackgroundDrawable(new BitmapDrawable());
                //位于相册下方
                popupWindow.showAtLocation(view,Gravity.BOTTOM,0,0);
                //监听输入框
                edit_talk.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        controlKeyboardLayout(ll_scroll, edit_talk);
                    }
                });
                //提交发布评论的内容
                submit_talk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String pl=edit_talk.getText().toString();
                        if(pl.length()==0)
                        {
                            Toast.makeText(getContext(), "输入不能为空！", Toast.LENGTH_SHORT).show();
                        }else
                        {
                            Map<String,String> map=new HashMap<>();
                            map.put("communityId",mid+"");
                            map.put("content",pl);
                            iPresentermpl.postRequest(Api.ADDCOMMUNITYCOMMENT,map,AddNewsBean.class);
                            popupWindow.dismiss();
                            lodata();
                        }
                    }
                });
            }
        });
    }
    /**
     * @param root         最外层布局，需要调整的布局
     * @param scrollToView 被键盘遮挡的scrollToView，滚动root,使scrollToView在root可视区域的底部
     */
    private void controlKeyboardLayout(final View root, final View scrollToView) {
        root.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        Rect rect = new Rect();
                        // 获取root在窗体的可视区域
                        root.getWindowVisibleDisplayFrame(rect);
                        // 获取root在窗体的不可视区域高度(被其他View遮挡的区域高度)
                        int rootInvisibleHeight = root.getRootView().getHeight() - rect.bottom;
                        // 若不可视区域高度大于100，则键盘显示
                        if (rootInvisibleHeight > 100) {
                            int[] location = new int[2];
                            // 获取scrollToView在窗体的坐标
                            scrollToView.getLocationInWindow(location);
                            // 计算root滚动高度，使scrollToView在可见区域
                            int srollHeight = (location[1]
                                    + scrollToView.getHeight() + dp2px(10)) - rect.bottom;
                            root.scrollTo(0, srollHeight);
                        } else {
                            // 键盘隐藏
                            root.scrollTo(0, 0);
                        }
                    }

                });
    }

    private int dp2px(int dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
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
        iPresentermpl.getRequest(String.format(Api.FINDCOUMMUNITYLIST, page), PLBean.class);
    }
    @Override
    public void success (Object data){
        if(data instanceof PLBean)
        {
            PLBean communityNewsBean= (PLBean) data;
            if(page==1)
            {
                communityNewsAdapter.setlist(communityNewsBean.getResult());
            }else
            {
                communityNewsAdapter.addlist(communityNewsBean.getResult());
            }
            page++;
            news_XRecyclerView.refreshComplete();
            news_XRecyclerView.loadMoreComplete();
        }
        if(data instanceof  NewsDZBean)
        {
            NewsDZBean newsDZBean= (NewsDZBean) data;

            if(newsDZBean.getMessage().equals("点赞成功"))
            {
                Toast.makeText(getContext(), newsDZBean.getMessage()+"", Toast.LENGTH_SHORT).show();
                communityNewsAdapter.getGivePraise(position);
            }
            else
            {
                AlertDialogUtils alertDialogUtils = new AlertDialogUtils(getContext());
                alertDialogUtils.show();
            }
        }
        if(data instanceof  NewsQXBean)
        {
            NewsQXBean newsQXBean= (NewsQXBean) data;
            if(newsQXBean.getMessage().equals("取消成功"))
            {
                Toast.makeText(getContext(), "取消成功", Toast.LENGTH_SHORT).show();
                communityNewsAdapter.getCancelPraise(position);
            }else
            {
                Toast.makeText(getContext(), newsQXBean.getMessage()+"", Toast.LENGTH_SHORT).show();
            }
        }
        if(data instanceof  AddNewsBean)
        {
            AddNewsBean addNewsBean= (AddNewsBean) data;
            if(addNewsBean.getMessage().equals("評論成功"))
            {
                Toast.makeText(getContext(), "评论成功", Toast.LENGTH_SHORT).show();
                iPresentermpl.getRequest(String.format(Api.FINDCOUMMUNITYLIST, page), CommunityNewsBean.class);
            }else
            {
                Toast.makeText(getContext(), addNewsBean.getMessage()+"", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    public void failure (String error){
    }
}
