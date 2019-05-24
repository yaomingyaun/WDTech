package com.wd.tech.home.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wd.tech.R;
import com.wd.tech.home.adapter.SearchAdapter;
import com.wd.tech.home.bean.SearchBean;
import com.wd.tech.home.view.HistoryFlowLayout;
import com.wd.tech.utils.netWork.Api;
import com.wd.tech.utils.presenter.IPresentermpl;
import com.wd.tech.utils.view.IView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity implements IView {

    @BindView(R.id.tech_seach_edit)
    EditText techSeachEdit;
    @BindView(R.id.diss_text)
    TextView dissText;
    @BindView(R.id.film_seach_relative)
    RelativeLayout filmSeachRelative;
    @BindView(R.id.ho)
    TextView ho;
    @BindView(R.id.hotlayout)
    HistoryFlowLayout hotlayout;
    @BindView(R.id.hotsearch)
    RelativeLayout hotsearch;
    @BindView(R.id.search_showlayout)
    XRecyclerView search_showlayout;
    @BindView(R.id.none_search)
    RelativeLayout none_search;
    private TextView mTextView;
    IPresentermpl iPresentermpl;
    int PAGE=1;
    SearchAdapter searchAdapter;
    private String s;
    private String s1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        iPresentermpl = new IPresentermpl(this);
        init();
        dissText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search_showlayout.setVisibility(View.VISIBLE);
                filmSeachRelative.setVisibility(View.VISIBLE);
                hotsearch.setVisibility(View.INVISIBLE);
                none_search.setVisibility(View.INVISIBLE);
                search_showlayout.setPullRefreshEnabled(true);
                search_showlayout.setLoadingMoreEnabled(true);
                PAGE=1;
                search_showlayout.setLoadingListener(new XRecyclerView.LoadingListener() {
                    @Override
                    public void onRefresh() {
                        PAGE=1;
                        iPresentermpl.getRequest(String.format(Api.FINDINFORMATIONBYTIRLE,techSeachEdit.getText().toString().trim().replace("\"","").replace("\"",""),PAGE,10),SearchBean.class);
                    }

                    @Override
                    public void onLoadMore() {
                        iPresentermpl.getRequest(String.format(Api.FINDINFORMATIONBYTIRLE,techSeachEdit.getText().toString().trim().replace("\"","").replace("\"",""),PAGE,10),SearchBean.class);
                    }
                });
                iPresentermpl.getRequest(String.format(Api.FINDINFORMATIONBYTIRLE,techSeachEdit.getText().toString().trim().replace("\"","").replace("\"",""),PAGE,10),SearchBean.class);
                layout();

                search_showlayout.setVisibility(View.VISIBLE);
                filmSeachRelative.setVisibility(View.VISIBLE);
                hotsearch.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void init() {
        techSeachEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(count!=0) {
                   dissText.setText("搜索");
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count != 0) {
                    dissText.setText("搜索");
                }else {
                    dissText.setText("取消");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        final List<String> textList = new ArrayList<>();
        textList.add("中年危机");
        textList.add("区块链");
        textList.add("苹果");
        textList.add("锤子科技");
        textList.add("子弹短信");
        textList.add("民营企业");
        textList.add("特斯拉");
        textList.add("支付宝");
        textList.add("资本市场");
        textList.add("电视剧");
        textList.add("量子位");
        for (int i=0;i<textList.size();i++)
        {
            mTextView = new TextView(this);
            mTextView.setText(textList.get(i));
            mTextView.setTextColor(Color.BLACK);
            mTextView.setTextSize(16);
            mTextView.setPadding(50,7,50,7);
            hotlayout.addView(mTextView);
            mTextView.setBackgroundResource(R.drawable.backgroud_search);
            final int finalI = i;
            final int finalI1 = i;
            mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // mResou.setVisibility(View.INVISIBLE);
                    techSeachEdit.setText(textList.get(finalI1));
                    //mSearchMessageRecyc.setVisibility(View.VISIBLE);
//                    s = mTextView.getText().toString();
//                    s1 = s.replace("\"", "").replace("\"", "");
                    layout();
                    search_showlayout.setVisibility(View.VISIBLE);
                    filmSeachRelative.setVisibility(View.VISIBLE);
                    hotsearch.setVisibility(View.INVISIBLE);
                    none_search.setVisibility(View.INVISIBLE);
//                    search_showlayout.setPullRefreshEnabled(true);
//                    search_showlayout.setLoadingMoreEnabled(true);
//                    search_showlayout.setLoadingListener(new XRecyclerView.LoadingListener() {
//                        @Override
//                        public void onRefresh() {
//                            PAGE=1;
//                            iPresentermpl.getRequest(String.format(Api.FINDINFORMATIONBYTIRLE,textList.get(finalI),1,15),SearchBean.class);
//                        }
//
//                        @Override
//                        public void onLoadMore() {
//                            iPresentermpl.getRequest(String.format(Api.FINDINFORMATIONBYTIRLE,textList.get(finalI),1,15),SearchBean.class);
//                        }
//                    });
                    iPresentermpl.getRequest(String.format(Api.FINDINFORMATIONBYTIRLE,textList.get(finalI),1,15),SearchBean.class);
                    layout();
                }
            });
        }
    }
    //布局
    public void layout(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        search_showlayout.setLayoutManager(layoutManager);
        searchAdapter = new SearchAdapter(this);
        search_showlayout.setAdapter(searchAdapter);
    }

    @Override
    public void success(Object data) {
      if(data instanceof SearchBean) {
          SearchBean bean = (SearchBean) data;
          List<SearchBean.ResultBean> result = bean.getResult();
          if (!result.isEmpty()) {
//              if (PAGE == 1) {
                  searchAdapter.setList(result);
//              } else {
//                  searchAdapter.addList(result);
//              }
//              PAGE++;
//              search_showlayout.refreshComplete();
//              search_showlayout.loadMoreComplete();
              return;
          }

          none_search.setVisibility(View.VISIBLE);
          search_showlayout.setVisibility(View.INVISIBLE);
          filmSeachRelative.setVisibility(View.VISIBLE);
          hotsearch.setVisibility(View.INVISIBLE);
      }

    }

    @Override
    public void failure(String error) {

    }
}
